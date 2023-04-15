package gov.iti.sakila.business.endpoints.soap;

import gov.iti.sakila.business.mappers.StoreMapper;
import gov.iti.sakila.business.services.FilmService;
import gov.iti.sakila.presistence.dtos.actor.ActorDto;
import gov.iti.sakila.presistence.dtos.customer.CustomerDto;
import gov.iti.sakila.presistence.dtos.film.FilmDto;
import gov.iti.sakila.presistence.dtos.film.FilmDtoCreate;
import gov.iti.sakila.presistence.dtos.film.FilmDtoWithCountForStore;
import gov.iti.sakila.presistence.dtos.film.StoreFilmInventoryDto;
import gov.iti.sakila.presistence.dtos.store.StoreDto;
import gov.iti.sakila.presistence.dtos.store.StoreInventoryDto;
import gov.iti.sakila.presistence.entities.Store;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import lombok.NonNull;

import java.util.Date;
import java.util.List;

@WebService
public class FilmEndPoint {
    private final FilmService filmService = new FilmService();
    public FilmDto createFilm(@WebParam(name = "film") FilmDtoCreate filmDtoCreate){
        return filmService.createFilm(filmDtoCreate);
    }
    public FilmDto findFilmById(@WebParam(name = "id")Short filmId){
        return filmService.findFilmById(filmId);
    }
    public int deleteFilmById(@WebParam(name = "id")Short filmId){
        return filmService.deleteFilmById(filmId);
    }
    //    public Film update(Film film){
//        if(findFilmById(film.getFilmId()) == null)
//            return null;
//        return filmRepository.update(film);
//    }
    public List<FilmDto> findAllFilms(){
        return filmService.findAllFilms();
    }

    public List<ActorDto> findFilmActors(@WebParam(name = "id")Short filmId){
        return filmService.findFilmActors(filmId);
    }
    public String findFilmLanguage(@WebParam(name = "id")Short filmId){
        return findFilmById(filmId).getLanguage();
    }
    public String findFilmOriginalLanguage(@WebParam(name = "id")Short filmId){
        return findFilmById(filmId).getOriginalLanguage();
    }
    public List<FilmDto> findFilmByReleaseYear(@WebParam(name = "id")Date releaseYear){
        return filmService.findFilmByReleaseYear(releaseYear);
    }
    public List<String> findFilmCategories(@WebParam(name = "id")Short filmId){
        return filmService.findFilmCategories(filmId);
    }
    public List<FilmDto> findByRating(@WebParam(name = "rating")String rating){
        return filmService.findByRating(rating);

    }

    public List<FilmDto> findByRentalDuration(@WebParam(name = "rentalDuration")String rentalDuration){
        return filmService.findByRentalDuration(rentalDuration);

    }
    public List<FilmDto> findFilmByReplacementCost(@WebParam(name = "replacementCost")String replacementCost){
        return filmService.findFilmByReplacementCost(replacementCost);

    }
    public List<FilmDto> findFilmByLength(@WebParam(name = "length")Short length){
        return filmService.findFilmByLength(length);

    }
    // return customers bought film
    public List<CustomerDto> findFilmCustomers(@WebParam(name = "id")Short filmId){
        return filmService.findFilmCustomers(filmId);
    }
    //    // return stores
    public List<StoreDto> findFilmStores(@WebParam(name = "id")Short filmId){
        return filmService.findFilmStores(filmId);

    }

    public int addActorToFilm(@WebParam(name = "filmId") Short filmId, @WebParam(name = "actorId")Short actorId ){
        return filmService.addActorToFilm(filmId,actorId);
    }

    public int addCategoryToFilm(@WebParam(name = "filmId")Short filmId,@WebParam(name = "categoryId")Short categoryId){
        return filmService.addCategoryToFilm(filmId,categoryId);
    }
    public List<FilmDto> findFilmByTitle(@WebParam(name = "title")String title){
        return filmService.findFilmByTitle(title);
    }
    public FilmDtoWithCountForStore findNumberOfActors(@WebParam(name = "id")Short filmId){
        return filmService.findNumberOfActors(filmId);
    }

    public long findFilmCountInStock(@WebParam(name = "id")Short filmId){
        return filmService.findFilmCountInStock(filmId);
    }

    public long findCountFilmRentals(@WebParam(name = "id") Short filmId){
        return filmService.findCountFilmRentals(filmId);
    }
    public List<StoreInventoryDto> findWhereFilmAvailable(@WebParam(name = "id") Short filmId){
        return filmService.findWhereFilmAvailable(filmId);
    }
    public List<StoreDto> findFilmAvailableInWhichStore(@WebParam(name = "id") Short filmId){
        return filmService.findFilmAvailableInWhichStore(filmId);
    }
}
