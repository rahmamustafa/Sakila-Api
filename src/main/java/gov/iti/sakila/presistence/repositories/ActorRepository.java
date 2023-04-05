package gov.iti.sakila.presistence.repositories;

import gov.iti.sakila.presistence.entities.Actor;

public class ActorRepository extends GenericRepository<Actor, Short> {

    public ActorRepository(){
        super(Actor.class);
    }
    

}
