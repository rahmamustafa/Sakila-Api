package gov.iti.sakila.business.endpoints.soap;
import gov.iti.sakila.business.services.ActorService;
import gov.iti.sakila.business.util.ValidatorHandler;
import gov.iti.sakila.presistence.dtos.actor.ActorDto;
import gov.iti.sakila.presistence.dtos.actor.ActorDtoCreate;
import gov.iti.sakila.presistence.dtos.film.FilmDto;

import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import java.util.List;
@WebService
public class ActorEndPoint {
    ActorService actorService = new ActorService();
    public ActorDto createActor(@WebParam(name = "actor") ActorDtoCreate actorDtoCreate){
        return actorService.createActor(actorDtoCreate);
    }

    public ActorDto findActorById(@WebParam(name = "id") Short actorId){
        return actorService.findActorById(actorId);

    }
    public int deleteActorById(@WebParam(name = "id")Short actorId){
        return actorService.deleteActorById(actorId);
    }
    public List<ActorDto> findAllActors(){
        return actorService.findAllActors();
    }
    public List<ActorDto> findActorByFirstName(@WebParam(name = "firstName")String firstName){
        return actorService.findActorByFirstName(firstName);


    }
    public List<FilmDto> findActorFilms(@WebParam(name = "id")Short actorId){
        return actorService.findActorFilms(actorId);
    }

    public int addFilmToActor(@WebParam(name = "actorId")Short actorId ,@WebParam(name = "filmId") Short filmId){
        return actorService.addFilmToActor(actorId,filmId);


    }
}
