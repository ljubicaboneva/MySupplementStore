package mk.ukim.finki.emt.ordermanagement.port.rest;

import mk.ukim.finki.emt.ordermanagement.application.OrderCatalog;
import mk.ukim.finki.emt.ordermanagement.domain.model.Order;
import mk.ukim.finki.emt.ordermanagement.domain.model.OrderId;
import mk.ukim.finki.emt.ordermanagement.domain.requests.OrderCreateRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderCatalogController {

    private final OrderCatalog orderCatalog;

    public OrderCatalogController(OrderCatalog orderCatalog) {
        this.orderCatalog = orderCatalog;
    }

    @GetMapping
    public List<Order> getOrders() {
        return orderCatalog.findAll();
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<Order> getOrder(@PathVariable String orderId) {
        return orderCatalog.findById(new OrderId(orderId)).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Order createNewOrder(@RequestBody OrderCreateRequest request) {
        return orderCatalog.createOrder(request);
    }

}
