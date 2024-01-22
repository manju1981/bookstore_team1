package com.idfc.bootcamp.bookstore;

import org.springframework.data.domain.Sort;
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
                                @RequestParam int offset,
                                @RequestParam(required=false) int limit
                                ){
        boolean b = limit <= 0;
        if (b) {
            offset = 0;
            limit =20;
        }

        return bookService.fetchBooks(search,offset,limit);
    }
}
