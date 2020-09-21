package mk.ukim.finki.emt.ordermanagement.domain.model;

import lombok.Getter;
import mk.ukim.finki.emt.sharedkernel.domain.base.AbstractEntity;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Currency;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Money;
import mk.ukim.finki.emt.sharedkernel.domain.geo.RecipientAddress;

import javax.persistence.*;
import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Entity
@Table(name = "orders")
public class Order extends AbstractEntity<OrderId> {

    @Version
    private Long version;

    @Enumerated(value = EnumType.STRING)
    private Currency currency;

    @Column(name = "order_state", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private OrderState orderState;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "address", column = @Column(name = "billing_address", nullable = false)),
            @AttributeOverride(name = "city", column = @Column(name = "billing_city", nullable = false)),
            @AttributeOverride(name = "country", column = @Column(name = "billing_country", nullable = false))
    })
    private RecipientAddress billingAddress;

    @Column(name = "ordered_on", nullable = false)
    private Instant orderedOn;

    @Column(name = "clientId", nullable = false)
    private ClientId clientId;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<OrderItem> items;

    public Order() {
    }

    public Order(OrderId orderId, Currency currency, RecipientAddress billingAddress, OrderState orderState, ClientId clientId) {
        super(orderId);
        this.items = new HashSet<>();
        this.currency = currency;
        this.orderedOn = Instant.now();
        this.billingAddress = billingAddress;
        this.orderState = orderState;
        this.clientId = clientId;

    }

    @Override
    public OrderId id() {
        return id;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public void setOrderedOn(Instant orderedOn) {
        this.orderedOn = orderedOn;
    }

    public void setBillingAddress(RecipientAddress recipientAddress) {
        this.billingAddress = recipientAddress;
    }

    public void setOrderState(OrderState orderState) {
        this.orderState = orderState;
    }

    public void setClientId(ClientId clientId){ this.clientId = clientId; }

    public Money total() {
        return items.stream().map(OrderItem::subtotal).reduce(new Money(currency, 0), Money::add);
    }

    public OrderItem addOrderItem(OrderItem orderItem) {
        items.add(orderItem);
        return orderItem;
    }

    public OrderItem removeOrderItem(OrderItem orderItem) {
        items.remove(orderItem);
        return orderItem;
    }

    public void addOrderItems(List<OrderItem> orderItems) {
        items.addAll(orderItems);
    }
}
