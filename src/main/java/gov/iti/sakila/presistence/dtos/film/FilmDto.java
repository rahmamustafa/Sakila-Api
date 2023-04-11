package gov.iti.sakila.presistence.dtos.film;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

/**
 * A DTO for the {@link gov.iti.sakila.presistence.entities.Film} entity
 */
@Data
@NoArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
@ToString
@XmlRootElement
public class FilmDto implements Serializable {
    private Short filmId;
    private String title;
    private String description;
//    private int releaseYear;
    private short rentalDuration;
    private BigDecimal rentalRate;
    private Short length;
    private BigDecimal replacementCost;
    private String rating;
    private String specialFeatures;
    private Date lastUpdate;
    private String language;
    private String originalLanguage;
    private int actorsNumber;


    public FilmDto(FilmDto film) {
        this.filmId = film.filmId;
        this.title = film.title;
        this.description = film.description;
        this.rentalDuration = film.rentalDuration;
        this.rentalRate = film.rentalRate;
        this.length = film.length;
        this.replacementCost = film.replacementCost;
        this.rating = film.rating;
        this.specialFeatures = film.specialFeatures;
        this.lastUpdate = film.lastUpdate;
        this.language = film.language;
    }

    @Override
    public String toString() {
        return "FilmDto{" +
                "filmId=" + filmId +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", rentalDuration=" + rentalDuration +
                ", rentalRate=" + rentalRate +
                ", length=" + length +
                ", replacementCost=" + replacementCost +
                ", rating='" + rating + '\'' +
                ", specialFeatures='" + specialFeatures + '\'' +
                ", lastUpdate=" + lastUpdate +
                ", language='" + language + '\'' +
                ", originalLanguage='" + originalLanguage + '\'' +
                '}';
    }
}