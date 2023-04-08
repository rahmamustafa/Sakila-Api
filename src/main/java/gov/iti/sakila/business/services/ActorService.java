package gov.iti.sakila.business.services;

import java.sql.Date;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import gov.iti.sakila.business.mappers.FilmMapper;
import gov.iti.sakila.presistence.dtos.film.FilmDto;
import gov.iti.sakila.presistence.dtos.actor.ActorDto;
import gov.iti.sakila.presistence.dtos.actor.ActorDtoCreate;
import gov.iti.sakila.presistence.entities.Actor;
import gov.iti.sakila.presistence.entities.Film;
import gov.iti.sakila.business.mappers.ActorMapper;
import gov.iti.sakila.presistence.repositories.ActorRepository;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;

@WebService
public class ActorService {
    private final ActorRepository actorRepository = new ActorRepository();

    public ActorDto createActor(@WebParam(name = "actor") ActorDtoCreate actorDtoCreate){
        Actor actor = ActorMapper.INSTANCE.actorDtoCreateToActor(actorDtoCreate);
        actor.setLastUpdate(Date.from(Instant.now()));
        actor = actorRepository.create(actor);
        return ActorMapper.INSTANCE.actorToActorDto(actor);
    }
    public ActorDto findActorById(@WebParam(name = "id")Short actorId){
       Actor actor  = actorRepository.findById(actorId);
       return ActorMapper.INSTANCE.actorToActorDto(actor);
    }
    public boolean deleteActorById(@WebParam(name = "id")Short actorId){
        return actorRepository.deleteById(actorId);
    }
    public List<ActorDto> findAllActors(){
        List<Actor> actors  = actorRepository.findAll();
        return actors.stream().map(ActorMapper.INSTANCE::actorToActorDto).toList();
    }
    public List<ActorDto> findActorByFirstName(@WebParam(name = "firstName")String firstName){
        List<Actor> actors  = actorRepository.findByFirstName(firstName);
        return actors.stream().map(ActorMapper.INSTANCE::actorToActorDto).toList();


    }
    public List<FilmDto> findActorFilms(@WebParam(name = "id")Short actorId){
        List<Film> films = actorRepository.findActorFilms(actorId);
        return films.stream().map(FilmMapper.INSTANCE::filmToFilmDto).toList();
    }

    public List<FilmDto> addFilmToActor(@WebParam(name = "actorId")Short actorId ,@WebParam(name = "filmId") Short filmId){
        List<Film> films = actorRepository.addFilmToActor(actorId,filmId);
        return films.stream().map(FilmMapper.INSTANCE::filmToFilmDto).toList();

    }
}