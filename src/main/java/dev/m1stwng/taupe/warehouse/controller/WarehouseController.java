package dev.m1stwng.taupe.warehouse.controller;

import dev.m1stwng.taupe.warehouse.dto.request.CreateLocationRequest;
import dev.m1stwng.taupe.warehouse.dto.request.CreateWarehouseRequest;
import dev.m1stwng.taupe.warehouse.dto.request.UpdateWarehouseRequest;
import dev.m1stwng.taupe.warehouse.dto.response.LocationResponse;
import dev.m1stwng.taupe.warehouse.dto.response.WarehouseResponse;
import dev.m1stwng.taupe.warehouse.service.LocationService;
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

    private final LocationService locationService;
    private final WarehouseService warehouseService;

    @GetMapping
    public ResponseEntity<List<WarehouseResponse>> findAll() {
        return ResponseEntity.ok(warehouseService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<WarehouseResponse> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(warehouseService.findById(id));
    }

    @GetMapping("/{id}/locations")
    public ResponseEntity<List<LocationResponse>> findAllLocationsByWarehouseId(@PathVariable UUID id) {
        return ResponseEntity.ok(locationService.findAllByWarehouseId(id));
    }

    @PostMapping
    public ResponseEntity<WarehouseResponse> create(@RequestBody @Valid CreateWarehouseRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(warehouseService.create(request));
    }

    @PostMapping("/{id}/locations")
    public ResponseEntity<LocationResponse> createLocation(
            @PathVariable UUID id,
            @RequestBody @Valid CreateLocationRequest request
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(locationService.create(id, request));
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
