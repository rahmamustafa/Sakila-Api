package gov.iti.sakila.presistence.repositories;

import java.sql.Date;
import java.time.Instant;
import java.util.List;

import gov.iti.sakila.presistence.entities.Actor;
import gov.iti.sakila.presistence.entities.Film;
import gov.iti.sakila.presistence.entities.FilmActor;
import jakarta.ws.rs.NotFoundException;

public interface ActorRepository extends GenericRepository<Actor, Short> {

    public List<Actor> findByFirstName(String firstName);
    public List<Film> findActorFilms(Short actorId);
    public int addFilmToActor(Short actorId , Short filmId);

}
