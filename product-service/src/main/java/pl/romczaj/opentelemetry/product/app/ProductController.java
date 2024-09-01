package pl.romczaj.opentelemetry.product.app;

import io.micrometer.tracing.annotation.NewSpan;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.romczaj.opentelemetry.internal.ProductValidateInternalRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/product")
public class ProductController {

    private final ProductService productService;

    @PostMapping("/validate")
    @NewSpan("controller-validate-products")
    public void validateProducts(@RequestBody @Valid ProductValidateInternalRequest productValidateInternalRequest){
        productService.validateProducts(productValidateInternalRequest);
    }
}
