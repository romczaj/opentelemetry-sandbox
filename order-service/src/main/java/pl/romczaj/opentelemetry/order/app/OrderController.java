package pl.romczaj.opentelemetry.order.app;

import io.micrometer.tracing.annotation.NewSpan;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orders")
@Slf4j
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    @NewSpan("controller-create-order")
    public void makeOrder(@RequestBody @Valid MakeOrderRequest makeOrderRequest) {
        orderService.makeOrder(makeOrderRequest);
    }
}