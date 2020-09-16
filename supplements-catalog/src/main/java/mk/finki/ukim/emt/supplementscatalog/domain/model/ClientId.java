package mk.finki.ukim.emt.supplementscatalog.domain.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import mk.ukim.finki.emt.sharedkernel.domain.base.DomainObjectId;

public class ClientId extends DomainObjectId {

    protected ClientId() {
        super(DomainObjectId.randomId(ClientId.class).toString());
    }

    @JsonCreator
    public ClientId(String id) {
        super(id);
    }

}