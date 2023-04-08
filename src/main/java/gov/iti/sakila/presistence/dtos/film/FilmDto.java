package gov.iti.sakila.presistence.dtos.film;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
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

    public FilmDto(String title, String description,
                   short rentalDuration, BigDecimal rentalRate, Short length, BigDecimal replacementCost,
                   String rating, String specialFeatures, Date lastUpdate, String language) {
        this.title = title;
        this.description = description;
        this.rentalDuration = rentalDuration;
        this.rentalRate = rentalRate;
        this.length = length;
        this.replacementCost = replacementCost;
        this.rating = rating;
        this.specialFeatures = specialFeatures;
        this.lastUpdate = lastUpdate;
        this.language = language;
    }
}