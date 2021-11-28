package com.example.bookmanagement.repository.book;

import com.example.bookmanagement.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IBookRepository extends JpaRepository<Book, Long> {

    @Query(value = "select * from book as b " +
            "order by b.title asc", nativeQuery = true)
    Iterable<Book> getAllSortByTitle();

    @Query(value = "select * from book as b " +
            "where b.title like ?1", nativeQuery = true)
    Iterable<Book> findCloseEnoughByTitle(String title);

    @Query(value = "select * from book b " +
            "join book_inventories bi on bi.book_book_id = b.book_id " +
            "join inventory i on bi.inventories_id = i.id " +
            "where i.amount = 0", nativeQuery = true)
    Iterable<Book> findAllByAmountEqualZero();

    Optional<Book> findByTitle(String title);
}
