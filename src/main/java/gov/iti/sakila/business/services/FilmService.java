package gov.iti.sakila.business.services;

import gov.iti.sakila.business.services.dtos.actor.ActorDto;
import gov.iti.sakila.business.services.dtos.customer.CustomerDto;
import gov.iti.sakila.business.services.dtos.film.FilmDto;
import gov.iti.sakila.business.services.dtos.film.FilmDtoCreate;
import gov.iti.sakila.business.services.dtos.film.FilmDtoWithCountForStore;
import gov.iti.sakila.business.services.dtos.store.StoreDto;
import gov.iti.sakila.business.services.dtos.store.StoreInventoryDto;
import lombok.NonNull;

import java.util.Date;
import java.util.List;

public interface FilmService {
    FilmDto createFilm(FilmDtoCreate filmDtoCreate);

    FilmDto findFilmById(@NonNull Short filmId);

    int deleteFilmById(@NonNull Short filmId);

    //    public Film update(Film film){
    //        if(findFilmById(film.getFilmId()) == null)
    //            return null;
    //        return filmRepository.update(film);
    //    }
    List<FilmDto> findAllFilms();

    List<ActorDto> findFilmActors(@NonNull Short filmId);

    String findFilmLanguage(@NonNull Short filmId);

    String findFilmOriginalLanguage(@NonNull Short filmId);

    List<FilmDto> findFilmByReleaseYear(@NonNull Date releaseYear);

    List<String> findFilmCategories(@NonNull Short filmId);

    List<FilmDto> findByRating(@NonNull String rating);

    List<FilmDto> findByRentalDuration(@NonNull String rentalDuration);

    List<FilmDto> findFilmByReplacementCost(@NonNull String replacementCost);

    List<FilmDto> findFilmByLength(@NonNull Short length);

    // return customers bought film
    List<CustomerDto> findFilmCustomers(@NonNull Short filmId);

    //    // return stores
    List<StoreDto> findFilmStores(@NonNull Short filmId);

    int addActorToFilm(@NonNull Short filmId, @NonNull Short actorId);

    int addCategoryToFilm(@NonNull Short filmId, @NonNull Short categoryId);

    List<FilmDto> findFilmByTitle(@NonNull String title);

    FilmDtoWithCountForStore findNumberOfActors(@NonNull Short filmId);

    long findFilmCountInStock(@NonNull Short filmId);

    long findFilmRentalCount(@NonNull Short filmId);

    List<StoreInventoryDto> findWhereFilmAvailable(@NonNull Short filmId);

    List<StoreDto> findFilmAvailableInWhichStore(@NonNull Short filmId);
}
