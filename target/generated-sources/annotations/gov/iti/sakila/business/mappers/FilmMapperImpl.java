package gov.iti.sakila.business.mappers;

import gov.iti.sakila.presistence.dtos.film.FilmDto;
import gov.iti.sakila.presistence.entities.Film;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-04-08T03:35:00+0200",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.5 (Oracle Corporation)"
)
public class FilmMapperImpl implements FilmMapper {

    @Override
    public FilmDto filmToFilmDto(Film film) {
        if ( film == null ) {
            return null;
        }

        FilmDto filmDto = new FilmDto();

        filmDto.setLanguage( mapLanguageName( film.getLanguageId() ) );
        filmDto.setOriginalLanguage( mapOriginalLanguageName( film.getOriginalLanguageId() ) );
        filmDto.setFilmId( film.getFilmId() );
        filmDto.setTitle( film.getTitle() );
        filmDto.setDescription( film.getDescription() );
        if ( film.getReleaseYear() != null ) {
            filmDto.setReleaseYear( LocalDateTime.ofInstant( film.getReleaseYear().toInstant(), ZoneOffset.UTC ).toLocalDate() );
        }
        filmDto.setRentalDuration( film.getRentalDuration() );
        filmDto.setRentalRate( film.getRentalRate() );
        filmDto.setLength( film.getLength() );
        filmDto.setReplacementCost( film.getReplacementCost() );
        filmDto.setRating( film.getRating() );
        filmDto.setSpecialFeatures( film.getSpecialFeatures() );
        if ( film.getLastUpdate() != null ) {
            filmDto.setLastUpdate( LocalDateTime.ofInstant( film.getLastUpdate().toInstant(), ZoneOffset.UTC ).toLocalDate() );
        }

        return filmDto;
    }

    @Override
    public Film filmDtoToFilm(FilmDto filmDto) {
        if ( filmDto == null ) {
            return null;
        }

        Film film = new Film();

        film.setFilmId( filmDto.getFilmId() );
        film.setTitle( filmDto.getTitle() );
        film.setDescription( filmDto.getDescription() );
        if ( filmDto.getReleaseYear() != null ) {
            film.setReleaseYear( Date.from( filmDto.getReleaseYear().atStartOfDay( ZoneOffset.UTC ).toInstant() ) );
        }
        film.setRentalDuration( filmDto.getRentalDuration() );
        film.setRentalRate( filmDto.getRentalRate() );
        film.setLength( filmDto.getLength() );
        film.setReplacementCost( filmDto.getReplacementCost() );
        film.setRating( filmDto.getRating() );
        film.setSpecialFeatures( filmDto.getSpecialFeatures() );

        return film;
    }
}
