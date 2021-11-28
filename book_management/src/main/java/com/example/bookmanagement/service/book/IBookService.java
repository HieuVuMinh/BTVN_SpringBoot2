package com.example.bookmanagement.service.book;

import com.example.bookmanagement.model.Book;
import com.example.bookmanagement.service.GeneralService;

import java.util.Optional;

public interface IBookService extends GeneralService<Book> {

    Iterable<Book> getAllSortByTitle();
    Iterable<Book> findCloseEnoughByTitle(String title);
    Iterable<Book> findAllByAmountEqualZero();
    Optional<Book> findByTitle(String title);

}
