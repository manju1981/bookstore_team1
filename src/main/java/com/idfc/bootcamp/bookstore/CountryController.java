package com.idfc.bootcamp.bookstore;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CountryController {

    CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @CrossOrigin()
    @PostMapping("/countries")
    public void addCountries(@RequestBody List<Country> countries) {
        countryService.addCountries(countries);
    }

    @CrossOrigin
    @GetMapping("/countries")
    public List<Country> getCountries(){
        return  countryService.getCountries();
    }
}
