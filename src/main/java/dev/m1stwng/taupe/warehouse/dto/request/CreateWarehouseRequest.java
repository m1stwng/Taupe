package dev.m1stwng.taupe.warehouse.dto.request;

import jakarta.validation.constraints.NotBlank;

public record CreateWarehouseRequest(@NotBlank String code, @NotBlank String name) {
}
