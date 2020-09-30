package mk.ukim.finki.emt.ordermanagement.port.rest;

import mk.ukim.finki.emt.ordermanagement.application.OrderCatalog;
import mk.ukim.finki.emt.ordermanagement.domain.model.Order;
import mk.ukim.finki.emt.ordermanagement.domain.model.OrderId;
import mk.ukim.finki.emt.ordermanagement.port.requests.OrderCreateRequest;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
public class OrderCatalogController {

    private final OrderCatalog orderCatalog;
    private final ApplicationEventPublisher applicationEventPublisher;

    public OrderCatalogController(OrderCatalog orderCatalog, ApplicationEventPublisher applicationEventPublisher) {
        this.orderCatalog = orderCatalog;
        this.applicationEventPublisher = applicationEventPublisher;
    }


    @GetMapping("/{orderId}")
    public ResponseEntity<Order> getOrder(@PathVariable String orderId) {
        return orderCatalog.findById(new OrderId(orderId)).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Order createNewOrder(@RequestBody OrderCreateRequest request) {
       // applicationEventPublisher.publishEvent(new OrderCreated(request.getOrderId(),request.getOrderedOn()));

        return orderCatalog.createOrder(request);
    }

}
