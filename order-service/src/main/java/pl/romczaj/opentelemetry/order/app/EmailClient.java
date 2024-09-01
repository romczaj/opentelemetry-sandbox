package pl.romczaj.opentelemetry.order.app;

import io.micrometer.tracing.annotation.NewSpan;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import pl.romczaj.opentelemetry.internal.MockDelay;

@Component
@Slf4j
public class EmailClient {

    @Async(value = "mailExecutor")
    @NewSpan
    public void sendConfirmationEmail(Long customerId) {
        log.info("Sending confirmation email to customer {}", customerId);
        MockDelay.ofMilliseconds(100);
    }
}
