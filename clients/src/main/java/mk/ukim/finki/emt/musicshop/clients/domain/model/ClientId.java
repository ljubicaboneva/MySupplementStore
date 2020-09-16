package mk.ukim.finki.emt.musicshop.clients.domain.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import mk.ukim.finki.emt.musicshop.sharedkernel.domain.base.DomainObjectId;

import javax.persistence.Embeddable;

@Embeddable
public class ClientId extends DomainObjectId {

    protected ClientId() {
        super(DomainObjectId.randomId(ClientId.class).toString());
    }

    @JsonCreator
    public ClientId(String id) {
        super(id);
    }

}