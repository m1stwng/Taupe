package dev.m1stwng.taupe.inventory.mapper;

import dev.m1stwng.taupe.inventory.dto.response.InventoryResponse;
import dev.m1stwng.taupe.inventory.entity.Inventory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface InventoryMapper {

    @Mapping(target = "productId", source = "product.id")
    @Mapping(target = "locationId", source = "location.id")
    InventoryResponse toResponse(Inventory inventory);
}
