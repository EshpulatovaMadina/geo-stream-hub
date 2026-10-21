CREATE TABLE IF NOT EXISTS couriers (
                                        id BIGSERIAL PRIMARY KEY,
                                        name VARCHAR(255) NOT NULL,
    phone VARCHAR(50) NOT NULL,
    status VARCHAR(50) NOT NULL
    );

CREATE TABLE IF NOT EXISTS delivery_orders (
                                               id BIGSERIAL PRIMARY KEY,
                                               customer_name VARCHAR(255) NOT NULL,
    delivery_address TEXT NOT NULL,
    courier_id BIGINT REFERENCES couriers(id),
    status VARCHAR(50) NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
                             );

CREATE TABLE IF NOT EXISTS location_points (
                                               id BIGSERIAL PRIMARY KEY,
                                               courier_id VARCHAR(50) NOT NULL,
    latitude DOUBLE PRECISION NOT NULL,
    longitude DOUBLE PRECISION NOT NULL,
    timestamp TIMESTAMP WITH TIME ZONE NOT NULL
                            );