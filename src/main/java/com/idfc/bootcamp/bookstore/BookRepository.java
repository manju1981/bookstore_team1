package com.idfc.bootcamp.bookstore;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>, PagingAndSortingRepository<Book, Long> {


    List<Book> findByTitleLikeIgnoreCaseOrAuthorLikeIgnoreCaseOrDescriptionLikeIgnoreCase(String titleSearch, String authorSearch, String descriptionSearch , Pageable pageable);
    List<Book> findBy( Pageable pageable);


    long countByTitleLikeIgnoreCaseOrAuthorLikeIgnoreCaseOrDescriptionLikeIgnoreCase(String titleSearch, String authorSearch, String descriptionSearch);
}
