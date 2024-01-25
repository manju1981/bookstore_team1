package com.idfc.bootcamp.bookstore;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    @Query(value = "INSERT INTO cart(book_id,quantity) VALUES (:bookId, :quantity) ON CONFLICT(book_id) DO UPDATE SET quantity = EXCLUDED.quantity RETURNING id", nativeQuery = true)
    long saveCartItem(int bookId, int quantity);

}
