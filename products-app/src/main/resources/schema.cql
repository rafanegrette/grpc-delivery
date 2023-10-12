-- =============================================================================
-- product
-- =============================================================================
CREATE TABLE IF NOT EXISTS product (
                                       id uuid PRIMARY KEY,
                                       available boolean,
                                       category text,
                                       discount float,
                                       name text,
                                       price int,
                                       restaurantid int
);

CREATE INDEX IF NOT EXISTS indexrestaurant ON product (restaurantid);