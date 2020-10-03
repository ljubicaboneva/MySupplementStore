package mk.ukim.finki.emt.ordermanagement.port.ui;

import com.vaadin.flow.component.Html;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.*;

import mk.ukim.finki.emt.ordermanagement.application.OrderCatalog;
import mk.ukim.finki.emt.ordermanagement.domain.model.Order;
import mk.ukim.finki.emt.ordermanagement.domain.model.OrderId;
import mk.ukim.finki.emt.ordermanagement.domain.model.OrderItem;
import mk.ukim.finki.emt.sharedkernel.domain.geo.Address;

import java.util.Optional;

@Route("order")
@PageTitle("Show Order")
public class OrderDetailsView extends VerticalLayout implements HasUrlParameter<String> {

    private final OrderCatalog orderCatalog;

    public OrderDetailsView(OrderCatalog orderCatalog) {
        this.orderCatalog = orderCatalog;
        setSizeFull();
    }

    @Override
    public void setParameter(BeforeEvent event, @OptionalParameter String parameter) {
        Optional<Order> order = Optional.ofNullable(parameter).map(OrderId::new).flatMap(orderCatalog::findById);
        if (order.isPresent()) {
            showOrder(order.get());
        } else {
            showNoSuchOrder();
        }
    }

    private void showOrder(Order order) {
        var title = new Html("<h3>Order Details</h3>");
        add(title);

        var header = new FormLayout();
        header.addFormItem(createReadOnlyTextField(order.getOrderedOn().toString()), "Ordered on");
        header.addFormItem(createReadOnlyTextField(order.getOrderState().name()), "State");
        header.addFormItem(createReadOnlyAddressArea(order.getBillingAddress()), "Billing Address");
        add(header);

        var items = new Grid<OrderItem>();
        items.addColumn(OrderItem::getQty).setHeader("Qty");
        items.addColumn(OrderItem::getM).setHeader("Price");
        var subtotalExcludingTax = items.addColumn(OrderItem::sub).setHeader("Subtotal");
        items.setItems(order.getItems());
        var footerRow = items.appendFooterRow();
        footerRow.getCell(subtotalExcludingTax).setText(String.valueOf(order.total()));
        add(items);
    }

    private TextField createReadOnlyTextField(String value) {
        var textField = new TextField();
        textField.setReadOnly(true);
        textField.setValue(value);
        return textField;
    }

    private TextArea createReadOnlyAddressArea(Address address) {
        var textArea = new TextArea();
        textArea.setHeight("140px");
        textArea.setValue(formatAddress(address));
        textArea.setReadOnly(true);
        return textArea;
    }

    private String formatAddress(Address address) {
        var sb = new StringBuilder();
        sb.append(Optional.ofNullable(address.getAddress()).orElse("")).append("\n");
        sb.append(address.getCity()).append("\n");
        sb.append(address.getCountry());
        return sb.toString();
    }

    private void showNoSuchOrder() {
        add(new Text("The order does not exist."));
    }
}
