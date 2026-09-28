package dev.m1stwng.taupe.product.dto.request;

import jakarta.validation.constraints.NotBlank;

public record CreateProductRequest(@NotBlank String sku, @NotBlank String name) {
}
