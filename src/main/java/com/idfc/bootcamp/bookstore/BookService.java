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

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    public List<Book> fetchBooks(String search, int pageNumber, int pageSize) {

        List<Book> list;
        Pageable pageable =  PageRequest.of(pageNumber, pageSize);
        if(!Strings.isBlank(search)) {
            String searchString = '%' + search +'%';

          list =   bookRepository
                    .findByTitleLikeIgnoreCaseOrAuthorLikeIgnoreCaseOrDescriptionLikeIgnoreCase(
                            searchString,
                            searchString,
                            searchString,
                            pageable);
        } else {
     list =   bookRepository.findBy(pageable);
        }
        return  list;
    }

}
