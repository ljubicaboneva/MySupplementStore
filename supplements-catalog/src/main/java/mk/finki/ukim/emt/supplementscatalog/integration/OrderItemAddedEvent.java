package mk.finki.ukim.emt.supplementscatalog.integration;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import mk.finki.ukim.emt.supplementscatalog.domain.model.OrderId;
import mk.finki.ukim.emt.supplementscatalog.domain.model.OrderItemId;
import mk.finki.ukim.emt.supplementscatalog.domain.model.SupplementId;
import mk.ukim.finki.emt.sharedkernel.domain.base.DomainEvent;
import mk.ukim.finki.emt.sharedkernel.domain.measurement.Quantity;

import java.time.Instant;

@Getter
public class OrderItemAddedEvent implements DomainEvent {

    @JsonProperty("orderItemId")
    private final OrderItemId orderItemId;

    @JsonProperty("supplementId")
    private final SupplementId supplementId;

    @JsonProperty("orderId")
    private final OrderId orderId;

    @JsonProperty("quantity")
    private final Quantity quantity;

    @JsonProperty("occurredOn")
    private final Instant occurredOn;

    @JsonCreator
    public OrderItemAddedEvent(OrderItemId orderItemId, SupplementId supplementId, OrderId orderId,
                          Quantity quantity, Instant occurredOn) {
        this.orderId = orderId;
        this.orderItemId = orderItemId;
        this.supplementId = supplementId;
        this.quantity = quantity;
        this.occurredOn = occurredOn;
    }

    @Override
    public Instant occurredOn() {
        return occurredOn;
    }
}