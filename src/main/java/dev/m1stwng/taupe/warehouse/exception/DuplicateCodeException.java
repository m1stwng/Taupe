package dev.m1stwng.taupe.warehouse.exception;

import dev.m1stwng.taupe.common.exception.BaseException;
import org.springframework.http.HttpStatus;

public class DuplicateCodeException extends BaseException {
    public DuplicateCodeException(String code) {
        super(HttpStatus.CONFLICT, "Duplicate code", "Warehouse with code %s already exists".formatted(code));
    }
}
