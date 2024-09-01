package pl.romczaj.opentelemetry.order.app;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.PostExchange;
import pl.romczaj.opentelemetry.internal.ProductValidateInternalRequest;

interface ProductServiceClient {
    @PostExchange(url = "/api/product/validate")
    void validateProducts(@RequestBody @Valid ProductValidateInternalRequest order);
}
