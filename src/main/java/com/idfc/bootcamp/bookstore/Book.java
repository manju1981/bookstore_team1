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

    public Book(String title, String author, String description, double ratings) {
        this.title = title;
        this.author = author;
        this.description = description;
        this.ratings = ratings;
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

}
