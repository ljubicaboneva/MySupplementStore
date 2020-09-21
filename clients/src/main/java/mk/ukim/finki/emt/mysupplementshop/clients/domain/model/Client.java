package mk.ukim.finki.emt.mysupplementshop.clients.domain.model;

import lombok.Getter;
import lombok.NonNull;
import mk.ukim.finki.emt.sharedkernel.domain.base.AbstractEntity;
import mk.ukim.finki.emt.sharedkernel.domain.geo.Address;
import mk.ukim.finki.emt.sharedkernel.domain.identity.Name;


import javax.persistence.*;

@Entity
@Table(name = "clients")
@Getter
public class Client extends AbstractEntity<ClientId> {

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "first_name", column = @Column(name = "first_name", nullable = false)),
            @AttributeOverride(name = "last_name", column = @Column(name = "last_name", nullable = false)),
    })
    private Name name;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "street", column = @Column(name = "billing_address_street", nullable = false)),
            @AttributeOverride(name = "number", column = @Column(name = "billing_address_number", nullable = false)),
            @AttributeOverride(name = "zip", column = @Column(name = "billing_address_zip")),
            @AttributeOverride(name = "city", column = @Column(name = "billing_address_city", nullable = false)),
            @AttributeOverride(name = "country", column = @Column(name = "billing_address_country", nullable = false))
    })
    private Address billingAddress;

    private String email;

    public Client(@NonNull ClientId clientId,@NonNull Name name, @NonNull Address billingAddress, @NonNull String email) {
        super(clientId);
        this.name = name;
        this.billingAddress = billingAddress;
        this.email = email;
    }

    protected Client() {}

    public void setName(Name name) {
        this.name = name;
    }

    public void setBillingAddress(Address billingAddress) {
        this.billingAddress = billingAddress;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public ClientId id() {
        return id;
    }
}

