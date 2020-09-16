package mk.finki.ukim.emt.supplementscatalog.domain.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import org.springframework.lang.NonNull;
import mk.ukim.finki.emt.sharedkernel.domain.base.DomainObjectId;

public class OrderItemId extends DomainObjectId {

    protected OrderItemId() {
        super(DomainObjectId.randomId(OrderItemId.class).toString());
    }

    @JsonCreator
    public OrderItemId(@NonNull String id) {
        super(id);
    }
}
