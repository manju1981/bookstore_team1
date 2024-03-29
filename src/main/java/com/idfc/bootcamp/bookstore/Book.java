package com.idfc.bootcamp.bookstore;

import jakarta.persistence.*;

@Entity
@Table(name="books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String author;
    private  String description;

    private double ratings;
    private int price;

    private String image_url;

    public Book(String title, String author, String description, double ratings, int price, String image_url) {
        this.title = title;
        this.author = author;
        this.description = description;
        this.ratings = ratings;
        this.price = price;
        this.image_url = image_url;
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
