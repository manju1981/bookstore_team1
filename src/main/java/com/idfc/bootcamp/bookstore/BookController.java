package com.idfc.bootcamp.bookstore;

import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;


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
                                                      @RequestParam(required=false) Integer pageSize,
                                                      @RequestParam(required = false) String sortBy,
                                                      @RequestParam(required = false) String order){

        if (pageNumber == null) pageNumber = 0;
        if( pageSize == null) pageSize = 20;

       BookListResponse bookListResponse = bookService.fetchBooks(search,pageNumber,pageSize,sortBy,order);

        return  new ResponseEntity<>(bookListResponse, HttpStatus.OK);
    }

    @CrossOrigin()
    @GetMapping("book/{id}")
    public ResponseEntity<BookDetails> getBook(@PathVariable long id){
        BookDetails bookDetails = bookService.getBookById(id);
        if(bookDetails==null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(bookDetails, HttpStatus.OK);
    }
    @CrossOrigin()
    @GetMapping("/cart")
    public ResponseEntity<List<CartItems>> getCartDetails(){
        List<CartItems> cartDetails = bookService.getCartDetails();
        if(cartDetails==null)
            return new ResponseEntity<>(HttpStatus.OK);
        return new ResponseEntity<>(cartDetails, HttpStatus.OK);
    }
    @CrossOrigin()
    @PostMapping("/cart")
    public ResponseEntity<String> getCartDetails(@RequestBody Cart cart) {
        return bookService.saveCartItems(cart);
    }


}
