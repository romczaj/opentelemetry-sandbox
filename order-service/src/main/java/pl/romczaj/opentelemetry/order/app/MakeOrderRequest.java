package pl.romczaj.opentelemetry.order.app;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.util.List;

public record MakeOrderRequest(
    @NotNull Long customerId,
    @NotEmpty List<@Valid OrderProductDto> orderProducts
) {

    public record OrderProductDto(
        @NotNull Long productId,
        @NotNull Integer quantity
    ) {

    }
}
