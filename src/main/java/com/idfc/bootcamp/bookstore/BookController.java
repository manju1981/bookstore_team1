package com.idfc.bootcamp.bookstore;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
public class BookController {
    BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @CrossOrigin()
    @GetMapping("books")
    public ResponseEntity<BookListResponse> listBooks(@RequestParam(required=false) String search,
                                                      @RequestParam(required=false) Integer pageNumber,
                                                      @RequestParam(required=false) Integer pageSize
                                ){

        if (Objects.isNull(pageNumber) || Objects.isNull(pageSize)) {
            pageNumber = 0;
            pageSize =20;
        }

       BookListResponse bookListResponse = bookService.fetchBooks(search,pageNumber,pageSize);

        return  new ResponseEntity<>(bookListResponse, HttpStatus.OK);
    }
}
