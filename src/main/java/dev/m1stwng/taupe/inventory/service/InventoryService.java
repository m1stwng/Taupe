package dev.m1stwng.taupe.inventory.service;

import dev.m1stwng.taupe.inventory.dto.response.InventoryResponse;
import dev.m1stwng.taupe.inventory.exception.InventoryNotFoundException;
import dev.m1stwng.taupe.inventory.mapper.InventoryMapper;
import dev.m1stwng.taupe.inventory.repository.InventoryRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryMapper inventoryMapper;
    private final InventoryRepository inventoryRepository;

    public List<InventoryResponse> findAll() {
        return inventoryRepository.findAll()
                .stream()
                .map(inventoryMapper::toResponse)
                .toList();
    }

    public InventoryResponse findById(UUID id) {
        return inventoryRepository.findById(id)
                .map(inventoryMapper::toResponse)
                .orElseThrow(() -> new InventoryNotFoundException(id));
    }
}
