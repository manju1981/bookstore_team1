create table orders
(
    id int not null,
    book_id INT not null,
    quantity int not null default 1,
    total_amount int not null,
    created_at TIMESTAMP DEFAULT now(),
    primary key (id),
    CONSTRAINT fk_orders_books
        FOREIGN KEY(book_id)
            REFERENCES books(id)
)