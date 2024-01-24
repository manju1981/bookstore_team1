package com.idfc.bootcamp.bookstore;

import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BookService {
    private final BookRepository bookRepository;
    private final BookDetailsRepository bookDetailsRepository;


    @Autowired
    public BookService(BookRepository bookRepository, BookDetailsRepository bookDetailsRepository) {
        this.bookRepository = bookRepository;
        this.bookDetailsRepository = bookDetailsRepository;
    }
    public BookListResponse fetchBooks(String search, int pageNumber, int pageSize) {

        List<Book> list;
        long totalNumberOfBooks;
        Pageable pageable =  PageRequest.of(pageNumber, pageSize);
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

}
