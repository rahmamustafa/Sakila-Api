package gov.iti.sakila.presistence.dtos.film;

import gov.iti.sakila.presistence.entities.Language;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

import java.time.Year;
import java.util.Date;

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
//    private int releaseYear;
    private Short rentalDuration;
    private BigDecimal rentalRate;
    private Short length;
    private BigDecimal replacementCost;
    private String rating;
    private String specialFeatures;
    private Short languageId;
    private Short originalLanguageId;

    public FilmDtoCreate(String title, String description,
                         short rentalDuration, BigDecimal rentalRate, Short length, BigDecimal replacementCost,
                         String rating, String specialFeatures, Short language) {
        this.title = title;
        this.description = description;
        this.rentalDuration = rentalDuration;
        this.rentalRate = rentalRate;
        this.length = length;
        this.replacementCost = replacementCost;
        this.rating = rating;
        this.specialFeatures = specialFeatures;
        this.languageId = language;
    }


}