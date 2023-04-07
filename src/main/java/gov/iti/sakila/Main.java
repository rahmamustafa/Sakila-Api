package gov.iti.sakila;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Date;

import javax.xml.crypto.Data;

import gov.iti.sakila.presistence.connection.JpaManagerSingleton;
import gov.iti.sakila.presistence.entities.Film;
import gov.iti.sakila.presistence.entities.Language;
import gov.iti.sakila.presistence.repositories.GenericRepository;
import gov.iti.sakila.services.FilmService;
import jakarta.persistence.EntityManager;

public class Main {
    public static void main(String[] args) {
        FilmService filmService = new FilmService();
       
        Film film = new Film("Rahma",Short.valueOf("6"),BigDecimal.valueOf(20.99),
                                BigDecimal.valueOf(20.99),Date.from(Instant.now()));
        
        EntityManager e =  JpaManagerSingleton.INSTANCE.getEntityManager();
        // GenericRepository g = new GenericRepository(Language.class);
        // Language language = new Language("A",Date.from(Instant.now()));
        // g.create(language);
        film.setLanguageId(e.find(Language.class, 1));
        filmService.create(film);
        System.out.println(filmService.findById(film.getFilmId()).getTitle());
    }
}
