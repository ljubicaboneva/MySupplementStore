package mk.ukim.finki.emt.ordermanagement.domain.requests;

import com.sun.istack.NotNull;
import lombok.Getter;
import mk.ukim.finki.emt.ordermanagement.domain.model.OrderItemId;
import mk.ukim.finki.emt.ordermanagement.domain.model.SupplementId;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Money;
import mk.ukim.finki.emt.sharedkernel.domain.measurement.Quantity;

@Getter
public class OrderItemCreateRequest {

    @NotNull
    OrderItemId orderItemId;

    @NotNull
    SupplementId supplementId;

    @NotNull
    Money price;

    @NotNull
    Quantity quantity;
}