package dev.m1stwng.taupe.warehouse.repository;

import dev.m1stwng.taupe.warehouse.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface LocationRepository extends JpaRepository<Location, UUID> {

    List<Location> findAllByWarehouseId(UUID warehouseId);

    boolean existsByCodeAndWarehouseId(String code, UUID warehouseId);
}
