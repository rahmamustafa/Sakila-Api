package gov.iti.sakila.presistence.dtos.film;

import gov.iti.sakila.presistence.entities.Language;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * A DTO for the {@link gov.iti.sakila.presistence.entities.Film} entity
 */
@Data
@NoArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
@ToString
public class FilmDtoCreate implements Serializable {
    private Short filmId;
    private String title;
    private String description;
    private LocalDate releaseYear;
    private short rentalDuration;
    private BigDecimal rentalRate;
    private Short length;
    private BigDecimal replacementCost;
    private String rating;
    private String specialFeatures;
    private LocalDate lastUpdate;
    private short languageId;
    private short originalLanguageId;

    public FilmDtoCreate(String title, String description, LocalDate releaseYear,
                         short rentalDuration, BigDecimal rentalRate, Short length, BigDecimal replacementCost,
                         String rating, String specialFeatures, LocalDate lastUpdate, Short language) {
        this.title = title;
        this.description = description;
        this.releaseYear = releaseYear;
        this.rentalDuration = rentalDuration;
        this.rentalRate = rentalRate;
        this.length = length;
        this.replacementCost = replacementCost;
        this.rating = rating;
        this.specialFeatures = specialFeatures;
        this.lastUpdate = lastUpdate;
        this.languageId = language;
    }
}