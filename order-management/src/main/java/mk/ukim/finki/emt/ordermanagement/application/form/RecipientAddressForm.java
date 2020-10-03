package mk.ukim.finki.emt.ordermanagement.application.form;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;


@Data
public class RecipientAddressForm implements Serializable {

    @NotEmpty
    private String name;
    @NotEmpty
    private String address;
    @NotNull
    private String city;
    @NotNull
    private String country;


}


