package com.idfc.bootcamp.bookstore;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
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
        Book book1 = new Book("Refactoring", "Author1","description", 2.0, 500);
        Book book2 = new Book("TDD", "Author2","description", 2.0, 600);

        bookRepository.saveAll(Arrays.asList(book1, book2));

        BookListResponse bookListResponse = restTemplate.exchange(baseUrl+"/books", HttpMethod.GET, null,
                new ParameterizedTypeReference<BookListResponse>(){}).getBody();

        assert bookListResponse != null;
        final List<Book> books = bookListResponse.getBooks();
        assert books != null;
        assertEquals(2, books.size());
    }

    @Test
    @DisplayName("should search in title, description or author when search query is passed")
    void shouldSearchInTitleDescriptionOrAuthorWhenSearchQueryIsPassed() {
        Book book1 = new Book("Refactoring", "Author1","description", 2.0, 100);
        Book book2 = new Book("TDD", "Author2","description", 2.0, 200);

        bookRepository.saveAll(Arrays.asList(book1, book2));

        BookListResponse bookListResponse = restTemplate.exchange(baseUrl+"/books?search={search}", HttpMethod.GET, null,
                new ParameterizedTypeReference<BookListResponse>(){}, "Author1").getBody() ;

        assert bookListResponse != null;
        final List<Book> books = bookListResponse.getBooks();
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

        BookListResponse bookListResponse = restTemplate.exchange(baseUrl+"/books?search={search}", HttpMethod.GET, null,
                new ParameterizedTypeReference<BookListResponse>(){}, "test").getBody() ;

        assert bookListResponse != null;
        final List<Book> books = bookListResponse.getBooks();
        assert books != null;
        assertEquals(3, books.size());
    }

    @Test
    @DisplayName("should return empty list when no books match search param")
    void shouldReturnEmptyListWhenNoBooksMatchSearchParam() {
        Book book1 = new Book("Refactoring", "Author1","description", 2.0, 300);
        Book book2 = new Book("TDD", "Author2","description", 2.0, 400);

        bookRepository.saveAll(Arrays.asList(book1, book2));

        BookListResponse bookListResponse = restTemplate.exchange(baseUrl+"/books?search={search}", HttpMethod.GET, null,
                new ParameterizedTypeReference<BookListResponse>(){}, "test").getBody() ;

        assert bookListResponse != null;
        final List<Book> books = bookListResponse.getBooks();
        assert books != null;
        assertEquals(0, books.size());
    }

    @Test
    @DisplayName("should return paginated list of books based on offset and limit")
    void shouldReturnPaginatedListOfBooksBasedOnOffsetAndTake() {
        Book book1 = new Book("Refactoring", "Author1","test", 2.0, 100);
        Book book2 = new Book("TDD", "testing","description", 2.0, 100);
        Book book3 = new Book("test", "Author3","description", 2.0, 100);
        Book book4 = new Book("BOOK4", "Author4","description", 2.0, 100);
        Book book5 = new Book("Refactoring", "Author1","test", 2.0, 100);
        Book book6 = new Book("TDD", "testing","descriptionas", 2.0, 100);
        Book book7 = new Book("test", "Author3","descriptionas", 2.0, 100);
        Book book8 = new Book("BOOK4", "Author4","descriptionas", 2.0, 100);

        bookRepository.saveAll(Arrays.asList(book1, book2, book3, book4,book5, book6, book7, book8));

        BookListResponse bookListResponse = restTemplate.exchange(baseUrl+"/books?pageNumber={pageNumber}&pageSize={pageSize}", HttpMethod.GET, null,
                new ParameterizedTypeReference<BookListResponse>(){}, 0,2).getBody() ;
        assert bookListResponse != null;
        List<Book> books = bookListResponse.getBooks();
        assert books != null;
        assertEquals(books.get(0).getTitle(), book1.getTitle());
        assertEquals(books.get(1).getTitle(), book2.getTitle());

        BookListResponse bookListResponse2 = restTemplate.exchange(baseUrl+"/books?pageNumber={pageNumber}&pageSize={pageSize}", HttpMethod.GET, null,
                new ParameterizedTypeReference<BookListResponse>(){},1  ,4).getBody() ;
        assert bookListResponse2 != null;
        List<Book> books2 = bookListResponse2.getBooks();
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
        Book book1 = new Book("Refactoring", "Author1","test", 2.0, 100);
        Book book2 = new Book("TDD", "asda","description", 2.0, 100);
        Book book3 = new Book("test", "Author3","description", 2.0, 100);
        Book book4 = new Book("BOOK4", "Author4","description", 2.0, 100);
        Book book5 = new Book("Refactoring", "Author1","test", 2.0, 100);
        Book book6 = new Book("TDD", "testing","descriptionas", 2.0, 100);
        Book book7 = new Book("test", "Author3","descriptionas", 2.0, 100);
        Book book8 = new Book("BOOK4", "Author4","descriptionas", 2.0, 100);

        bookRepository.saveAll(Arrays.asList(book1, book2, book3, book4,book5, book6, book7, book8));

        BookListResponse bookListResponse = restTemplate.exchange(baseUrl+"/books?search={search}&pageNumber={pageNumber}&pageSize={pageSize}", HttpMethod.GET, null,
                new ParameterizedTypeReference<BookListResponse>(){}, "test",0,2).getBody();
        assert bookListResponse != null;
        List<Book> books = bookListResponse.getBooks();
        assert books != null;
        assertEquals(books.get(0).getTitle(), book1.getTitle());
        assertEquals(books.get(1).getTitle(), book3.getTitle());

    }

    @Test
    @DisplayName("should return totalNumberOfRecords along with paginated list of books")
    void shouldReturnTotalNumberOfRecordsAlongWithPaginatedListOfBooks() {
        Book book1 = new Book("Refactoring", "Author1","test", 2.0, 100);
        Book book2 = new Book("TDD", "asda","description", 2.0, 100);
        Book book3 = new Book("test", "Author3","description", 2.0, 100);
        Book book4 = new Book("BOOK4", "Author4","description", 2.0, 100);

        bookRepository.saveAll(Arrays.asList(book1, book2, book3, book4));
        BookListResponse actualBooksResponse = restTemplate.exchange(baseUrl+"/books?pageNumber={pageNumber}&pageSize={pageSize}", HttpMethod.GET, null,
                new ParameterizedTypeReference<BookListResponse>(){}, 0,2).getBody() ;

        BookListResponse expectedBooksResponse = new BookListResponse(Arrays.asList(book1, book2), 4);

        assert actualBooksResponse != null;
        assertEquals(expectedBooksResponse.getBooks().size(), actualBooksResponse.getBooks().size());
        assertEquals(expectedBooksResponse.getTotalNoOfBooks(), actualBooksResponse.getTotalNoOfBooks());
    }
//    @Test
//    @DisplayName("should return book details based on book id")
//    void shouldReturnBookDetailsBasedOnBookId() {
//        Book book = new Book("Refactoring", "Ashutosh","Book for clean code", 2.0, 100);
//        bookRepository.save(book);
//        Inventory inventory = new Inventory(1, 10, 10);
//        inventoryRepository.save(inventory);
//        BookDetails bookDetails = restTemplate.exchange(baseUrl+"/book/1", HttpMethod.GET, null,
//                new ParameterizedTypeReference<BookDetails>(){}).getBody() ;
//        assertEquals("Ashutosh", bookDetails.getAuthor());
//    }

    @AfterEach
    void tearDown() {
        bookRepository.deleteAll();
    }
}
