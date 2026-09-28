package dev.m1stwng.taupe.warehouse.exception;

import dev.m1stwng.taupe.common.exception.BaseException;
import org.springframework.http.HttpStatus;

import java.util.UUID;

public class WarehouseNotFoundException extends BaseException {
    public WarehouseNotFoundException(UUID id) {
        super(HttpStatus.NOT_FOUND, "Warehouse was not found", "Warehouse with id %s was not found".formatted(id));
    }
}
