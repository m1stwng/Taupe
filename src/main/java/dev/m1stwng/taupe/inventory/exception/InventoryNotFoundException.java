package dev.m1stwng.taupe.inventory.exception;

import dev.m1stwng.taupe.common.exception.BaseException;
import org.springframework.http.HttpStatus;

import java.util.UUID;

public class InventoryNotFoundException extends BaseException {
    public InventoryNotFoundException(UUID id) {
        super(HttpStatus.NOT_FOUND, "Inventory was not found", "Inventory with id %s was not found".formatted(id));
    }
}
