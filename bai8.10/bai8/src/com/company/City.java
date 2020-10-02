package com.company;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class City {
    private int id, polulation;
    private String name, countryCode;

    public static City parse(String line) {
        Pattern pattern = Pattern.compile("( \\[| )(.+?=.+?)(,|\\]$)");
        Matcher matcher = pattern.matcher(line);
        Map<String, String> map = new HashMap<>();

        while (matcher.find()) {
            String match = matcher.group(2);
            List<String> tokens = StringUtils.tokenize(match, "=");
            map.put(tokens.get(0), tokens.get(1));
        }

        return new City(
                Integer.parseInt(map.get("id")),
                Integer.parseInt(map.get("population")),
                map.get("name"),
                map.get("countryCode")
        );
    }

    public City() {
    }

    public City(int id, int polulation, String name, String countryCode) {
        this.id = id;
        this.polulation = polulation;
        this.name = name;
        this.countryCode = countryCode;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPolulation() {
        return polulation;
    }

    public void setPolulation(int polulation) {
        this.polulation = polulation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
