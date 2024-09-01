package pl.romczaj.opentelemetry.order.app;

import io.micrometer.tracing.annotation.NewSpan;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.romczaj.opentelemetry.internal.MockDelay;
import pl.romczaj.opentelemetry.internal.ProductValidateInternalRequest;
import pl.romczaj.opentelemetry.internal.ProductValidateInternalRequest.ProductInternal;
import pl.romczaj.opentelemetry.order.app.MakeOrderRequest.OrderProductDto;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderService {

    private final ProductServiceClient productServiceClient;
    private final EmailClient emailClient;
    private final PaymentClient paymentClient;

    @NewSpan("order-service-make-order")
    public void makeOrder(MakeOrderRequest makeOrderRequest) {
        log.info("Creating order for customer: {}", makeOrderRequest);
        MockDelay.ofMilliseconds(200);
        ProductValidateInternalRequest productValidateInternalRequest = buildProductValidateInternalRequest(makeOrderRequest.orderProducts());
        productServiceClient.validateProducts(productValidateInternalRequest);
        emailClient.sendConfirmationEmail(makeOrderRequest.customerId());
        paymentClient.startPayment(makeOrderRequest.customerId());
    }

    private ProductValidateInternalRequest buildProductValidateInternalRequest(List<OrderProductDto> orderProductDtos) {
        return new ProductValidateInternalRequest(
            orderProductDtos.stream().map(this::map).toList()
        );
    }

    private ProductInternal map(OrderProductDto orderProductDto) {
        return new ProductInternal(
            orderProductDto.productId(),
            orderProductDto.quantity()
        );
    }
}