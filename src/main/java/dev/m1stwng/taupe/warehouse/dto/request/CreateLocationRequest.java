package dev.m1stwng.taupe.warehouse.dto.request;

import jakarta.validation.constraints.NotBlank;

public record CreateLocationRequest(@NotBlank String code) {
}
