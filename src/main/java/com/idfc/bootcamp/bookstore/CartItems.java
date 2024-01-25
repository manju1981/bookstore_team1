package com.idfc.bootcamp.bookstore;

import jakarta.persistence.Entity;

@Entity
public class CartItems extends Book {

    protected long id;
    protected int quantity;

    public CartItems(long id, String title, String author, String description, double ratings, int price, int quantity) {
        super(id, title, author, description, ratings, price);
        this.quantity = quantity;
    }

    public CartItems() {

    }

    public CartItems(long id, int quantity) {
        this.id = id;
        this.quantity = quantity;
    }
}
