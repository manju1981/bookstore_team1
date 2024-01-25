package com.idfc.bootcamp.bookstore;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BookService {
    private final BookRepository bookRepository;
    private final BookDetailsRepository bookDetailsRepository;
    private final CartItemsRepository cartItemsRepository;
    private CartRepository cartRepository;

    @Autowired
    public BookService(BookRepository bookRepository, BookDetailsRepository bookDetailsRepository, CartItemsRepository cartItemsRepository, CartRepository cartRepository) {
        this.bookRepository = bookRepository;
        this.bookDetailsRepository = bookDetailsRepository;
        this.cartItemsRepository = cartItemsRepository;
        this.cartRepository = cartRepository;
    }
    public BookListResponse fetchBooks(String search, int pageNumber, int pageSize, String sortBy, String order) {

        List<Book> list;
        long totalNumberOfBooks;
        Sort sort = Sort.unsorted();
        if (order == null) {
            order = "asc";
        }
        if (sortBy != null) {
             sort = order.equalsIgnoreCase(Sort.Direction.ASC.name()) ?
                    Sort.by(sortBy).ascending(): Sort.by(sortBy).descending();
        }
        Pageable pageable =  PageRequest.of(pageNumber, pageSize,sort);


        if(!Strings.isBlank(search)) {
            String searchString = '%' + search +'%';
            list =   bookRepository
                    .findByTitleLikeIgnoreCaseOrAuthorLikeIgnoreCaseOrDescriptionLikeIgnoreCase(
                            searchString,
                            searchString,
                            searchString,
                            pageable);
            totalNumberOfBooks = bookRepository.countByTitleLikeIgnoreCaseOrAuthorLikeIgnoreCaseOrDescriptionLikeIgnoreCase(
                    searchString,
                    searchString,
                    searchString
            );

        } else {
            list = bookRepository.findBy(pageable);
            totalNumberOfBooks = bookRepository.count();

        }

        return new BookListResponse(list,totalNumberOfBooks);
    }

    public BookDetails getBookById(long id){
        return bookDetailsRepository.findById(id);
    }

    public List<CartItems> getCartDetails() {
        return cartItemsRepository.findAll();
    }

    public ResponseEntity<String> saveCartItems(Cart cart) {
        try {
            cartRepository.saveCartItem(cart.getBook_id(), cart.getQuantity());
            return new ResponseEntity<>("Cart updated successfully", HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>("Failed to update cart", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
