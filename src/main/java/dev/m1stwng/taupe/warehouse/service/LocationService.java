package dev.m1stwng.taupe.warehouse.service;

import dev.m1stwng.taupe.warehouse.dto.request.CreateLocationRequest;
import dev.m1stwng.taupe.warehouse.dto.response.LocationResponse;
import dev.m1stwng.taupe.warehouse.entity.Location;
import dev.m1stwng.taupe.warehouse.entity.Warehouse;
import dev.m1stwng.taupe.warehouse.exception.DuplicateLocationException;
import dev.m1stwng.taupe.warehouse.exception.LocationNotFoundException;
import dev.m1stwng.taupe.warehouse.exception.WarehouseNotFoundException;
import dev.m1stwng.taupe.warehouse.mapper.LocationMapper;
import dev.m1stwng.taupe.warehouse.repository.LocationRepository;
import dev.m1stwng.taupe.warehouse.repository.WarehouseRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class LocationService {

    private final LocationMapper locationMapper;
    private final LocationRepository locationRepository;
    private final WarehouseRepository warehouseRepository;

    public List<LocationResponse> findAllByWarehouseId(UUID warehouseId) {
        return locationRepository.findAllByWarehouseId(warehouseId)
                .stream()
                .map(locationMapper::toResponse)
                .toList();
    }

    public LocationResponse findById(UUID id) {
        return locationRepository.findById(id)
                .map(locationMapper::toResponse)
                .orElseThrow(() -> new LocationNotFoundException(id));
    }

    public LocationResponse create(UUID warehouseId, CreateLocationRequest request) {
        final Warehouse warehouse = warehouseRepository.findById(warehouseId)
                .orElseThrow(() -> new WarehouseNotFoundException(warehouseId));

        if (locationRepository.existsByCodeAndWarehouseId(request.code(), warehouseId)) {
            throw new DuplicateLocationException(request.code(), warehouseId);
        }

        final Location location = Location.builder()
                .code(request.code())
                .warehouse(warehouse)
                .build();

        return locationMapper.toResponse(locationRepository.save(location));
    }
}
