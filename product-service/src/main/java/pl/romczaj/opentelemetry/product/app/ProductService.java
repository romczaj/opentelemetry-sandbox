package pl.romczaj.opentelemetry.product.app;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import pl.romczaj.opentelemetry.internal.MockDelay;
import pl.romczaj.opentelemetry.internal.ProductValidateInternalRequest;
import pl.romczaj.opentelemetry.internal.ProductValidateInternalRequest.ProductInternal;

@Component
@Slf4j
public class ProductService {

    public void validateProducts(ProductValidateInternalRequest productValidateInternalRequest) {
        log.info("Validate quantity of products");
        MockDelay.ofMilliseconds(300);
        productValidateInternalRequest.products().forEach(this::validateProductQuantity);
    }

    private void validateProductQuantity(ProductInternal productInternal) {
        if (productInternal.requestQuantity() >= 100) {
            throw new RuntimeException(String.format("Quantity of product %s is too high", productInternal.productId()));
        }
    }
}
