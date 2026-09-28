package dev.m1stwng.taupe.warehouse.controller;

import dev.m1stwng.taupe.warehouse.dto.request.CreateWarehouseRequest;
import dev.m1stwng.taupe.warehouse.dto.request.UpdateWarehouseRequest;
import dev.m1stwng.taupe.warehouse.dto.response.WarehouseResponse;
import dev.m1stwng.taupe.warehouse.service.WarehouseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/warehouses")
@RequiredArgsConstructor
public class WarehouseController {

    private final WarehouseService warehouseService;

    @GetMapping
    public ResponseEntity<List<WarehouseResponse>> findAll() {
        return ResponseEntity.ok(warehouseService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<WarehouseResponse> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(warehouseService.findById(id));
    }

    @PostMapping
    public ResponseEntity<WarehouseResponse> create(@RequestBody @Valid CreateWarehouseRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(warehouseService.create(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<WarehouseResponse> update(
            @PathVariable UUID id,
            @RequestBody @Valid UpdateWarehouseRequest request
    ) {
        return ResponseEntity.ok(warehouseService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        warehouseService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
