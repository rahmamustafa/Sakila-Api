package gov.iti.sakila.business.mappers;

import gov.iti.sakila.presistence.dtos.actor.ActorDto;
import gov.iti.sakila.presistence.dtos.actor.ActorDtoCreate;
import gov.iti.sakila.presistence.entities.Actor;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-04-08T03:35:00+0200",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.5 (Oracle Corporation)"
)
public class ActorMapperImpl implements ActorMapper {

    @Override
    public ActorDto actorToActorDto(Actor actor) {
        if ( actor == null ) {
            return null;
        }

        ActorDto actorDto = new ActorDto();

        actorDto.setActorId( actor.getActorId() );
        actorDto.setFirstName( actor.getFirstName() );
        actorDto.setLastName( actor.getLastName() );
        actorDto.setLastUpdate( actor.getLastUpdate() );

        return actorDto;
    }

    @Override
    public Actor actorDtoToActor(ActorDto actorDto) {
        if ( actorDto == null ) {
            return null;
        }

        Actor actor = new Actor();

        actor.setActorId( actorDto.getActorId() );
        actor.setFirstName( actorDto.getFirstName() );
        actor.setLastName( actorDto.getLastName() );
        actor.setLastUpdate( actorDto.getLastUpdate() );

        return actor;
    }

    @Override
    public Actor actorDtoCreateToActor(ActorDtoCreate actorDtoCreate) {
        if ( actorDtoCreate == null ) {
            return null;
        }

        Actor actor = new Actor();

        actor.setFirstName( actorDtoCreate.getFirstName() );
        actor.setLastName( actorDtoCreate.getLastName() );

        return actor;
    }
}
