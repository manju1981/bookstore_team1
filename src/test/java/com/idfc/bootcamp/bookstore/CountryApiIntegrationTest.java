package com.idfc.bootcamp.bookstore;

import com.google.gson.Gson;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
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
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(properties = {"spring.flyway.enabled=false"})
public class CountryApiIntegrationTest {
    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private BookDetailsRepository bookDetailsRepository;
    @Autowired
    private CountryRepository countryRepository;
    @LocalServerPort
    int randomServerPort;

    private String baseUrl;

    @BeforeEach
    void setUp() {
        baseUrl = "http://localhost:"+randomServerPort+"/";
    }

    @Test
    @DisplayName("should add countries when country list is passed")
    void shouldAddCountriesWhenCountryListIsPassed() throws MalformedURLException, URISyntaxException {
        Country country1 = new Country("Latvia");
        Country country2 = new Country("Belarus");
        Country country3 = new Country("Cyprus");
        Country country4 = new Country("Djibouti");
        Country country5 = new Country("Luxembourg");
        Gson gson = new Gson();
        String jsonCountryList = gson.toJson(Arrays.asList(country1,country2,country3,country4, country5));
        RequestEntity<String> requestEntity = RequestEntity.post(new URL(baseUrl+"countries").toURI()).contentType(MediaType.APPLICATION_JSON).body(jsonCountryList);
        HttpStatusCode actualStatus = restTemplate.exchange(requestEntity, new ParameterizedTypeReference<>(){}).getStatusCode();

        assertEquals(HttpStatus.OK, actualStatus);
    }

    @Test
    @DisplayName("should get list of all countries present in database")
    void shouldGetListOfAllCountriesPresentInDatabase() {
        Country country1 = new Country("Latvia");
        Country country2 = new Country("Belarus");
        Country country3 = new Country("Cyprus");
        Country country4 = new Country("Djibouti");
        Country country5 = new Country("Luxembourg");

        countryRepository.saveAll(Arrays.asList(country1,country2,country3,country4, country5));
        List<Country> countries = restTemplate.exchange(baseUrl + "countries", HttpMethod.GET, null,
                new ParameterizedTypeReference<List<Country>>(){}).getBody();
        assertNotNull(countries);
        assertEquals(5, countries.size());
    }

    @Test
    @DisplayName("should return empty list when no countries are present")
    void shouldReturnEmptyListWhenNoCountriesArePresent() {
        List<Country> countries = restTemplate.exchange(baseUrl + "countries", HttpMethod.GET, null,
                new ParameterizedTypeReference<List<Country>>(){}).getBody();
        assertNotNull(countries);
        assertEquals(0, countries.size());
    }
    @AfterEach
    void tearDown() {
        countryRepository.deleteAll();
    }
    }
