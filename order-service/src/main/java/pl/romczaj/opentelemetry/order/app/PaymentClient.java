package pl.romczaj.opentelemetry.order.app;

import io.micrometer.tracing.annotation.NewSpan;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import pl.romczaj.opentelemetry.internal.MockDelay;

@Component
@Slf4j
public class PaymentClient {

    @Async("paymentExecutor")
    @NewSpan
    public void startPayment(Long customerId){
        log.info("Start processing payment for customer {}", customerId);
        MockDelay.ofMilliseconds(150);
    }
}
