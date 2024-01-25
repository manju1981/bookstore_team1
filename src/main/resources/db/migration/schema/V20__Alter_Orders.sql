ALTER TABLE orders ALTER COLUMN id TYPE bigint;
ALTER TABLE orders ALTER COLUMN book_id TYPE bigint;
ALTER TABLE orders ADD country_id bigint CONSTRAINT fk_country_id FOREIGN KEY (country_id) REFERENCES country (country_id);
