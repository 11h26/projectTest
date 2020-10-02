package com.company;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Country {
    private String code, name, continent;
    private double surfaceArea, gpn;
    private int capital, population;

    public static Country parse(String line) {
        Pattern pattern = Pattern.compile("(\\{| )(.+?=.+?)(,|\\}$)");
        Matcher matcher = pattern.matcher(line);
        Map<String, String> map = new HashMap<>();

        while (matcher.find()) {
            String match = matcher.group(2);
            List<String> tokens = StringUtils.tokenize(match, "=");
            map.put(tokens.get(0), tokens.get(1));
        }

        return new Country(
                map.get("code"),
                map.get("name"),
                map.get("continent"),
                Double.parseDouble(map.get("surfaceArea")),
                Double.parseDouble(map.get("gnp")),
                Integer.parseInt(map.get("capital")),
                Integer.parseInt(map.get("population"))
        );
    }

    public Country() {
    }

    public Country(String code, String name, String continent, double surfaceArea, double gpn, int capital, int population) {
        this.code = code;
        this.name = name;
        this.continent = continent;
        this.surfaceArea = surfaceArea;
        this.gpn = gpn;
        this.capital = capital;
        this.population = population;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public double getSurfaceArea() {
        return surfaceArea;
    }

    public void setSurfaceArea(double surfaceArea) {
        this.surfaceArea = surfaceArea;
    }

    public double getGpn() {
        return gpn;
    }

    public void setGpn(double gpn) {
        this.gpn = gpn;
    }

    public int getCapital() {
        return capital;
    }

    public void setCapital(int capital) {
        this.capital = capital;
    }
}
