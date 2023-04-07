package gov.iti.sakila.services;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import gov.iti.sakila.presistence.entities.Actor;
import gov.iti.sakila.presistence.entities.Category;
import gov.iti.sakila.presistence.entities.Customer;
import gov.iti.sakila.presistence.entities.Film;
import gov.iti.sakila.presistence.entities.FilmActor;
import gov.iti.sakila.presistence.entities.FilmCategory;
import gov.iti.sakila.presistence.entities.Inventory;
import gov.iti.sakila.presistence.entities.Language;
import gov.iti.sakila.presistence.entities.Store;
import gov.iti.sakila.presistence.repositories.FilmRepository;

public class FilmService {

    private FilmRepository filmRepository = new FilmRepository();

    public Film create(Film film){
        return filmRepository.create(film);
    }
    public Film findById(Short filmId){
        return filmRepository.findById(filmId);
    }
    public Film deleteById(Short filmId){
        return filmRepository.deleteById(filmId);
    }
    public Film update(Film film){
        if(findById(film.getFilmId()) == null)
            return null;
        return filmRepository.update(film);
    }
    public List<Film> findAll(){
        return Collections.unmodifiableList(filmRepository.findAll());
    }

    public List<Actor> findFilmActors(Short filmId){
        Film film = findById(filmId);
        return film.getFilmActorList().stream().map(FilmActor::getActor).toList();
    }
    public Language findFilmLanguage(Short filmId){
        Film film = findById(filmId);
        return film.getLanguageId();
    }
    public Language findFilmOriginalLanguage(Short filmId){
        Film film = findById(filmId);
        return film.getOriginalLanguageId();
    }
    public List<Film> findByReleaseYear(Date releaseYear){
        return filmRepository.findByReleaseYear(releaseYear);

    }
    public List<Category> findFilmCategories(Short filmId){
        Film film = findById(filmId);
        return film.getFilmCategoryList().stream().map(FilmCategory::getCategory).toList();
    }
    public List<Film> findByRating(String rating){
        return filmRepository.findByRating(rating);
    }
    public List<Film> findByRentalDuration(String rentalDuration){
        return filmRepository.findByRentalDuration(rentalDuration);

    }
    public List<Film> findByReplacementCost(String replacementCost){
        return filmRepository.findByReplacementCost(replacementCost);
    }
    public List<Film> findByLength(Short length){
        return filmRepository.findByLength(length);

    }
    // return customers bought film
    public List<Customer> findFilmCustomers(Short filmId){
        Film film = findById(filmId);
        return film.getInventoryList().stream().flatMap(inv -> inv.getStoreId().getCustomerList().stream()).toList();     
    }
    // return stores
    public List<Store> findFilmStores(Short filmId){
        Film film = findById(filmId);
        return film.getInventoryList().stream().map(inv -> inv.getStoreId()).toList();     
    }
    // return inventory
    public List<Inventory> findFilmInventories(Short filmId){
        Film film = findById(filmId);
        return film.getInventoryList();
    }

  
}
