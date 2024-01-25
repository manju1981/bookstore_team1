package com.idfc.bootcamp.bookstore;

import com.google.gson.Gson;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class CountryControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    CountryRepository countryRepository;
    @MockBean
    BookRepository bookRepository;
    @MockBean
    BookDetailsRepository bookDetailsRepository;
    @MockBean
    CartRepository cartRepository;
    @MockBean
    CartItemsRepository cartItemsRepository;

    @Test
    @DisplayName("should add list of countries")
    void shouldReturnListOfCountries() throws Exception {
        Country country1 = new Country("Latvia");
        Country country2 = new Country("Belarus");
        Country country3 = new Country("Cyprus");
        Country country4 = new Country("Djibouti");
        Country country5 = new Country("Luxembourg");

        Gson gson = new Gson();
        String jsonCountryList = gson.toJson(Arrays.asList(country1, country2, country3, country4, country5));
        mockMvc.perform(post("/countries").content(jsonCountryList).contentType("application/json")).andExpect(status().isOk());
    }

    @Test
    @DisplayName("should get the list of countries")
    void shouldGetTheListOfCountries() throws Exception {
        Country country1 = new Country("Latvia");
        Country country2 = new Country("Belarus");
        Country country3 = new Country("Cyprus");
        Country country4 = new Country("Djibouti");
        Country country5 = new Country("Luxembourg");
       when(countryRepository.findAll()).thenReturn(Arrays.asList(country1, country2,country3,country4,country5));

        mockMvc.perform(MockMvcRequestBuilders.get("/countries")).andExpect(status().isOk());
    }
}
