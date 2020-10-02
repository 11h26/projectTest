package com.company;

import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class App {
    private static final String CITIES_DATA = "data/cities.dat";
    private static final String COUNTRIES_DATA = "data/countries.dat";

    private final List<City> cityList;
    private final List<Country> countryList;

    public App() {
        try {
            cityList = Reader.readAllCities(CITIES_DATA);
            countryList = Reader.readAllCountries(COUNTRIES_DATA);
        } catch (Exception e) {
            System.out.println("File ngu lin!");
            throw e;
        }
    }

    public void part11() {
        System.out.println("1.1. ");
        countryList.forEach(country -> {
            City maxPopulationCity = cityList
                    .stream()
                    .filter(city -> city.getCountryCode().equals(country.getCode()))
                    .max(Comparator.comparing(City::getPolulation))
                    .orElse(new City());

            System.out.println("Thành phố đông dân nhất " + country.getName() + ": " + maxPopulationCity.getName() + " " + maxPopulationCity.getPolulation());
        });
    }

    public void part12() {
        System.out.println("1.2. ");
        Set<String> continentSet = countryList
                .stream()
                .map(Country::getContinent)
                .collect(Collectors.toSet());

        continentSet.forEach(continent -> {
            System.out.println("Thành phố đông dân nhất lục địa " + continent + ": ");

            City maxPopulationCity = countryList
                    .stream()
                    .filter(country -> country.getContinent() != null && country.getContinent().equals(continent))
                    .flatMap(country -> cityList
                            .stream()
                            .filter(city -> city.getCountryCode().equals(country.getCode()))
                    )
                    .max(Comparator.comparing(City::getPolulation))
                    .orElse(new City());
            System.out.println(maxPopulationCity.getName() + " " + maxPopulationCity.getPolulation());
        });
    }

    public void part13() {
        System.out.println("1.3. ");
        System.out.println("Thủ đô đông dân nhất: ");

        City max = countryList
                .stream()
                .map(country -> cityList.stream().filter(city -> country.getCapital() == city.getId()).findFirst().orElse(new City()))
                .max(Comparator.comparing(City::getPolulation))
                .orElse(new City());

        System.out.println(max.getName() + " " + max.getPolulation());
    }

    public void part14() {
        System.out.println("1.4. ");
        Set<String> continentSet = countryList
                .stream()
                .map(Country::getContinent)
                .collect(Collectors.toSet());

        continentSet.forEach(continent -> {
            System.out.println("Thủ đông dân nhất lục địa " + continent + ": ");

            City maxPopulationCity = countryList
                    .stream()
                    .filter(country -> country.getContinent() != null && country.getContinent().equals(continent))
                    .map(country -> cityList.stream().filter(city -> country.getCapital() == city.getId()).findFirst().orElse(new City()))
                    .max(Comparator.comparing(City::getPolulation))
                    .orElse(new City());
            System.out.println(maxPopulationCity.getName() + " " + maxPopulationCity.getPolulation());
        });
    }

    private int countCity(Country country) {
        return cityList.stream().filter(city -> city.getCountryCode().equals(country.getCode())).toArray().length;
    }

    public void part15() {
        System.out.println("1.5. ");
        countryList.stream()
                .sorted(Comparator.comparing(this::countCity).reversed())
                .forEach(country -> System.out.println(country.getName() + " " + countCity(country)));
    }

    public double getPopulationDensity(Country country) {
        try {
            return country.getPopulation() / country.getSurfaceArea() * 1.0;
        } catch (Exception e) {
            return 0;
        }
    }

    public void part16() {
        System.out.println("1.6. ");
        countryList.stream()
                .sorted(Comparator.comparing(this::getPopulationDensity).reversed())
                .forEach(country -> System.out.println(country.getName() + " " + getPopulationDensity(country)));
    }
}
