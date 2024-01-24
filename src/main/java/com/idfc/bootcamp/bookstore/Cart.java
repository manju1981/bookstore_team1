package com.idfc.bootcamp.bookstore;

import jakarta.persistence.*;

@Entity
@Table(name="cart")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int book_id;
    private int quantity;
    public Cart(int book_id, int quantity) {
        this.book_id=book_id;
        this.quantity=quantity;
    }


    public Cart() {

    }
}
