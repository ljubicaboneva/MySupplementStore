package mk.ukim.finki.emt.ordermanagement.domain.event;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.NonNull;
import mk.ukim.finki.emt.ordermanagement.domain.model.*;
import mk.ukim.finki.emt.sharedkernel.domain.base.DomainEvent;
import mk.ukim.finki.emt.sharedkernel.domain.measurement.Quantity;

import java.time.Instant;

public class OrderItemAdded implements DomainEvent {

    @JsonProperty("orderId")
    private final OrderId orderId;

    @JsonProperty
    private final OrderItemId orderItemId;

    @JsonProperty
    private final SupplementId supplementId;

    @JsonProperty("quantity")
    private final Quantity quantity;

    @JsonProperty("occurredOn")
    private final Instant occurredOn;

    public OrderItemAdded(OrderId orderId, OrderItemId orderItemId, SupplementId supplementId, Quantity quantity, Instant occurredOn) {
        this.orderId = orderId;
        this.orderItemId = orderItemId;
        this.supplementId = supplementId;
        this.quantity = quantity;
        this.occurredOn = occurredOn;
    }

    @NonNull
    public OrderId orderId() {
        return orderId;
    }

    @NonNull
    public OrderItemId orderItemId() {
        return orderItemId;
    }

    @NonNull
    public SupplementId supplementId() {
        return supplementId;
    }

    @NonNull
    public Quantity quantity() {
        return quantity;
    }

    @Override
    public Instant occurredOn() {
        return occurredOn;
    }

}