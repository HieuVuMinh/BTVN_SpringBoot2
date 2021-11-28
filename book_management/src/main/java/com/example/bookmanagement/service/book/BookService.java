package com.example.bookmanagement.service.book;

import com.example.bookmanagement.model.Book;
import com.example.bookmanagement.repository.book.IBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookService implements IBookService{

    @Autowired
    private IBookRepository bookRepository;

    @Override
    public Iterable<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Optional<Book> findById(Long id) {
        return bookRepository.findById(id);
    }

    @Override
    public Book save(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public Iterable<Book> getAllSortByTitle() {
        return bookRepository.getAllSortByTitle();
    }

    @Override
    public Iterable<Book> findCloseEnoughByTitle(String title) {
        return bookRepository.findCloseEnoughByTitle("%" + title + "%");
    }

    @Override
    public Iterable<Book> findAllByAmountEqualZero() {
        return bookRepository.findAllByAmountEqualZero();
    }

    @Override
    public Optional<Book> findByTitle(String title) {
        return bookRepository.findByTitle(title);
    }
}
