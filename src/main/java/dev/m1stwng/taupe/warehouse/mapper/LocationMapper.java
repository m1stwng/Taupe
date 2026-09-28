package dev.m1stwng.taupe.warehouse.mapper;

import dev.m1stwng.taupe.warehouse.dto.response.LocationResponse;
import dev.m1stwng.taupe.warehouse.entity.Location;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface LocationMapper {

    @Mapping(target = "warehouseId", source = "warehouse.id")
    LocationResponse toResponse(Location location);
}
