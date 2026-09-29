CREATE TABLE inventories
(
    id          UUID PRIMARY KEY DEFAULT GEN_RANDOM_UUID(),
    quantity    INTEGER NOT NULL CHECK (quantity >= 0),

    product_id  UUID    NOT NULL REFERENCES products (id),
    location_id UUID    NOT NULL REFERENCES locations (id),

    UNIQUE (product_id, location_id)
);
