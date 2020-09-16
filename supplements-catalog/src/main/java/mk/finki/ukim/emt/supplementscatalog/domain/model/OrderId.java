package mk.finki.ukim.emt.supplementscatalog.domain.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.NonNull;
import mk.ukim.finki.emt.sharedkernel.domain.base.DomainObjectId;

public class OrderId extends DomainObjectId {

    protected OrderId() {
        super(DomainObjectId.randomId(OrderId.class).toString());
    }

    @JsonCreator
    public OrderId(@NonNull String id) {
        super(id);
    }
}