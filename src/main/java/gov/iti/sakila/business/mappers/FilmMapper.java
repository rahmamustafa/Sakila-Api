package gov.iti.sakila.business.mappers;

import gov.iti.sakila.presistence.dtos.actor.ActorDto;
import gov.iti.sakila.presistence.dtos.film.FilmDto;
import gov.iti.sakila.presistence.dtos.film.FilmDtoCreate;
import gov.iti.sakila.presistence.entities.Actor;
import gov.iti.sakila.presistence.entities.Film;
import gov.iti.sakila.presistence.entities.Language;
import lombok.Data;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Mapper
public interface FilmMapper {

    FilmMapper INSTANCE = Mappers.getMapper( FilmMapper.class );
    @Mapping(source = "languageId", target = "language", qualifiedByName = "mapLanguageName")
    @Mapping(source = "originalLanguageId", target = "originalLanguage", qualifiedByName = "mapOriginalLanguageName")
    FilmDto filmToFilmDto(Film film);
    @Mapping(source = "lastUpdate" , target = "lastUpdate", ignore = true)
    Film filmDtoToFilm(FilmDto filmDto);
    @Mapping(source = "lastUpdate" , target = "lastUpdate", ignore = true)
    Film filmDtoCreateToFilm(FilmDtoCreate filmDtoCreate);

    @Named("mapLanguageName")
    default String mapLanguageName(Language language) {
        return language.getName();
    }
    @Named("mapOriginalLanguageName")
    default String mapOriginalLanguageName(Language language) {
        if(language!=null)
            return language.getName();
        return null;
    }

}
