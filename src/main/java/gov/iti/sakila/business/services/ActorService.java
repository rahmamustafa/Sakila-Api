package gov.iti.sakila.business.services;

import gov.iti.sakila.business.services.dtos.actor.ActorDto;
import gov.iti.sakila.business.services.dtos.actor.ActorDtoCreate;
import gov.iti.sakila.business.services.dtos.film.FilmDto;
import gov.iti.sakila.presistence.entities.Actor;
import jakarta.validation.constraints.NotNull;
import lombok.NonNull;

import java.util.List;

public interface ActorService{
    ActorDto createActor(ActorDtoCreate actorDtoCreate);

    ActorDto findActorById(@NonNull Short actorId);

    int deleteActorById(@NonNull Short actorId);

    List<ActorDto> findAllActors();

    List<ActorDto> findActorByFirstName(@NonNull String firstName);

    List<FilmDto> findActorFilms(@NonNull Short actorId);

    int addFilmToActor(@NotNull Short actorId, @NonNull Short filmId);
}
