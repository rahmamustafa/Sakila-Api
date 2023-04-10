package gov.iti.sakila.presistence.repositories;

import java.time.Instant;
import java.util.Date;
import java.util.List;

import gov.iti.sakila.presistence.dtos.actor.ActorDto;
import gov.iti.sakila.presistence.dtos.film.FilmDtoWithCountForStore;
import gov.iti.sakila.presistence.dtos.store.StoreDto;
import gov.iti.sakila.presistence.entities.Actor;
import gov.iti.sakila.presistence.entities.Film;
import gov.iti.sakila.presistence.entities.FilmActor;
import jakarta.jws.WebParam;
import jakarta.persistence.TypedQuery;

public class FilmRepository extends GenericRepository<Film , Short> {

    FilmActorRepository filmActorRepository = new FilmActorRepository();
    ActorRepository actorRepository;
    public FilmRepository(ActorRepository actorRepository){
        super(Film.class);
        this.actorRepository = actorRepository;
    }
    public FilmRepository(){
        super(Film.class);
        actorRepository = new ActorRepository(this);
    }
    public List<Film> findByReleaseYear(Date releaseYear){
        return findListObjByNamedQuery("Film.findByReleaseYear", "releaseYear", releaseYear);

    }
    public List<Film> findByTitle(String title){
        return findListObjByNamedQuery("Film.findByTitle", "title", title);

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
        if(film==null)
            return null;
       return film.getFilmActorList().stream().map(FilmActor::getActor).toList();
    }
    public int addActorToFilm( Short filmId, Short actorId){
      return actorRepository.addFilmToActor(actorId,filmId);
    }
    public FilmDtoWithCountForStore findNumberOfActor(Short filmId) {

/*        String q = "SELECT NEW gov.iti.sakila.presistence.dtos.film.FilmDtoWithCountForStore(f, count(a)," +
                " ( SELECT ac2 " +
                " from Actor ac2 " +
                "where ac2 = a.actor) " +
                " )  " +
                "FROM Film f " +
                "JOIN f.filmActorList a " +
                "WHERE f.filmId = :filmId "
               + "GROUP BY f"*/
                ;
        /*String q = "SELECT NEW gov.iti.sakila.presistence.dtos.film.FilmDtoWithCountForStore(f, count(a), collect(ac)) " +
                "FROM Film f " +
                "JOIN f.filmActorList a " +
                "JOIN  a.actor ac "+
                "WHERE f.filmId = :filmId " +
                "GROUP BY f";*/

        String q = "SELECT NEW gov.iti.sakila.presistence.dtos.film.FilmDtoWithCountForStore(f, count(a)) " +
                "FROM Film f " +
                "JOIN f.filmActorList a " +
                "WHERE f.filmId = :filmId " +
                "GROUP BY f";
        TypedQuery<FilmDtoWithCountForStore> query = entityManager.createQuery(q , FilmDtoWithCountForStore.class);
        query.setParameter("filmId", filmId);
        List<FilmDtoWithCountForStore> resultList = query.getResultList();
        FilmDtoWithCountForStore resultMap = (FilmDtoWithCountForStore) resultList.get(0);

        return resultMap;
    }

}
