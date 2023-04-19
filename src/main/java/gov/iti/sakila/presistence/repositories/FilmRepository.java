package gov.iti.sakila.presistence.repositories;

import java.util.Date;
import java.util.List;

import gov.iti.sakila.business.services.dtos.film.FilmDtoWithCountForStore;
import gov.iti.sakila.business.services.dtos.store.StoreInventoryDto;
import gov.iti.sakila.presistence.entities.*;

public interface FilmRepository extends GenericRepository<Film , Short> {

        List<Film> findByReleaseYear(Date releaseYear);
        List<Film> findByTitle(String title);
        List<Film> findByRating(String rating);
        List<Film> findByRentalDuration(String rentalDuration);
        List<Film> findByReplacementCost(String replacementCost);
        List<Film> findByLength(Short length);

        List<Actor> findFilmActors(Short filmId);

        int addActorToFilm(Short filmId, Short actorId);

        FilmDtoWithCountForStore findNumberOfActors(Short filmId);


        long findFilmCountInStock(Short filmId);

        long filmCountInStockV2(Short filmId);


        long findFilmRentalCount(Short filmId);

        List<StoreInventoryDto> findWhereFilmAvailable(Short filmId);

        List<Store> findFilmAvailableInWhichStore(Short filmId);


}
