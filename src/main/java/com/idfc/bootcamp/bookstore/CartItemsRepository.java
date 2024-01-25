package com.idfc.bootcamp.bookstore;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CartItemsRepository extends JpaRepository<CartItems, Long> {

    @Query(value = "SELECT b.id, b.title, b.author, b.description, b.ratings, b.price, b.image_url, c.quantity FROM books as b INNER JOIN cart as c ON b.id=c.book_id", nativeQuery = true)
    List<CartItems> findAll();
}
