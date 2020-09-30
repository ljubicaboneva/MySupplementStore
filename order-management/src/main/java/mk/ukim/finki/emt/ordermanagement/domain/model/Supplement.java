package mk.ukim.finki.emt.ordermanagement.domain.model;

import lombok.Getter;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Money;
import mk.ukim.finki.emt.sharedkernel.domain.identity.Name;
import mk.ukim.finki.emt.sharedkernel.domain.measurement.Quantity;

@Getter
public class Supplement {

    private SupplementId id;

    private Name name;

    private Money price;

    private Quantity quantity;

    public String getNameValue() {
        return name.getName();
    }
}
