package com.example.exercises;

import com.example.dao.InMemoryWorldDao;
import com.example.dao.WorldDao;
import com.example.domain.Country;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class Exercise3 {
    public static void main(String[] args) {
        WorldDao worldDao = InMemoryWorldDao.getInstance();
        List<Country> countries = worldDao.findAllCountries();
        Map<String, Long> continentCountryCounts = countries.stream()
                .collect(Collectors.groupingBy(Country::getContinent, Collectors.counting()));
        continentCountryCounts.forEach((continent, count) -> {
            System.out.println(continent + ": " + count);
        });

        List<Map.Entry<String, Long>> contsByCountNums = new ArrayList<>(continentCountryCounts.entrySet());
        Comparator<Map.Entry<String, Long>> compareByCountryNumber = (e1, e2) -> (int) (e1.getValue()-e2.getValue());
        Consumer<Map.Entry<String,Long>> entryPrinter = entry -> System.out.println(entry.getKey() + ": " + entry.getValue());
        contsByCountNums.stream()
                .sorted(compareByCountryNumber.reversed())
                .forEach(entryPrinter);
    }
}
