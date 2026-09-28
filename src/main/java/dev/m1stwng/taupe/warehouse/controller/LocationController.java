package dev.m1stwng.taupe.warehouse.controller;

import dev.m1stwng.taupe.warehouse.dto.response.LocationResponse;
import dev.m1stwng.taupe.warehouse.service.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/locations")
@RequiredArgsConstructor
public class LocationController {

    private final LocationService locationService;

    @GetMapping("/{id}")
    public ResponseEntity<LocationResponse> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(locationService.findById(id));
    }
}
