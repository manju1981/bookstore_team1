package com.idfc.bootcamp.bookstore;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @CrossOrigin()
    @GetMapping("book/{id}")
    public ResponseEntity<BookDetails> getBook(@PathVariable long id){
        BookDetails bookDetails = bookService.getBookById(id);
        if(bookDetails==null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(bookDetails, HttpStatus.OK);
    }

}
