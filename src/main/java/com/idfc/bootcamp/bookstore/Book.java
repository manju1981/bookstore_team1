package com.idfc.bootcamp.bookstore;

import jakarta.persistence.*;

@Entity
@Table(name="books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    protected String title;

    protected String author;
    protected  String description;

    protected double ratings;
    protected int price;

    private String image_url;

    public Book(String title, String author, String description, double ratings, int price, String image_url) {
        this.title = title;
        this.author = author;
        this.description = description;
        this.ratings = ratings;
        this.price = price;
        this.image_url = image_url;
    }
    public Book(long id, String title, String author, String description, double ratings, int price) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.description = description;
        this.ratings = ratings;
        this.price = price;
    }


    public Book() {
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }
    public String getDescription() {return description;}

    public double getRatings(){
        return ratings;
    }
    public int getPrice() {
        return price;
    }

    public String getImage_url() {
        return image_url;
    }
}
