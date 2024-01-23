package com.idfc.bootcamp.bookstore;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class BookControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    BookRepository bookRepository;

    Book b1 = new Book("book1", "author1", "description", 2.0);
    Book b2 = new Book("book2", "author2","description", 3.0);

    @Test
    @org.junit.jupiter.api.DisplayName("should return success http status")
    public void shouldReturnSuccessHttpStatus() throws Exception {
        mockMvc.perform(get("/books")).andExpect(status().isOk());
    }

    @org.junit.jupiter.api.Test
    @DisplayName("should return two books")
    void shouldReturnTwoBooks() throws Exception {
        when(bookRepository.findAll()).thenReturn(Arrays.asList(b1, b2));

        mockMvc.perform(get("/books"))
                .andExpect(jsonPath("$.length()").value(2));
        verify(bookRepository).findAll();
    }

    @Test
    @DisplayName("should assert title")
    void shouldAssertTitle() throws Exception {
        when(bookRepository.findAll()).thenReturn(Arrays.asList(b1, b2));

        mockMvc.perform(get("/books"))
                .andExpect(jsonPath("$[0].title").value("book1"))
                .andExpect(jsonPath("$[1].title").value("book2"))
                .andExpect(jsonPath("$[0].description").value("description"))
                .andExpect(jsonPath("$[0].ratings").value(2.0))
                .andExpect(jsonPath("$[1].description").value("description"))
                .andExpect(jsonPath("$[1].ratings").value(3.0));

        verify(bookRepository).findAll();
    }

    @Test
    @DisplayName("should search in title, description or Author when search query is passed ")
    void shouldSearchInTitleDescriptionOrAuthorWhenSearchQueryIsPassed() throws Exception {
        Book b1 = new Book("book1", "author1", "description", 2.0);
        Book b2 = new Book("book2", "author2","description", 3.0);
        when(bookRepository.findByTitleOrAuthorOrDescription("author1","author1","author1")).thenReturn(Arrays.asList(b1));
        mockMvc.perform(get("/books").param("search", "author1"))
                .andExpect(jsonPath("$[0].title").value("book1"))
                .andExpect(jsonPath("$.length()").value(1));
        verify(bookRepository).findByTitleOrAuthorOrDescription("author1","author1","author1");
    }


}
