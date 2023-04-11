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
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import jakarta.validation.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.*;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import lombok.NonNull;

import java.awt.*;
import java.sql.Date;
import java.time.Instant;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Path("actors")
public class ActorService {
    private final ActorRepository actorRepository = new ActorRepository();
    private final ValidatorHandler validatorHandler = ValidatorHandler.getInstance();

    @Path("add")
    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_JSON)
    public ActorDto createActor(ActorDtoCreate actorDtoCreate){
            validatorHandler.isValid(actorDtoCreate);
            Actor actor = ActorMapper.INSTANCE.actorDtoCreateToActor(actorDtoCreate);
            actor.setLastUpdate(Date.from(Instant.now()));
            actor = actorRepository.create(actor);
            return ActorMapper.INSTANCE.actorToActorDto(actor);
    }

    public ActorDto findActorById(@NonNull  Short actorId){
        if(actorId<=0)
            throw new IllegalArgumentException("please enter valid data");
        Actor actor  = actorRepository.findById(actorId);
        if(actor==null)
            throw new NotFoundException("Actor Not Found");
        return ActorMapper.INSTANCE.actorToActorDto(actor);
    }
    public int deleteActorById(@NonNull Short actorId){
        if(actorId<=0)
            throw new IllegalArgumentException("please enter valid data");
        return actorRepository.deleteById(actorId);
    }
    public List<ActorDto> findAllActors(){
        List<Actor> actors  = actorRepository.findAll();
        return actors.stream().map(ActorMapper.INSTANCE::actorToActorDto).toList();
    }
    public List<ActorDto> findActorByFirstName(@NonNull String firstName){
        if(firstName.isBlank())
            throw new IllegalArgumentException("please enter valid data");
        List<Actor> actors  = actorRepository.findByFirstName(firstName);
        if(actors.size()==0)
            throw new NotFoundException("Actor Not Found");
        return actors.stream().map(ActorMapper.INSTANCE::actorToActorDto).toList();


    }
    public List<FilmDto> findActorFilms(@NonNull Short actorId){
        if(actorId<=0)
            throw new IllegalArgumentException("please enter valid data");
        List<Film> films = actorRepository.findActorFilms(actorId);
        if(films==null)
            throw new NotFoundException("Actor Not Found");

        return films.stream().map(FilmMapper.INSTANCE::filmToFilmDto).toList();
    }

    public int addFilmToActor(@NotNull Short actorId,@NonNull  Short filmId){
        if(actorId<=0  || filmId<=0)
            throw new IllegalArgumentException("please enter valid data");
        return actorRepository.addFilmToActor(actorId,filmId);
    }
}