package gov.iti.sakila;

import gov.iti.sakila.services.FilmService;

public class Main {
    public static void main(String[] args) {
        FilmService filmService = new FilmService();
        System.out.println(filmService.findByRating("G"));
    }
}
