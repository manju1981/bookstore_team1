package com.idfc.bootcamp.bookstore;

import jakarta.persistence.*;

@Entity
@Table(name="inventory")
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int book_id;

    private int stock;
    public Inventory(int book_id, int price, int stock) {
        this.book_id=book_id;
        this.stock=stock;
    }

    public Inventory() {

    }

    public int getStock() {
        return stock;
    }

    public int getBook_id() {
        return book_id;
    }
}
