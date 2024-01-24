package com.idfc.bootcamp.bookstore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryService {

    CountryRepository countryRepository;

    @Autowired
    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    public void addCountries(List<Country> countries) {
        countryRepository.saveAll(countries);
    }

    public List<Country> getCountries() {
        return countryRepository.findAll();
    }
}
