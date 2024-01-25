package com.idfc.bootcamp.bookstore;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class BookControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    BookRepository bookRepository;

    @MockBean
    BookDetailsRepository bookDetailsRepository;

    @MockBean
    CountryRepository countryRepository;

    Book b1 = new Book("book1", "author1", "description", 2.0, 100, "image_url");
    Book b2 = new Book("book2", "author2","description", 3.0, 100, "image_url");
    @MockBean
    CartRepository cartRepository;
    @MockBean
    CartItemsRepository cartItemsRepository;


    Book b1 = new Book("book1", "author1", "description", 2.0, 100);
    Book b2 = new Book("book2", "author2","description", 3.0, 100);

    @Test
    @org.junit.jupiter.api.DisplayName("should return success http status")
    public void shouldReturnSuccessHttpStatus() throws Exception {
        mockMvc.perform(get("/books")).andExpect(status().isOk());
    }

    @org.junit.jupiter.api.Test
    @DisplayName("should return two books")
    void shouldReturnTwoBooks() throws Exception {
        when(bookRepository.findBy(any(Pageable.class))).thenReturn(Arrays.asList(b1, b2));

        mockMvc.perform(get("/books"))
                .andExpect(jsonPath("$.books.length()").value(2));
        verify(bookRepository).findBy(any(Pageable.class));
    }

    @Test
    @DisplayName("should assert title")
    void shouldAssertTitle() throws Exception {
        when(bookRepository.findBy(any(Pageable.class))).thenReturn(Arrays.asList(b1, b2));

        mockMvc.perform(get("/books"))
                .andExpect(jsonPath("$.books.[0].title").value("book1"))
                .andExpect(jsonPath("$.books.[1].title").value("book2"))
                .andExpect(jsonPath("$.books.[0].description").value("description"))
                .andExpect(jsonPath("$.books.[0].ratings").value(2.0))
                .andExpect(jsonPath("$.books.[1].description").value("description"))
                .andExpect(jsonPath("$.books.[1].ratings").value(3.0));

        verify(bookRepository).findBy(any(Pageable.class));
    }

    @Test
    @DisplayName("should search in title, description or Author when search query is passed ")
    void shouldSearchInTitleDescriptionOrAuthorWhenSearchQueryIsPassed() throws Exception {

        Book b1 = new Book("book1", "author1", "description", 2.0, 100, "image_url");
        when(bookRepository.findByTitleLikeIgnoreCaseOrAuthorLikeIgnoreCaseOrDescriptionLikeIgnoreCase(
                anyString(),
                anyString(),
                anyString(),
                any(Pageable.class))).thenReturn(List.of(b1));
        mockMvc.perform(get("/books").param("search", "author1"))
                .andExpect(jsonPath("$.books.[0].title").value("book1"))
                .andExpect(jsonPath("$.books.length()").value(1));
        verify(bookRepository).findByTitleLikeIgnoreCaseOrAuthorLikeIgnoreCaseOrDescriptionLikeIgnoreCase(  anyString(),
                anyString(),
                anyString(), any(Pageable.class));
    }

    @Test
    @DisplayName("should return paginated list of books based on page number and page size")
    void shouldReturnPaginatedListOfBooksBasedOnOffsetAndTake() throws Exception {
        Book book1 = new Book("Refactoring", "Author1","test", 2.0, 100, "image_url");
        Book book2 = new Book("TDD", "testing","description", 2.0, 100, "image_url");

        when(bookRepository.findBy(
                any(Pageable.class))).thenReturn(Arrays.asList(book1,book2));
        mockMvc.perform(get("/books").param("pageNumber", "1").param("pageSize", "2"))
                .andExpect(jsonPath("$.books.[0].title").value("Refactoring"))
                .andExpect(jsonPath("$.books.[1].title").value("TDD"))
                .andExpect(jsonPath("$.books.length()").value(2));
        verify(bookRepository).findBy(
                any(Pageable.class));
    }
    @Test
    @DisplayName("should return price for the first book")
    void shouldReturnPriceForTheFirstBook() throws Exception {
        Book b1 = new Book("book1", "author1", "description", 2.0, 100, "image_url");
        Book b2 = new Book("book2", "author2","description", 3.0, 100, "image_url");
        when(bookRepository.findByTitleLikeIgnoreCaseOrAuthorLikeIgnoreCaseOrDescriptionLikeIgnoreCase(anyString(),
                anyString(),
                anyString(), any(Pageable.class))).thenReturn(Arrays.asList(b1, b2));
        mockMvc.perform(get("/books").param("search", "author1"))
                .andExpect(jsonPath("$.books.[0].price").value(100))
                .andExpect(jsonPath("$.books.length()").value(2));
        verify(bookRepository).findByTitleLikeIgnoreCaseOrAuthorLikeIgnoreCaseOrDescriptionLikeIgnoreCase(anyString(),
                anyString(),
                anyString(), any(Pageable.class));
    }

    @Test
    @DisplayName("should return sorted list of books in descending order of Price field")
    void shouldReturnSortedListOfBooksInDescendingOrderOfPriceField() throws Exception {
        Book b1 = new Book("book1", "author1", "description", 2.0, 80, "image_url");
        Book b2 = new Book("book2", "author2","description", 3.0, 350, "image_url");
        Book b3 = new Book("book3", "author3", "description", 2.0, 590, "image_url");
        Book b4 = new Book("book4", "author4","description", 3.0, 600, "image_url");

        when(bookRepository.findBy(any(Pageable.class))).thenReturn(Arrays.asList(b4,b3,b2,b1));
        mockMvc.perform(get("/books").param("sortBy", "price").param("order", "desc"))
                .andExpect(jsonPath("$.books.[0].price").value(600))
                .andExpect(jsonPath("$.books.[3].price").value(80));
    }

    @Test
    @DisplayName("should return book details based on id when it is not added to cart")
    void shouldReturnBookDetailsBasedOnIdWhenItIsNotAddedToCart() throws Exception {
        BookDetails bookDetails = new BookDetails(2,"Ashutosh book", "Ashutosh",
                "written by hariharan copied by ashutosh", 2.0, 10000,10,0, "image_url");
        when(bookDetailsRepository.findById(2)).thenReturn(bookDetails);
        mockMvc.perform(get("/book/2").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Ashutosh book"))
                .andExpect(jsonPath("$.quantity").value("0"));
    }

    @Test
    @DisplayName("should return not found when id is not available in DB")
    void shouldReturnNotFoundWhenIdIsNotAvailableInDb() throws Exception {
        when(bookDetailsRepository.findById(100)).thenReturn(null);
        mockMvc.perform(get("/book/100"))
                .andExpect(status().isNotFound());
        verify(bookDetailsRepository).findById(100);
    }

    @Test
    @DisplayName("should return book details when its added in cart")
    void shouldReturnBookDetailsWhenItsAddedInCart() throws Exception {
        BookDetails bookDetails = new BookDetails(1,"Ashutosh book", "Ashutosh",
                "written by hariharan copied by ashutosh", 2.0, 10000,10,10, "image_url");

        when(bookDetailsRepository.findById(1)).thenReturn(bookDetails);
        mockMvc.perform(get("/book/1"))
                .andExpect(jsonPath("$.title").value("Ashutosh book"));
        verify(bookDetailsRepository).findById(1L);
    }

    @Test
    @DisplayName("should return the cart items")
    void shouldReturnTheCartItems() throws Exception {
        CartItems items = new CartItems(1, "book1", "author1", "description", 2.0, 100, 10);
        when(cartItemsRepository.findAll()).thenReturn(Arrays.asList(items));
        mockMvc.perform(get("/cart"))
                .andExpect(jsonPath("$[0].title").value("book1"));
    }

    @Test
    @DisplayName("should return empty response with 200 http status when cart is empty")
    void shouldReturnEmptyResponseWith200HttpStatusWhenCartIsEmpty() throws Exception {

        when(cartItemsRepository.findAll()).thenReturn(new ArrayList<>());
        mockMvc.perform(get("/cart"))
                .andExpect(jsonPath("$.length()").value(0))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("should add the item to cart successfully")
    void shouldAddTheItemToCartSuccessfully() throws Exception {
       Cart cart = new Cart(1,10);
    }
}
