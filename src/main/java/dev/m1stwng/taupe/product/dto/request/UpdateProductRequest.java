package dev.m1stwng.taupe.product.dto.request;

import jakarta.validation.constraints.NotBlank;

public record UpdateProductRequest(@NotBlank String sku, @NotBlank String name) {
}
