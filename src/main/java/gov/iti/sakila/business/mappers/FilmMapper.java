package gov.iti.sakila.business.mappers;

import gov.iti.sakila.business.services.dtos.film.FilmDto;
import gov.iti.sakila.business.services.dtos.film.FilmDtoCreate;
import gov.iti.sakila.presistence.entities.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Mapper
public interface FilmMapper {

    FilmMapper INSTANCE = Mappers.getMapper( FilmMapper.class );
    @Mapping(source = "languageId", target = "language", qualifiedByName = "mapLanguageName")
    @Mapping(source = "filmActorList", target = "actorsNumber", qualifiedByName = "mapActorsNumber")
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

    @Named("mapActorsNumber")
    default int mapNumberOfFilms(List<FilmActor> filmActorList) {
        return filmActorList.size();
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
