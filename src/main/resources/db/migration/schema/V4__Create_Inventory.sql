create table inventory
(
    id     bigint generated by default as identity,
    book_id  INT not null,
    price INT,
    stock int not null default 0,
    primary key (id),
    CONSTRAINT fk_inventory_books
        FOREIGN KEY(book_id)
            REFERENCES books(id)
)