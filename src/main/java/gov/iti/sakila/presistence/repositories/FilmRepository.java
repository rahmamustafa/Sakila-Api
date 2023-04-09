package gov.iti.sakila.presistence.repositories;

import java.time.Instant;
import java.util.Date;
import java.util.List;

import gov.iti.sakila.presistence.dtos.actor.ActorDto;
import gov.iti.sakila.presistence.dtos.store.StoreDto;
import gov.iti.sakila.presistence.entities.Actor;
import gov.iti.sakila.presistence.entities.Film;
import gov.iti.sakila.presistence.entities.FilmActor;
import jakarta.jws.WebParam;

public class FilmRepository extends GenericRepository<Film , Short> {

    FilmActorRepository filmActorRepository = new FilmActorRepository();

    public FilmRepository(){
        super(Film.class);
    }
    public List<Film> findByReleaseYear(Date releaseYear){
        return findListObjByNamedQuery("Film.findByReleaseYear", "releaseYear", releaseYear);

    }
    public List<Film> findByRating(String rating){
        return findListObjByNamedQuery("Film.findByRating", "rating", rating);
    }
    public List<Film> findByRentalDuration(String rentalDuration){
        return findListObjByNamedQuery("Film.findByRentalDuration", "rentalDuration", rentalDuration);

    }
    public List<Film> findByReplacementCost(String replacementCost){
        return findListObjByNamedQuery("Film.findByReplacementCost", "replacementCost", replacementCost);

    }
    public List<Film> findByLength(Short length){
        return findListObjByNamedQuery("Film.findByLength", "length", length);

    }
    public List<Actor> findFilmActors(Short filmId){
        Film film = findById(filmId);
       return film.getFilmActorList().stream().map(FilmActor::getActor).toList();
    }
    public List<Actor> addActorToFilm( Short filmId, Short actorId){
        FilmActor filmActor  = new FilmActor(actorId, filmId);
        filmActor.setLastUpdate(Date.from(Instant.now()));
        filmActorRepository.create(filmActor);
        return findFilmActors(actorId);
    }


}
