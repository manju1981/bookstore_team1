package com.idfc.bootcamp.bookstore;

import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BookService {
    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    public List<Book> fetchBooks(String search, int offset, int limit) {
        if (limit <= 0) {
            limit = 20;
        }
        Pageable pageable =  PageRequest.of(offset, limit);
        Page<Book>  page= null;
        if(!Strings.isBlank(search)) {
            String searchString = '%' + search +'%';

           page =   bookRepository
                    .findByTitleLikeIgnoreCaseOrAuthorLikeIgnoreCaseOrDescriptionLikeIgnoreCase(
                            searchString,
                            searchString,
                            searchString,
                            pageable);
        } else {
            page = bookRepository.findAll(pageable);
        }
        System.out.println(page.getContent());
        if (Objects.nonNull(page)){
            return  page.getContent();
        }
        return List.of();

    }
}
