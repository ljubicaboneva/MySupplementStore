package mk.ukim.finki.emt.ordermanagement.application.form;

import mk.ukim.finki.emt.ordermanagement.domain.model.Supplement;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class OrderItemForm implements Serializable {

    @NotNull
    private Supplement supplement;

    @Min(1)
    private int quantity = 1;

    public Supplement getSupplement() {
        return supplement;
    }

    public void setSupplement(Supplement supplement) {
        this.supplement = supplement;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
