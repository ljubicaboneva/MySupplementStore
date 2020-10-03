package mk.finki.ukim.emt.supplementscatalog.port.ui;


import com.vaadin.flow.component.Html;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.*;
import mk.finki.ukim.emt.supplementscatalog.application.SupplementCatalog;
import mk.finki.ukim.emt.supplementscatalog.domain.model.Supplement;
import mk.finki.ukim.emt.supplementscatalog.domain.model.SupplementId;
import mk.ukim.finki.emt.sharedkernel.domain.geo.Address;

import java.util.Optional;

@Route("supplement")
@PageTitle("Show Supplement")
public class SupplementDetailsView extends VerticalLayout implements HasUrlParameter<String> {

    private final SupplementCatalog supplementCatalog;

    public SupplementDetailsView(SupplementCatalog supplementCatalog) {
        this.supplementCatalog = supplementCatalog;

        setSizeFull();
    }

    @Override
    public void setParameter(BeforeEvent event, @OptionalParameter String parameter) {
        Optional<Supplement> supplement = Optional.ofNullable(parameter).map(SupplementId::new).flatMap(supplementCatalog::findById);
        if (supplement.isPresent()) {
            showSupplement(supplement.get());
        } else {
            showNoSuchSupplement();
        }
    }

    private void showSupplement(Supplement supplement) {
        var title = new Html("<h3>Supplement Details</h3>");
        add(title);

        var header = new FormLayout();
        header.addFormItem(createReadOnlyTextField(supplement.getName().toString()), "Name");
        header.addFormItem(createReadOnlyTextField(supplement.getType().toString()), "Type");
        header.addFormItem(createReadOnlyTextField(supplement.getBrand().toString()), "Brand");
        add(header);

    }

    private TextField createReadOnlyTextField(String value) {
        var textField = new TextField();
        textField.setReadOnly(true);
        textField.setValue(value);
        return textField;
    }


    private void showNoSuchSupplement() {
        add(new Text("The supplement does not exist."));
    }
}
