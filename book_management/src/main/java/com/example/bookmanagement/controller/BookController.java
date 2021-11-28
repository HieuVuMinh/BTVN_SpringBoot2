package com.example.bookmanagement.controller;

import com.example.bookmanagement.model.Book;
import com.example.bookmanagement.model.Inventory;
import com.example.bookmanagement.service.book.IBookService;
import com.example.bookmanagement.service.inventory.IInventoryService;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/books")
public class BookController {

    Faker faker = new Faker();

    public static Random random = new Random();

    @Autowired
    private IInventoryService inventoryService;

    @Autowired
    private IBookService bookService;

    // 1
    @GetMapping("/fake_data")
    public void fakeData() {
        for (int i = 0; i < 100; i++) {
            Book book = fakeDataBook();
            List<Inventory> inventories = fakeDataInventory();
            for (Inventory inventory : inventories) {
                inventoryService.save(inventory);
            }
            book.setInventories(inventories);
            bookService.save(book);
        }
    }

    // 2
    @GetMapping("/sort")
    public Iterable<Book> sortBooks() {
        return bookService.getAllSortByTitle();
    }

    // 3
    @GetMapping("/{title}")
    public Iterable<Book> findByTitle(@PathVariable String title) {
        return bookService.findCloseEnoughByTitle(title);
    }

    // 4
    @GetMapping("/amount")
    public Iterable<Book> findAllByAmountEqualZero() {
        return bookService.findAllByAmountEqualZero();
    }

    // 5
    @GetMapping("/buy")
    public String buyBook() {
        Iterable<Book> books = bookService.findAll();

        if (books != null) {
            for (int i = 0; i < random.nextInt(10); i++) {
                Book book = getRandomIndex(books);
                bookService.deleteById(book.getBookId());
                return book.getTitle() + " has been bought!";
            }
        }
        return "Out of stock!";
    }

    // 6
    @GetMapping("/import")
    public void importBook() {
        Iterable<Book> books = bookService.findAll();
        double bookSize = getBooksSize(books);
        if (bookSize <= 10) {
            fakeData();
        }
    }

    @PostMapping()
    public Book add(@RequestBody Book book) {
        for (int i = 0; i < book.getInventories().size(); i++) {
            Inventory inventory = book.getInventories().get(i);
            if (inventory.getUpdateDate() == null) {
                inventory.setUpdateDate(LocalDateTime.now());
            }
        }
        return bookService.save(book);
    }

    @GetMapping
    public Iterable<Book> getAll() {
        return bookService.findAll();
    }

    private static Book getRandomIndex(Iterable<Book> books) {
        double bookSize = getBooksSize(books);
        for (Book book: books){
            if (--bookSize < 0){
                return book;
            }
        }
        throw new AssertionError();
    }

    private static double getBooksSize(Iterable<Book> books) {
        List<Book> bookList = new ArrayList<>();
        books.iterator().forEachRemaining(bookList::add);
        double bookSize = (Math.random() * bookList.size());
        return bookSize;
    }

    private List<Inventory> fakeDataInventory() {
        List<Inventory> inventories = new ArrayList<>();

        for (int i = 0; i < random.nextInt(4); i++) {
            Inventory inventory = new Inventory();
            inventory.setId(random.nextLong());
            inventory.setAmount(random.nextInt(50));
            inventory.setUpdateDate(LocalDateTime.now());
            inventories.add(inventory);
        }
        return inventories;
    }

    private Book fakeDataBook() {
        Book book = new Book();
        book.setBookId(random.nextLong());
        book.setTitle(faker.book().title());
        book.setAuthor(faker.book().author());
        return book;
    }
}
