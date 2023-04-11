package gov.iti.sakila.presistence.dtos.film;

import gov.iti.sakila.business.mappers.ActorMapper;
import gov.iti.sakila.business.mappers.FilmMapper;
import gov.iti.sakila.presistence.dtos.actor.ActorDto;
import gov.iti.sakila.presistence.entities.Actor;
import gov.iti.sakila.presistence.entities.Film;
import gov.iti.sakila.presistence.entities.FilmActor;
import jakarta.ws.rs.Path;
import jakarta.xml.bind.annotation.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@NoArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
@ToString
@XmlRootElement
public class FilmDtoWithCountForStore extends FilmDto{

    private long countInStore;
    @XmlElementWrapper(name="actors")
    @XmlElement(name="actor")
    //private List<ActorDto> actors;
    private List<ActorDto> actors;

    public FilmDtoWithCountForStore(Film film, long countInStore, List<Actor> actors) {
        super(FilmMapper.INSTANCE.filmToFilmDto(film));
        this.actors = actors.stream().map(ActorMapper.INSTANCE::actorToActorDto).toList();;
        this.countInStore = countInStore;
    }
    public FilmDtoWithCountForStore(Film film, long countInStore) {
        super(FilmMapper.INSTANCE.filmToFilmDto(film));
        //this.actors = actors.stream().map(ActorMapper.INSTANCE::actorToActorDto).toList();;
        this.countInStore = countInStore;
    }

    @Override
    public String toString() {
        return "FilmDtoWithCountForStore{" +
                "countInStore=" + countInStore +
                ", actors=" + actors +
                "} " + super.toString()+"\n";
    }
}
