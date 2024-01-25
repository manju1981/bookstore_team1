package com.idfc.bootcamp.bookstore;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name="orders")
@NoArgsConstructor
public class Order {
    @Id
    private long id;
    private long book_id;
    private int quantity;
    private int totalAmount;
    private Date createdAt;
    private Country country;

    public Order(long id, long book_id, int quantity, int totalAmount, Date createdAt, Country country) {
        this.id = id;
        this.book_id = book_id;
        this.quantity = quantity;
        this.totalAmount = totalAmount;
        this.createdAt = createdAt;
        this.country = country;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
