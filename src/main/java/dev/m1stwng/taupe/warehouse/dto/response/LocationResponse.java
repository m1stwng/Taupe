package dev.m1stwng.taupe.warehouse.dto.response;

import java.util.UUID;

public record LocationResponse(UUID id, UUID warehouseId, String code) {
}
