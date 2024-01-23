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
    private Book book1;

    @BeforeEach
    void setUp() {
        baseUrl = "http://localhost:"+randomServerPort+"/";
    }

    @Test
    @DisplayName("should return list of books when endpoint is accessed")
    void shouldReturnListOfBooksWhenEndpointIsAccessed() {
        Book book1 = new Book("Refactoring", "Author1","description", 2.0, 500);
        Book book2 = new Book("TDD", "Author2","description", 2.0, 600);

        bookRepository.saveAll(Arrays.asList(book1, book2));

        final List<Book> books = restTemplate.exchange(baseUrl+"/books", HttpMethod.GET, null,
                new ParameterizedTypeReference<List<Book>>(){}).getBody();

        assert books != null;
        assertEquals(2, books.size());
    }

    @Test
    @DisplayName("should search in title, description or author when search query is passed")
    void shouldSearchInTitleDescriptionOrAuthorWhenSearchQueryIsPassed() {
        Book book1 = new Book("Refactoring", "Author1","description", 2.0, 100);
        Book book2 = new Book("TDD", "Author2","description", 2.0, 200);

        bookRepository.saveAll(Arrays.asList(book1, book2));

        final List<Book> books = restTemplate.exchange(baseUrl+"/books?search={search}", HttpMethod.GET, null,
                new ParameterizedTypeReference<List<Book>>(){}, "Author1").getBody() ;

        assert books != null;
        assertEquals(1, books.size());
    }

    @Test
    @DisplayName("should return three books based on search query")
    void shouldReturnThreeBooksBasedOnSearchQuery() {
        Book book1 = new Book("Refactoring", "Author1","test", 2.0, 100);
        Book book2 = new Book("TDD", "test","description", 2.0, 100);
        Book book3 = new Book("test", "Author3","description", 2.0, 200);
        Book book4 = new Book("BOOK4", "Author4","description", 2.0, 300);

        bookRepository.saveAll(Arrays.asList(book1, book2, book3, book4));

        final List<Book> books = restTemplate.exchange(baseUrl+"/books?search={search}", HttpMethod.GET, null,
                new ParameterizedTypeReference<List<Book>>(){}, "test").getBody() ;

        assert books != null;
        assertEquals(3, books.size());
    }

    @Test
    @DisplayName("should return empty list when no books match search param")
    void shouldReturnEmptyListWhenNoBooksMatchSearchParam() {
        Book book1 = new Book("Refactoring", "Author1","description", 2.0, 300);
        Book book2 = new Book("TDD", "Author2","description", 2.0, 400);

        bookRepository.saveAll(Arrays.asList(book1, book2));

        final List<Book> books = restTemplate.exchange(baseUrl+"/books?search={search}", HttpMethod.GET, null,
                new ParameterizedTypeReference<List<Book>>(){}, "test").getBody() ;

        assert books != null;
        assertEquals(0, books.size());
    }

    @Test
    @DisplayName("should return paginated list of books based on offset and limit")
    void shouldReturnPaginatedListOfBooksBasedOnOffsetAndTake() {
        Book book1 = new Book("Refactoring", "Author1","test", 2.0);
        Book book2 = new Book("TDD", "testing","description", 2.0);
        Book book3 = new Book("test", "Author3","description", 2.0);
        Book book4 = new Book("BOOK4", "Author4","description", 2.0);
        Book book5 = new Book("Refactoring", "Author1","test", 2.0);
        Book book6 = new Book("TDD", "testing","descriptionas", 2.0);
        Book book7 = new Book("test", "Author3","descriptionas", 2.0);
        Book book8 = new Book("BOOK4", "Author4","descriptionas", 2.0);

        bookRepository.saveAll(Arrays.asList(book1, book2, book3, book4,book5, book6, book7, book8));

         List<Book> books = restTemplate.exchange(baseUrl+"/books?pageNumber={pageNumber}&pageSize={pageSize}", HttpMethod.GET, null,
                new ParameterizedTypeReference<List<Book>>(){}, 0,2).getBody() ;
        assert books != null;
        assertEquals(books.get(0).getTitle(), book1.getTitle());
        assertEquals(books.get(1).getTitle(), book2.getTitle());

        List<Book> books2 = restTemplate.exchange(baseUrl+"/books?pageNumber={pageNumber}&pageSize={pageSize}", HttpMethod.GET, null,
                new ParameterizedTypeReference<List<Book>>(){},1  ,4).getBody() ;
        assert books2 != null;
        assertEquals(4,books2.size());

        assertEquals(books2.get(0).getTitle(), book5.getTitle());
        assertEquals(books2.get(1).getTitle(), book6.getTitle());
        assertEquals(books2.get(2).getTitle(), book7.getTitle());
        assertEquals(books2.get(3).getTitle(), book8.getTitle());


    }


    @org.junit.jupiter.api.Test
    @org.junit.jupiter.api.DisplayName("should return paged output with search")
    void shouldReturnPagedOutputWithSearch() {
        Book book1 = new Book("Refactoring", "Author1","test", 2.0);
        Book book2 = new Book("TDD", "asda","description", 2.0);
        Book book3 = new Book("test", "Author3","description", 2.0);
        Book book4 = new Book("BOOK4", "Author4","description", 2.0);
        Book book5 = new Book("Refactoring", "Author1","test", 2.0);
        Book book6 = new Book("TDD", "testing","descriptionas", 2.0);
        Book book7 = new Book("test", "Author3","descriptionas", 2.0);
        Book book8 = new Book("BOOK4", "Author4","descriptionas", 2.0);

        bookRepository.saveAll(Arrays.asList(book1, book2, book3, book4,book5, book6, book7, book8));

        List<Book> books = restTemplate.exchange(baseUrl+"/books?search={search}&pageNumber={pageNumber}&pageSize={pageSize}", HttpMethod.GET, null,
                new ParameterizedTypeReference<List<Book>>(){}, "test",0,2).getBody() ;
        assert books != null;
        assertEquals(books.get(0).getTitle(), book1.getTitle());
        assertEquals(books.get(1).getTitle(), book3.getTitle());

    }


    @AfterEach
    void tearDown() {
        bookRepository.deleteAll();
    }
}
