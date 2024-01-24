package com.idfc.bootcamp.bookstore;

import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class BookDetails {
    @Id
    private long id;
    private String title;
    private String author;
    private  String description;
    @JsonSetter(nulls = Nulls.SKIP)
    private double ratings = 0.0;
    @JsonSetter(nulls = Nulls.SKIP)
    private int price = 0;
    @JsonSetter(nulls = Nulls.SKIP)
    private int stock = 0;
    @JsonSetter(nulls = Nulls.AS_EMPTY)
    private int quantity = 0;

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

    public void setId(long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setRatings(double ratings) {
        this.ratings = ratings;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void setId(Long id) {
        this.id = id;
    }


}
