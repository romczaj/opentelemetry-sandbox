package pl.romczaj.opentelemetry.internal;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.util.List;

public record ProductValidateInternalRequest(
    @NotEmpty List<@Valid ProductInternal> products
) {
    public record ProductInternal(
        @NotNull Long productId,
        @NotNull Integer requestQuantity
    ) {
    }
}
