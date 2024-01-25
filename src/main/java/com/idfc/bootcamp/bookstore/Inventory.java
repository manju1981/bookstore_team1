package com.idfc.bootcamp.bookstore;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "inventory")
public class Inventory {
    @Id
    private long id;
    private long bookId;
    private int stock;

    public Inventory(Long bookId, int stock) {
        this.bookId = bookId;
        this.stock = stock;
    }

    public int getStock() {
        return this.stock;
    }
}
