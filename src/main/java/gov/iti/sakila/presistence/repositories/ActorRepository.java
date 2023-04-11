package gov.iti.sakila.presistence.repositories;

import java.sql.Date;
import java.time.Instant;
import java.util.List;

import gov.iti.sakila.presistence.entities.Actor;
import gov.iti.sakila.presistence.entities.Film;
import gov.iti.sakila.presistence.entities.FilmActor;
import jakarta.ws.rs.NotFoundException;

public class ActorRepository extends GenericRepository<Actor, Short> {

    FilmActorRepository filmActorRepository = new FilmActorRepository();
    FilmRepository filmRepository;

    public ActorRepository(FilmRepository filmRepository) {
        super(Actor.class);
        this.filmRepository = filmRepository;
    }

    public ActorRepository(){
        super(Actor.class);
        filmRepository = new FilmRepository(this);
    }
    public List<Actor> findByFirstName(String firstName){
        return findListObjByNamedQuery("Actor.findByFirstName", "firstName", firstName);
    }
    public List<Film> findActorFilms(Short actorId){
        Actor actor = findById(actorId);
        if(actor==null)
            return null;
        return actor.getFilmActorList().stream().map(FilmActor::getFilm).toList();
    }
    public int addFilmToActor(Short actorId , Short filmId){
        if(findById(actorId)==null)
            throw new NotFoundException("Actor Not Found");;
        if(filmRepository.findById(filmId)==null)
            throw new NotFoundException("Film Not Found");
        FilmActor filmActor  = new FilmActor(actorId, filmId);
        if(filmActorRepository.findById(filmActor.getFilmActorPK())!=null)
            throw new IllegalArgumentException("This actor in this film");
        filmActor.setLastUpdate(Date.from(Instant.now()));
        filmActorRepository.create(filmActor);
        return 1;
    }

}
