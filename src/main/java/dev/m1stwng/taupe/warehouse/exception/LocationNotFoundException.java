package dev.m1stwng.taupe.warehouse.exception;

import dev.m1stwng.taupe.common.exception.BaseException;
import org.springframework.http.HttpStatus;

import java.util.UUID;

public class LocationNotFoundException extends BaseException {
    public LocationNotFoundException(UUID id) {
        super(HttpStatus.NOT_FOUND, "Location was not found", "Location with id %s was not found".formatted(id));
    }
}
