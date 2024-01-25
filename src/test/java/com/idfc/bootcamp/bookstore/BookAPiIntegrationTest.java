package com.idfc.bootcamp.bookstore;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.google.gson.Gson;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
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

    @Autowired
    private CartRepository cartRepository;

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
        Book book1 = new Book("Refactoring", "Author1","test", 2.0, 100);
        Book book2 = new Book("TDD", "testing","description", 2.0, 100);
        Book book3 = new Book("test", "Author3","description", 2.0, 100);
        Book book4 = new Book("BOOK4", "Author4","description", 2.0, 100);
        Book book5 = new Book("Refactoring", "Author1","test", 2.0, 100);
        Book book6 = new Book("TDD", "testing","descriptionas", 2.0, 100);
        Book book7 = new Book("test", "Author3","descriptionas", 2.0, 100);
        Book book8 = new Book("BOOK4", "Author4","descriptionas", 2.0, 100);

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
        Book book1 = new Book("Refactoring", "Author1","test", 2.0, 100);
        Book book2 = new Book("TDD", "asda","description", 2.0, 100);
        Book book3 = new Book("test", "Author3","description", 2.0, 100);
        Book book4 = new Book("BOOK4", "Author4","description", 2.0, 100);
        Book book5 = new Book("Refactoring", "Author1","test", 2.0, 100);
        Book book6 = new Book("TDD", "testing","descriptionas", 2.0, 100);
        Book book7 = new Book("test", "Author3","descriptionas", 2.0, 100);
        Book book8 = new Book("BOOK4", "Author4","descriptionas", 2.0, 100);

        bookRepository.saveAll(Arrays.asList(book1, book2, book3, book4,book5, book6, book7, book8));

        List<Book> books = restTemplate.exchange(baseUrl+"/books?search={search}&pageNumber={pageNumber}&pageSize={pageSize}", HttpMethod.GET, null,
                new ParameterizedTypeReference<List<Book>>(){}, "test",0,2).getBody() ;
        assert books != null;
        assertEquals(books.get(0).getTitle(), book1.getTitle());
        assertEquals(books.get(1).getTitle(), book3.getTitle());

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

    @Test
    @DisplayName("should return list of carts item")
    void shouldReturnListOfCartsItem() {
        Book book1 = new Book("Refactoring", "Author1","test", 2.0, 100);
        Book book2 = new Book("TDD", "asda","description", 2.0, 100);
        bookRepository.saveAll(Arrays.asList(book1, book2));
        Cart cart1 = new Cart(1,10);
        Cart cart2 = new Cart(1,10);
        cartRepository.saveAll(Arrays.asList(cart1, cart2));
        List<Book> books = restTemplate.exchange(baseUrl+"/cart", HttpMethod.GET, null,
                new ParameterizedTypeReference<List<Book>>(){}, "test",0,2).getBody() ;
        assertEquals("Refactoring", books.get(0).getTitle());
        assertEquals(100, books.get(1).getPrice());

    }

    @Test
    @DisplayName("should return empty list with status code 200 when cart is empty")
    void shouldReturnEmptyListWithStatusCode200WhenCartIsEmpty() {
        Book book1 = new Book("Refactoring", "Author1","test", 2.0, 100);
        Book book2 = new Book("TDD", "asda","description", 2.0, 100);
        bookRepository.saveAll(Arrays.asList(book1, book2));
        ResponseEntity<List<Book>> books = restTemplate.exchange(baseUrl+"/cart", HttpMethod.GET, null,
                new ParameterizedTypeReference<>(){}, "test",0,2) ;
        assertEquals(new ArrayList<>(), books.getBody());
        assertEquals(200, books.getStatusCode().value());

    }

    @Test
    @DisplayName("should return success response for new insert into cart") // need to fix test case
    void shouldReturnSuccessResponseForNewInsertIntoCart() throws  MalformedURLException, URISyntaxException {
        Book book1 = new Book("Ashutosh", "Author1","test", 2.0, 100);
        Book book =  bookRepository.save(book1);

        Cart cart = new Cart(book.getId().intValue(),10);

        Gson gson = new Gson();
        String jsonCart = gson.toJson(cart);
        RequestEntity<String> requestEntity = RequestEntity.post(new URL(baseUrl+"cart").toURI()).contentType(MediaType.APPLICATION_JSON).accept(MediaType.valueOf(MediaType.ALL_VALUE)).body(jsonCart);
        ResponseEntity<String> actualStatus = restTemplate.exchange(requestEntity, new ParameterizedTypeReference<>(){});
        assertEquals("Failed to update cart", actualStatus.getBody());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, actualStatus.getStatusCode());

    }

    @AfterEach
    void tearDown() {
        bookRepository.deleteAll();
        cartRepository.deleteAll();
    }
}
