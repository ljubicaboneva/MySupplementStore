package mk.finki.ukim.emt.supplementscatalog.domain.model;

import lombok.Getter;
import mk.ukim.finki.emt.sharedkernel.domain.base.AbstractEntity;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Money;
import mk.ukim.finki.emt.sharedkernel.domain.identity.Name;
import mk.ukim.finki.emt.sharedkernel.domain.measurement.Quantity;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Entity
@Table(name = "supplements")
@Where(clause = "deleted=false")
public class Supplement extends AbstractEntity<SupplementId> {

    @Version
    private Long version;

    @Embedded
    private Name name;

    @Embedded
    private Quantity quantity;

    @Enumerated(EnumType.STRING)
    private Brand brand;

    @Embedded
    private Money price;

    @Column(name = "grams")
    private float grams;

    @ManyToOne(fetch = FetchType.LAZY)
    private Type type;

    @Column(name = "deleted", nullable = false)
    private boolean deleted = false;

    public Supplement() {
    }

    public Supplement(SupplementId supplementId, Name name, Brand brand, Money price, float grams, Quantity quantity, Type type) {
        super(supplementId);
        this.name = name;
        this.brand = brand;
        this.price = price;
        this.grams = grams;
        this.type = type;
        this.quantity = quantity;
    }

    @Override
    public SupplementId id() {
        return id;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public void setPrice(Money price) {
        this.price = price;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public void setQuantity(int quantity) {
        this.quantity = Quantity.valueOf(quantity);
    }

    public void reduceQuantity(Quantity reduceValue) {
        this.quantity = this.quantity.subtract(reduceValue);
    }

    public void increaseQuantity(Quantity increaseValue) {
        this.quantity = this.quantity.add(increaseValue);
    }


}