package com.idfc.bootcamp.bookstore;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(properties = {"spring.flyway.enabled=false"})
public class BookAPiIntegrationTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private BookRepository bookRepository;

    @LocalServerPort
    int randomServerPort;

    private String baseUrl;

    @BeforeEach
    void setUp() {
        baseUrl = "http://localhost:"+randomServerPort+"/";
    }

    @Test
    @DisplayName("should return list of books when endpoint is accessed")
    void shouldReturnListOfBooksWhenEndpointIsAccessed() {
        Book book1 = new Book("Refactoring", "Author1","description", 2.0);
        Book book2 = new Book("TDD", "Author2","description", 2.0);

        bookRepository.saveAll(Arrays.asList(book1, book2));

        final List<Book> books = restTemplate.exchange(baseUrl+"/books", HttpMethod.GET, null,
                new ParameterizedTypeReference<List<Book>>(){}).getBody();

        assertEquals(2, books.size());
    }

    @Test
    @DisplayName("should search in title, description or author when search query is passed")
    void shouldSearchInTitleDescriptionOrAuthorWhenSearchQueryIsPassed() {
        Book book1 = new Book("Refactoring", "Author1","description", 2.0);
        Book book2 = new Book("TDD", "Author2","description", 2.0);

        bookRepository.saveAll(Arrays.asList(book1, book2));

        final List<Book> books = restTemplate.exchange(baseUrl+"/books?search={search}", HttpMethod.GET, null,
                new ParameterizedTypeReference<List<Book>>(){}, "Author1").getBody() ;

        assertEquals(1, books.size());
    }

    @Test
    @DisplayName("should return three books based on search query")
    void shouldReturnThreeBooksBasedOnSearchQuery() {
        Book book1 = new Book("Refactoring", "Author1","test", 2.0);
        Book book2 = new Book("TDD", "test","description", 2.0);
        Book book3 = new Book("test", "Author3","description", 2.0);
        Book book4 = new Book("BOOK4", "Author4","description", 2.0);

        bookRepository.saveAll(Arrays.asList(book1, book2, book3, book4));

        final List<Book> books = restTemplate.exchange(baseUrl+"/books?search={search}", HttpMethod.GET, null,
                new ParameterizedTypeReference<List<Book>>(){}, "test").getBody() ;

        assertEquals(3, books.size());
    }

    @Test
    @DisplayName("should return empty list when no books match search param")
    void shouldReturnEmptyListWhenNoBooksMatchSearchParam() {
        Book book1 = new Book("Refactoring", "Author1","description", 2.0);
        Book book2 = new Book("TDD", "Author2","description", 2.0);

        bookRepository.saveAll(Arrays.asList(book1, book2));

        final List<Book> books = restTemplate.exchange(baseUrl+"/books?search={search}", HttpMethod.GET, null,
                new ParameterizedTypeReference<List<Book>>(){}, "test").getBody() ;

        assertEquals(0, books.size());
    }
    @AfterEach
    void tearDown() {
        bookRepository.deleteAll();
    }
}
