package mk.ukim.finki.emt.ordermanagement.domain.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import mk.ukim.finki.emt.sharedkernel.domain.base.DomainObjectId;

import javax.persistence.Embeddable;

@Embeddable
public class ClientId extends DomainObjectId {

    protected ClientId() {
        super(DomainObjectId.randomId(mk.ukim.finki.emt.ordermanagement.domain.model.ClientId.class).toString());
    }

    @JsonCreator
    public ClientId(String clientId) {
        super(clientId);
    }

}
