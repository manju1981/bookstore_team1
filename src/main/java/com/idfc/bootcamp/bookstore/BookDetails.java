package com.idfc.bootcamp.bookstore;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class BookDetails {
    @Id
    private long id;
    private String title;
    private String author;
    private  String description;
    private double ratings;
    private int price ;
    private int stock ;
    private int quantity;

    public BookDetails(long id, String title, String author, String description, double ratings, int price, int stock, int quantity) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.description = description;
        this.ratings = ratings;
        this.price = price;
        this.stock = stock;
        this.quantity = quantity;
    }

    public BookDetails() {

    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getDescription() {
        return description;
    }

    public double getRatings() {
        return ratings;
    }

    public int getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    public int getQuantity() {
        return quantity;
    }


}
