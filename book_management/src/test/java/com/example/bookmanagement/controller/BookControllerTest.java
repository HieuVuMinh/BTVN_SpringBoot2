package com.example.bookmanagement.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.example.bookmanagement.model.Book;
import com.example.bookmanagement.model.Inventory;
import com.example.bookmanagement.service.book.IBookService;
import com.example.bookmanagement.service.inventory.IInventoryService;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {BookController.class})
@ExtendWith(SpringExtension.class)
class BookControllerTest {
    @Autowired
    private BookController bookController;

    @MockBean
    private IBookService iBookService;

    @MockBean
    private IInventoryService iInventoryService;

    @Test
    @Disabled("TODO: This test is incomplete")
    void testBuyBook() throws Exception {
        when(this.iBookService.findAll()).thenReturn((Iterable<Book>) mock(Iterable.class));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/books/buy");
        MockMvcBuilders.standaloneSetup(this.bookController).build().perform(requestBuilder);
    }

    @Test
    void testFakeData() throws Exception {
        Inventory inventory = new Inventory();
        inventory.setAmount(10);
        inventory.setUpdateDate(LocalDateTime.of(1, 1, 1, 1, 1));
        inventory.setId(123L);
        when(this.iInventoryService.save((Inventory) any())).thenReturn(inventory);

        Book book = new Book();
        book.setInventories(new ArrayList<Inventory>());
        book.setBookId(123L);
        book.setTitle("Dr");
        book.setAuthor("JaneDoe");
        when(this.iBookService.save((Book) any())).thenReturn(book);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/books/fake_data");
        MockMvcBuilders.standaloneSetup(this.bookController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}

