package dev.m1stwng.taupe.product.mapper;

import dev.m1stwng.taupe.product.dto.response.ProductResponse;
import dev.m1stwng.taupe.product.entity.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductResponse toResponse(Product product);
}
