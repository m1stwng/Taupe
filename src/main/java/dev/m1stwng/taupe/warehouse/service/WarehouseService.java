package dev.m1stwng.taupe.warehouse.service;


import dev.m1stwng.taupe.warehouse.dto.request.CreateWarehouseRequest;
import dev.m1stwng.taupe.warehouse.dto.request.UpdateWarehouseRequest;
import dev.m1stwng.taupe.warehouse.dto.response.WarehouseResponse;
import dev.m1stwng.taupe.warehouse.entity.Warehouse;
import dev.m1stwng.taupe.warehouse.exception.DuplicateCodeException;
import dev.m1stwng.taupe.warehouse.exception.WarehouseNotFoundException;
import dev.m1stwng.taupe.warehouse.mapper.WarehouseMapper;
import dev.m1stwng.taupe.warehouse.repository.WarehouseRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class WarehouseService {

    private final WarehouseMapper warehouseMapper;
    private final WarehouseRepository warehouseRepository;

    public List<WarehouseResponse> findAll() {
        return warehouseRepository.findAll()
                .stream()
                .map(warehouseMapper::toResponse)
                .toList();
    }

    public WarehouseResponse findById(UUID id) {
        return warehouseRepository.findById(id)
                .map(warehouseMapper::toResponse)
                .orElseThrow(() -> new WarehouseNotFoundException(id));
    }

    public WarehouseResponse create(CreateWarehouseRequest request) {
        if (warehouseRepository.existsByCode(request.code())) {
            throw new DuplicateCodeException(request.code());
        }

        final Warehouse warehouse = Warehouse.builder()
                .code(request.code())
                .name(request.name())
                .build();

        return warehouseMapper.toResponse(warehouseRepository.save(warehouse));
    }

    public WarehouseResponse update(UUID id, UpdateWarehouseRequest request) {
        final Warehouse warehouse = warehouseRepository.findById(id)
                .orElseThrow(() -> new WarehouseNotFoundException(id));

        if (!warehouse.getCode().equals(request.code()) && warehouseRepository.existsByCode(request.code())) {
            throw new DuplicateCodeException(request.code());
        }

        warehouse.setCode(request.code());
        warehouse.setName(request.name());

        return warehouseMapper.toResponse(warehouseRepository.save(warehouse));
    }

    public void delete(UUID id) {
        if (!warehouseRepository.existsById(id)) {
            throw new WarehouseNotFoundException(id);
        }

        warehouseRepository.deleteById(id);
    }
}
