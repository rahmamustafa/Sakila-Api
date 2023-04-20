package gov.iti.sakila.presistence.repositories.repositoriesimpl;

import gov.iti.sakila.presistence.entities.Actor;
import gov.iti.sakila.presistence.entities.Film;
import gov.iti.sakila.presistence.entities.FilmActor;
import gov.iti.sakila.presistence.repositories.ActorRepository;

import java.sql.Date;
import java.time.Instant;
import java.util.List;

public class ActorRepositoryImpl extends GenericRepositoryImpl<Actor, Short> implements ActorRepository {

    FilmActorRepositoryImpl filmActorRepository = new FilmActorRepositoryImpl();
    FilmRepositoryImpl filmRepository;

    public ActorRepositoryImpl(FilmRepositoryImpl filmRepository) {
        super(Actor.class);
        this.filmRepository = filmRepository;
    }

    public ActorRepositoryImpl(){
        super(Actor.class);
        filmRepository = new FilmRepositoryImpl(this);
    }
    @Override
    public List<Actor> findByFirstName(String firstName){
        return findListObjByNamedQuery("Actor.findByFirstName", "firstName", firstName);
    }
    @Override

    public List<Film> findActorFilms(Short actorId){
        Actor actor = findById(actorId);
        return actor.getFilmActorList().stream().map(FilmActor::getFilm).toList();
    }
    @Override
    public int addFilmToActor(Short actorId , Short filmId){
        findById(actorId);
        filmRepository.findById(filmId);
        FilmActor filmActor  = new FilmActor(actorId, filmId);
        if(filmActorRepository.checkExist(filmActor.getFilmActorPK()))
            throw new IllegalArgumentException("This actor in this film");
        filmActor.setLastUpdate(Date.from(Instant.now()));
        filmActorRepository.create(filmActor);
        return 1;
    }

}
