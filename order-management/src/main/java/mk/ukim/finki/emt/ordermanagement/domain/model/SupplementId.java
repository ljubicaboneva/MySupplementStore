package mk.ukim.finki.emt.ordermanagement.domain.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import mk.ukim.finki.emt.sharedkernel.domain.base.DomainObjectId;

import javax.persistence.Embeddable;

@Embeddable
public class SupplementId extends DomainObjectId {

    private SupplementId() {
        super(DomainObjectId.randomId(SupplementId.class).toString());
    }

    @JsonCreator
    public SupplementId(String id) {
        super(id);
    }
}

