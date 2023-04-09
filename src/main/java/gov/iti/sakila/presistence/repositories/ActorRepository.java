package gov.iti.sakila.presistence.repositories;

import java.sql.Date;
import java.time.Instant;
import java.util.List;

import gov.iti.sakila.presistence.entities.Actor;
import gov.iti.sakila.presistence.entities.Film;
import gov.iti.sakila.presistence.entities.FilmActor;

public class ActorRepository extends GenericRepository<Actor, Short> {

    FilmActorRepository filmActorRepository = new FilmActorRepository();
    public ActorRepository(){
        super(Actor.class);
    }
    public List<Actor> findByFirstName(String firstName){
        return findListObjByNamedQuery("Actor.findByFirstName", "firstName", firstName);
    }
    public List<Film> findActorFilms(Short actorId){
        Actor actor = findById(actorId);
        return actor.getFilmActorList().stream().map(FilmActor::getFilm).toList();
    }
    public List<Film> addFilmToActor(Short actorId , Short filmId){
        FilmActor filmActor  = new FilmActor(actorId, filmId);
        filmActor.setLastUpdate(Date.from(Instant.now()));
        filmActorRepository.create(filmActor);
//      entityManager.refresh(filmActor);
        return findActorFilms(actorId);
    }

}
