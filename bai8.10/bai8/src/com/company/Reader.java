package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Reader {
    public static List<String> readAllLines(String fileName) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return lines;
    }

    public static List<City> readAllCities(String fileName) {
        List<String> cityLines = Reader.readAllLines(fileName);

        return cityLines.stream().map(City::parse).collect(Collectors.toList());
    }

    public static List<Country> readAllCountries(String fileName) {
        List<String> countryLines = Reader.readAllLines(fileName);

        return countryLines.stream().map(Country::parse).collect(Collectors.toList());
    }
}
