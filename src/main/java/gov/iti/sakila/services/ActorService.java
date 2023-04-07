package gov.iti.sakila.services;

import java.util.Collections;
import java.util.List;

import gov.iti.sakila.presistence.entities.Actor;
import gov.iti.sakila.presistence.entities.Film;
import gov.iti.sakila.presistence.repositories.ActorRepository;

public class ActorService {
    private ActorRepository actorRepository = new ActorRepository();

    public Actor create(Actor actor){
        return actorRepository.create(actor);
    }
    public Actor findById(Short actorId){
        return actorRepository.findById(actorId);
    }
    public Actor deleteById(Short actorId){
        return actorRepository.deleteById(actorId);
    }
    public Actor update(Actor actor){
        if(findById(actor.getActorId()) == null)
            return null;
        return actorRepository.update(actor);
    }
    public List<Actor> findAll(){
        return Collections.unmodifiableList(actorRepository.findAll());
    }
    public List<Actor> findByFirstName(String firstName){
        return actorRepository.findByFirstName(firstName);

    }
    public List<Film> findActorFilms(Short actorId){
        return actorRepository.findActorFilms(actorId);
    }
    


}