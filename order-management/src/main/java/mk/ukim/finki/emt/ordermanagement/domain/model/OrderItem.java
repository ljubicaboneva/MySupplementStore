package mk.ukim.finki.emt.ordermanagement.domain.model;

import lombok.Getter;
import mk.ukim.finki.emt.sharedkernel.domain.base.AbstractEntity;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Money;
import mk.ukim.finki.emt.sharedkernel.domain.measurement.Quantity;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "order_items")
public class OrderItem extends AbstractEntity<OrderItemId> {

    @Embedded
    @AttributeOverrides({@AttributeOverride(name = "id", column = @Column(name = "supplemet_id", nullable = false))})
    private SupplementId supplementId;

    @Embedded
    private Money price;

    @Embedded
    @AttributeOverrides({@AttributeOverride(name = "value", column = @Column(name = "qty", nullable = false))})
    private Quantity quantity;

    @Column(name = "deleted", nullable = false)
    private boolean deleted = false;

    public OrderItem() {

    }

    public OrderItem(SupplementId supplementId, Money price, Quantity quantity) {
        this.supplementId = supplementId;
        this.price = price;
        this.quantity = quantity;
    }

    public OrderItem(OrderItemId orderItemId, SupplementId supplementId, Money price, Quantity quantity) {
        super(orderItemId);
        this.supplementId = supplementId;
        this.price = price;
        this.quantity = quantity;
    }

    @Override
    public OrderItemId id() {
        return id;
    }

    public void setSupplementId(SupplementId supplementId) {
        this.supplementId = supplementId;
    }

    public void setPrice(Money price) {
        this.price = price;
    }

    public void setQuantity(Quantity quantity) {
        this.quantity = quantity;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public Money subtotal() {
        return price.multiply(quantity.getValue());
    }
}

