package com.idfc.bootcamp.bookstore;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BookController {
    BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @CrossOrigin()
    @GetMapping("books")
    public List<Book> listBooks(@RequestParam(required=false) String search,
                                @RequestParam(required=false) Integer pageNumber,
                                @RequestParam(required=false) Integer pageSize
                                ){

        if (pageNumber == null || pageSize == null) {
            pageNumber = 0;
            pageSize =20;
        }

        return bookService.fetchBooks(search, pageNumber,pageSize);
    }
}
