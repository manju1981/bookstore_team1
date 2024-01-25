package com.idfc.bootcamp.bookstore;


import lombok.NoArgsConstructor;

import java.util.List;


@NoArgsConstructor
public class BookListResponse {
    private List<Book> books ;
    private long totalNoOfBooks;


    public BookListResponse(List<Book> books, long totalNoOfBooks) {
        this.books = books;
        this.totalNoOfBooks = totalNoOfBooks;
    }

    public List<Book> getBooks() {
        return books;
    }

    public long getTotalNoOfBooks() {
        return totalNoOfBooks;
    }
}
