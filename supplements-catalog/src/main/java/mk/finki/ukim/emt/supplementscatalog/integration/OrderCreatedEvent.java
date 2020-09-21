package mk.finki.ukim.emt.supplementscatalog.integration;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.NonNull;
import mk.finki.ukim.emt.supplementscatalog.domain.model.ClientId;
import mk.finki.ukim.emt.supplementscatalog.domain.model.OrderId;
import mk.ukim.finki.emt.sharedkernel.domain.base.DomainEvent;

import java.time.Instant;

public class OrderCreatedEvent implements DomainEvent {

    @JsonProperty("orderId")
    private final OrderId orderId;

    @JsonProperty("clientId")
    private final ClientId clientId;

    @JsonProperty("occurredOn")
    private final Instant occurredOn;

    @JsonCreator
    public OrderCreatedEvent(OrderId orderId, ClientId clientId, Instant occurredOn) {
        this.orderId = orderId;
        this.clientId = clientId;
        this.occurredOn = occurredOn;
    }

    @Override
    public Instant occurredOn() {
        return occurredOn;
    }

    @NonNull
    public OrderId orderId() {
        return orderId;
    }

    @NonNull
    public ClientId clientId() {
        return clientId;
    }
}
