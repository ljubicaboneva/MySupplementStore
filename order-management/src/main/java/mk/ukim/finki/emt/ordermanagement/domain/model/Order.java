package mk.ukim.finki.emt.ordermanagement.domain.model;

import lombok.Getter;
import mk.ukim.finki.emt.sharedkernel.domain.base.AbstractEntity;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Currency;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Money;
import mk.ukim.finki.emt.sharedkernel.domain.geo.RecipientAddress;
import mk.ukim.finki.emt.sharedkernel.domain.measurement.Quantity;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Getter
@Entity
@Table(name = "orders")
public class Order extends AbstractEntity<OrderId> {

    @Version
    private Long version;

    @Column(name = "currency", nullable = false)
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

    @AttributeOverrides({@AttributeOverride(name = "id", column = @Column(name = "client_id", nullable = false))})
    private ClientId clientId;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<OrderItem> items;

    public Order(OrderId orderId, Currency currency, RecipientAddress billingAddress, OrderState orderState, ClientId clientId) {
        super(orderId);
        this.items = new HashSet<>();
        this.currency = currency;
        this.orderedOn = Instant.now();
        this.billingAddress = billingAddress;
        this.orderState = orderState;
        this.clientId = clientId;

    }

    public Order (){}

    public Order(Instant now, Currency currency, RecipientAddress toDomainModel) {
        this.orderedOn = now;
        this.currency = currency;
        this.billingAddress = toDomainModel;

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

    public int total() {
        Money money = items.stream().map(OrderItem::subtotal).reduce(new Money(currency, 0), Money::add);
        return money.getAmount();
    }

    public OrderItem addOrderItem(OrderItem orderItem) {
        items.add(orderItem);
        return orderItem;
    }

    public OrderItem removeOrderItem(OrderItem orderItem) {
        items.remove(orderItem);
        return orderItem;
    }

    public OrderItem addItem(@NonNull Supplement supplement, int qty) {
        Objects.requireNonNull(supplement, "supplement must not be null");
        var item = new OrderItem(supplement.getId(), supplement.getPrice(), Quantity.valueOf(qty));
        item.setQuantity(Quantity.valueOf(qty));
        items.add(item);
        return item;
    }


    public void addOrderItems(List<OrderItem> orderItems) {
        items.addAll(orderItems);
    }
}
