package com.idfc.bootcamp.bookstore;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BookDetailsRepository extends JpaRepository<BookDetails, Long> {

    @Query(value = "SELECT b.id, b.title, b.author, b.description, b.ratings, b.price, COALESCE( i.stock, 0) as stock, COALESCE (c.quantity, 0) as quantity FROM books as b INNER JOIN inventory as i\n" +
            "    ON b.id=i.book_id LEFT JOIN cart as c ON b.id=c.book_id WHERE b.id=:id", nativeQuery = true)
    BookDetails findById(long id);
}
