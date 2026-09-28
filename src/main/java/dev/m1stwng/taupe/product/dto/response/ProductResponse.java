package dev.m1stwng.taupe.product.dto.response;

import java.util.UUID;

public record ProductResponse(UUID id, String sku, String name) {
}
