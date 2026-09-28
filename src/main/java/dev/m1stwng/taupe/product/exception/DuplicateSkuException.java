package dev.m1stwng.taupe.product.exception;

import dev.m1stwng.taupe.common.exception.BaseException;
import org.springframework.http.HttpStatus;

public class DuplicateSkuException extends BaseException {
    public DuplicateSkuException(String sku) {
        super(HttpStatus.CONFLICT, "Duplicate SKU", "Product with SKU %s already exists".formatted(sku));
    }
}
