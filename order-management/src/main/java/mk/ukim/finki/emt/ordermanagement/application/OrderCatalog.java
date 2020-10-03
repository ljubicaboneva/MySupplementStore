package mk.ukim.finki.emt.ordermanagement.application;



import mk.ukim.finki.emt.ordermanagement.application.form.OrderForm;
import mk.ukim.finki.emt.ordermanagement.application.form.RecipientAddressForm;
import mk.ukim.finki.emt.ordermanagement.domain.event.OrderCreated;
import mk.ukim.finki.emt.ordermanagement.domain.event.OrderItemAdded;
import mk.ukim.finki.emt.ordermanagement.domain.model.*;
import mk.ukim.finki.emt.ordermanagement.domain.repository.OrderItemRepository;
import mk.ukim.finki.emt.ordermanagement.domain.repository.OrderRepository;
import mk.ukim.finki.emt.ordermanagement.port.requests.OrderCreateRequest;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Currency;
import mk.ukim.finki.emt.sharedkernel.domain.geo.RecipientAddress;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import javax.validation.Validator;
import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class OrderCatalog {

    private final OrderRepository orderRepository;

    private final OrderItemRepository orderItemRepository;

    private final ApplicationEventPublisher applicationEventPublisher;





    public OrderCatalog(OrderRepository orderRepository, OrderItemRepository orderItemRepository, ApplicationEventPublisher applicationEventPublisher) {
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.applicationEventPublisher = applicationEventPublisher;

    }

    @NonNull
    public Optional<Order> findById(@NonNull OrderId orderId) {
        Objects.requireNonNull(orderId, "orderId must not be null");
        return orderRepository.findById(orderId);
    }


    public Order create(OrderCreateRequest request) {
        //saving order
        Order newOrder = new Order(
                request.getOrderId(),
                Currency.valueOf(request.getCurrency()),
                request.getRecipientAddress(),
                OrderState.PROCESSING,
                new ClientId("1"));
        applicationEventPublisher.publishEvent(new OrderCreated(request.getOrderId(),newOrder.getOrderedOn()));

        newOrder = orderRepository.saveAndFlush(newOrder);



        //saving orderItems
        List<OrderItem> orderItemList = request.getOrderItems()
                .stream()
                .map(orderItemCreateRequest -> {
                    OrderItem orderItem = new OrderItem(
                            orderItemCreateRequest.getOrderItemId(),
                            orderItemCreateRequest.getSupplementId(),
                            orderItemCreateRequest.getPrice(),
                            orderItemCreateRequest.getQuantity());
                    return orderItemRepository.save(orderItem);
                })
                .collect(Collectors.toList());

        // adding order items to order
        newOrder.addOrderItems(orderItemList);
        newOrder = orderRepository.save(newOrder);

        //publish events
        OrderId orderId = newOrder.id();
        newOrder.getItems().forEach(orderItem ->
                applicationEventPublisher.publishEvent(new OrderItemAdded(
                        orderId,
                        orderItem.id(),
                        orderItem.getSupplementId(),
                        orderItem.getQuantity(),
                        Instant.now())));

        //return new order
        return newOrder;
    }

    public OrderId createOrder(@NonNull OrderForm order) {


        var newOrder = orderRepository.saveAndFlush(toDomainModel(order));
        applicationEventPublisher.publishEvent(new OrderCreated(newOrder.id(),newOrder.getOrderedOn()));
        newOrder.getItems().forEach(orderItem -> applicationEventPublisher.publishEvent(new OrderItemAdded(newOrder.id(),orderItem.id(),orderItem.getSupplementId(),orderItem.getQuantity(), Instant.now())));
        return newOrder.id();
    }

    @NonNull
    private Order toDomainModel(@NonNull OrderForm orderForm) {
        var order = new Order(Instant.now(), orderForm.getCurrency(),
                toDomainModel(orderForm.getBillingAddress()));
        orderForm.getItems().forEach(item -> order.addItem(item.getSupplement(), item.getQuantity()));
        return order;
    }

    @NonNull
    private RecipientAddress toDomainModel(@NonNull RecipientAddressForm form) {
        return new RecipientAddress(form.getName(), form.getAddress(),form.getCity(), form.getCountry());
    }


}
