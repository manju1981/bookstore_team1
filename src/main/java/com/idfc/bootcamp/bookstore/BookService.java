package com.idfc.bootcamp.bookstore;

import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class BookService {
    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    public List<Book> fetchBooks(String search) {
        if(!Strings.isBlank(search)) {
            return bookRepository.findByTitleOrAuthorOrDescription(search, search, search);
        }
        return bookRepository.findAll();
    }
}
