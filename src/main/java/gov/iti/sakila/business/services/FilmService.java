package gov.iti.sakila.business.services;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import gov.iti.sakila.business.mappers.ActorMapper;
import gov.iti.sakila.business.mappers.FilmMapper;
import gov.iti.sakila.presistence.dtos.actor.ActorDto;
import gov.iti.sakila.presistence.dtos.film.FilmDto;
import gov.iti.sakila.presistence.dtos.film.FilmDtoCreate;
import gov.iti.sakila.presistence.entities.*;
import gov.iti.sakila.presistence.repositories.FilmRepository;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;

@WebService
public class FilmService {

    private final FilmRepository filmRepository = new FilmRepository();

    public FilmDto createFilm(@WebParam(name = "film") FilmDtoCreate filmDtoCreate){
        Film film = FilmMapper.INSTANCE.filmDtoCreateToFilm(filmDtoCreate);
        film.setLastUpdate(java.sql.Date.from(Instant.now()));
        film.setLanguageId();
        film = filmRepository.create(film);
        return FilmMapper.INSTANCE.filmToFilmDto(film);
    }
    public FilmDto findFilmById(@WebParam(name = "filmId")Short filmId){
        Film film = filmRepository.findById(filmId);
        return FilmMapper.INSTANCE.filmToFilmDto(film);
    }
    public boolean deleteFilmById(@WebParam(name = "filmId")Short filmId){
        return filmRepository.deleteById(filmId);
    }
//    public Film update(Film film){
//        if(findFilmById(film.getFilmId()) == null)
//            return null;
//        return filmRepository.update(film);
//    }
    public List<FilmDto> findAllFilms(){
        List<Film> films = Collections.unmodifiableList(filmRepository.findAll());
        return films.stream().map(FilmMapper.INSTANCE::filmToFilmDto).toList();
    }

    public List<ActorDto> findFilmActors(@WebParam(name = "filmId")Short filmId){
        Film film = filmRepository.findById(filmId);
        List<Actor> filmActors = film.getFilmActorList().stream().map(FilmActor::getActor).toList();
        return filmActors.stream().map(ActorMapper.INSTANCE::actorToActorDto).toList();
    }
    public String findFilmLanguage(@WebParam(name = "filmId")Short filmId){
        return findFilmById(filmId).getLanguage();
    }
    public String findFilmOriginalLanguage(@WebParam(name = "filmId")Short filmId){
        return findFilmById(filmId).getOriginalLanguage();
    }
    public List<FilmDto> findByReleaseYear(@WebParam(name = "filmId")Date releaseYear){
        List<Film> films = filmRepository.findByReleaseYear(releaseYear);
        return films.stream().map(FilmMapper.INSTANCE::filmToFilmDto).toList();

    }
    public List<String> findFilmCategories(Short filmId){
        Film film = filmRepository.findById(filmId);
        List<Category> categories = film.getFilmCategoryList().stream().map(FilmCategory::getCategory).toList();
        return categories.stream().map(Category::getName).toList();
    }
    public List<FilmDto> findByRating(String rating){
        List<Film> films = filmRepository.findByRating(rating);
        return films.stream().map(FilmMapper.INSTANCE::filmToFilmDto).toList();

    }

    public List<FilmDto> findByRentalDuration(String rentalDuration){
        List<Film> films = filmRepository.findByRentalDuration(rentalDuration);
        return films.stream().map(FilmMapper.INSTANCE::filmToFilmDto).toList();

    }
    public List<FilmDto> findByReplacementCost(String replacementCost){
        List<Film> films = filmRepository.findByReplacementCost(replacementCost);
        return films.stream().map(FilmMapper.INSTANCE::filmToFilmDto).toList();

    }
    public List<FilmDto> findByLength(Short length){
        List<Film> films = filmRepository.findByLength(length);
        return films.stream().map(FilmMapper.INSTANCE::filmToFilmDto).toList();

    }
    // return customers bought film
//    public List<Customer> findFilmCustomers(Short filmId){
//        Film film = findById(filmId);
//        return film.getInventoryList().stream().flatMap(inv -> inv.getStoreId().getCustomerList().stream()).toList();
//    }
//    // return stores
//    public List<Store> findFilmStores(Short filmId){
//        Film film = findById(filmId);
//        return film.getInventoryList().stream().map(inv -> inv.getStoreId()).toList();
//    }
//    // return inventory
//    public List<Inventory> findFilmInventories(Short filmId){
//        Film film = findById(filmId);
//        return film.getInventoryList();
//    }

}
