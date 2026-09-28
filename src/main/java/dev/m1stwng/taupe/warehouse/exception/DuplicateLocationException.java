package dev.m1stwng.taupe.warehouse.exception;

import dev.m1stwng.taupe.common.exception.BaseException;
import org.springframework.http.HttpStatus;

import java.util.UUID;

public class DuplicateLocationException extends BaseException {
    public DuplicateLocationException(String code, UUID warehouseId) {
        super(
                HttpStatus.CONFLICT,
                "Duplicate location",
                "Location with code %s and warehouse id %s already exists".formatted(code, warehouseId)
        );
    }
}
