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
//    @Mapping(source = "releaseYear", target = "releaseYear", qualifiedByName = "mapReleaseYear")
    @Mapping(source = "originalLanguageId", target = "originalLanguage", qualifiedByName = "mapOriginalLanguageName")
    FilmDto filmToFilmDto(Film film);
    @Mapping(source = "originalLanguageId" , target = "originalLanguageId", ignore = true)
    @Mapping(source = "languageId" , target = "languageId", ignore = true)
//    @Mapping(source = "releaseYear", target = "releaseYear", qualifiedByName = "mapYear")
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
    @Named("mapReleaseYear")
    default Integer mapReleaseYear(Date releaseYear) {
        if(releaseYear==null)
            return null;
        return releaseYear.getYear();
    }
    @Named("mapYear")
    default Date mapYear(int releaseYear) {
        LocalDate localDate = LocalDate.of(releaseYear, 1, 1); // 1st January of the release year
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

}
