package gov.iti.sakila.business.mappers;

import gov.iti.sakila.business.services.dtos.actor.ActorDto;
import gov.iti.sakila.business.services.dtos.actor.ActorDtoCreate;
import gov.iti.sakila.presistence.entities.Actor;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ActorMapper {

    ActorMapper INSTANCE = Mappers.getMapper( ActorMapper.class );
        ActorDto actorToActorDto(Actor actor);
    Actor actorDtoToActor(ActorDto actorDto);
    Actor actorDtoCreateToActor(ActorDtoCreate actorDtoCreate);
    }

