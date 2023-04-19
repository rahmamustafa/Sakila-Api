package gov.iti.sakila.business.services.servicesimpl;

import gov.iti.sakila.business.mappers.ActorMapper;
import gov.iti.sakila.business.mappers.FilmMapper;
import gov.iti.sakila.business.util.ValidatorHandler;
import gov.iti.sakila.business.services.dtos.actor.ActorDto;
import gov.iti.sakila.business.services.dtos.actor.ActorDtoCreate;
import gov.iti.sakila.business.services.dtos.film.FilmDto;
import gov.iti.sakila.presistence.dbconnection.JpaManagerSingleton;
import gov.iti.sakila.presistence.entities.Actor;
import gov.iti.sakila.presistence.entities.Film;
import gov.iti.sakila.presistence.repositories.ActorRepository;
import gov.iti.sakila.presistence.repositories.repositoriesimpl.ActorRepositoryImpl;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.*;
import lombok.NonNull;

import java.sql.Date;
import java.time.Instant;
import java.util.List;

public class ActorServiceImpl implements gov.iti.sakila.business.services.ActorService {
    private final ActorRepository actorRepository = new ActorRepositoryImpl();
    private final ValidatorHandler validatorHandler = ValidatorHandler.getInstance();
    private final String ERROR_MESSAGE = "Please Enter Valid Data";

    @Override
    public ActorDto createActor(ActorDtoCreate actorDtoCreate){
            validatorHandler.validate(actorDtoCreate); //throw exception if failed
            Actor actor = ActorMapper.INSTANCE.actorDtoCreateToActor(actorDtoCreate);
            actor.setLastUpdate(Date.from(Instant.now()));
            actor = actorRepository.create(actor);
            return ActorMapper.INSTANCE.actorToActorDto(actor);
    }

    @Override
    public ActorDto findActorById(@NonNull Short actorId){
        if(actorId<=0)
            throw new IllegalArgumentException(ERROR_MESSAGE);
        Actor actor  = actorRepository.findById(actorId);
        return ActorMapper.INSTANCE.actorToActorDto(actor);
    }
    @Override
    public int deleteActorById(@NonNull Short actorId){
        if(actorId<=0)
            throw new IllegalArgumentException(ERROR_MESSAGE);
        return actorRepository.deleteById(actorId);
    }
    @Override
    public List<ActorDto> findAllActors(){
        List<Actor> actors  = actorRepository.findAll();
        if(actors.size()==0)
            throw new NotFoundException("Not Found");
        return actors.stream().map(ActorMapper.INSTANCE::actorToActorDto).toList();
    }
    @Override
    public List<ActorDto> findActorByFirstName(@NonNull String firstName){
        if(firstName.isBlank())
            throw new IllegalArgumentException(ERROR_MESSAGE);
        List<Actor> actors  = actorRepository.findByFirstName(firstName);
        return actors.stream().map(ActorMapper.INSTANCE::actorToActorDto).toList();


    }
    @Override
    public List<FilmDto> findActorFilms(@NonNull Short actorId){
        if(actorId<=0)
            throw new IllegalArgumentException(ERROR_MESSAGE);
        List<Film> films = actorRepository.findActorFilms(actorId);
        if(films.size()==0)
            throw new NotFoundException("Not Found");
        return films.stream().map(FilmMapper.INSTANCE::filmToFilmDto).toList();
    }

    @Override
    public int addFilmToActor(@NotNull Short actorId, @NonNull Short filmId){
        if(actorId<=0  || filmId<=0)
            throw new IllegalArgumentException(ERROR_MESSAGE);
        return actorRepository.addFilmToActor(actorId,filmId);
    }
}