package mk.ukim.finki.emt.ordermanagement.domain.model;

import lombok.Getter;
import mk.ukim.finki.emt.sharedkernel.domain.geo.Address;
import mk.ukim.finki.emt.sharedkernel.domain.identity.FullName;

import javax.persistence.Embedded;

@Getter
public class Client {

    private mk.ukim.finki.emt.ordermanagement.domain.model.ClientId id;

    @Embedded
    private FullName name;

    @Embedded
    private Address billingAddress;

    private String email;
}
