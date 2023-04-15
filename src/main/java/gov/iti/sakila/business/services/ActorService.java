package gov.iti.sakila.business.services;

import gov.iti.sakila.business.mappers.ActorMapper;
import gov.iti.sakila.business.mappers.FilmMapper;
import gov.iti.sakila.business.util.ValidatorHandler;
import gov.iti.sakila.presistence.dtos.actor.ActorDto;
import gov.iti.sakila.presistence.dtos.actor.ActorDtoCreate;
import gov.iti.sakila.presistence.dtos.film.FilmDto;
import gov.iti.sakila.presistence.entities.Actor;
import gov.iti.sakila.presistence.entities.Film;
import gov.iti.sakila.presistence.repositories.ActorRepository;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.*;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import lombok.NonNull;

import java.sql.Date;
import java.time.Instant;
import java.util.List;

@Path("actors")
public class ActorService {
    private final ActorRepository actorRepository = new ActorRepository();
    private final ValidatorHandler validatorHandler = ValidatorHandler.getInstance();
    private final String ERROR_MESSAGE = "Please Enter Valid Data";


    @Path("add")
    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_JSON)
    public ActorDto createActor(ActorDtoCreate actorDtoCreate){
            validatorHandler.validate(actorDtoCreate); //throw exception if failed
            Actor actor = ActorMapper.INSTANCE.actorDtoCreateToActor(actorDtoCreate);
            actor.setLastUpdate(Date.from(Instant.now()));
            actor = actorRepository.create(actor);
            return ActorMapper.INSTANCE.actorToActorDto(actor);
    }

    public ActorDto findActorById(@NonNull  Short actorId){
        if(actorId<=0)
            throw new IllegalArgumentException(ERROR_MESSAGE);
        Actor actor  = actorRepository.findById(actorId);
        if(actor==null)
            throw new NotFoundException("Actor Not Found");
        return ActorMapper.INSTANCE.actorToActorDto(actor);
    }
    public int deleteActorById(@NonNull Short actorId){
        if(actorId<=0)
            throw new IllegalArgumentException(ERROR_MESSAGE);
        return actorRepository.deleteById(actorId);
    }
    public List<ActorDto> findAllActors(){
        List<Actor> actors  = actorRepository.findAll();
        if(actors.size()==0)
            throw new NotFoundException("Not Found");
        return actors.stream().map(ActorMapper.INSTANCE::actorToActorDto).toList();
    }
    public List<ActorDto> findActorByFirstName(@NonNull String firstName){
        if(firstName.isBlank())
            throw new IllegalArgumentException(ERROR_MESSAGE);
        List<Actor> actors  = actorRepository.findByFirstName(firstName);
        if(actors.size()==0)
            throw new NotFoundException("Actor Not Found");
        return actors.stream().map(ActorMapper.INSTANCE::actorToActorDto).toList();


    }
    public List<FilmDto> findActorFilms(@NonNull Short actorId){
        if(actorId<=0)
            throw new IllegalArgumentException(ERROR_MESSAGE);
        List<Film> films = actorRepository.findActorFilms(actorId);
        if(films.size()==0)
            throw new NotFoundException("Not Found");
        return films.stream().map(FilmMapper.INSTANCE::filmToFilmDto).toList();
    }

    public int addFilmToActor(@NotNull Short actorId,@NonNull  Short filmId){
        if(actorId<=0  || filmId<=0)
            throw new IllegalArgumentException(ERROR_MESSAGE);
        return actorRepository.addFilmToActor(actorId,filmId);
    }
}