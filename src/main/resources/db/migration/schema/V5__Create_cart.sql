create table cart
(
    id bigint generated by default as identity,
    book_id INT not null UNIQUE,
    quantity int not null default 1,
    primary key (id),
    CONSTRAINT fk_books_cart
        FOREIGN KEY(book_id)
            REFERENCES books(id)
)