CREATE TABLE locations
(
    id           UUID PRIMARY KEY DEFAULT GEN_RANDOM_UUID(),
    code         VARCHAR(255) NOT NULL UNIQUE,

    warehouse_id UUID         NOT NULL REFERENCES warehouses (id) ON DELETE CASCADE,

    UNIQUE (code, warehouse_id)
);
