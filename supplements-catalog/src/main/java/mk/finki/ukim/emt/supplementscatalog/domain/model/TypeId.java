package mk.finki.ukim.emt.supplementscatalog.domain.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import mk.ukim.finki.emt.sharedkernel.domain.base.DomainObjectId;

public class TypeId extends DomainObjectId {

    private TypeId() {
        super(DomainObjectId.randomId(TypeId.class).getId());
    }

    @JsonCreator
    public TypeId(String id) {
        super(id);
    }
}