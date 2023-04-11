package gov.iti.sakila.presistence.dtos.film;

import gov.iti.sakila.presistence.entities.Language;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
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
@XmlRootElement
public class FilmDtoCreate implements Serializable {
    @XmlElement(required=true)
    private String title;
    private String description;
//    private int releaseYear;
    @XmlElement(required=true)
    private Short rentalDuration;
    @XmlElement(required=true)
    private BigDecimal rentalRate;
    private Short length;
    @XmlElement(required=true)
    private BigDecimal replacementCost;
    private String specialFeatures;
    @XmlElement(required=true)
    private Short languageId;
    private Short originalLanguageId;

//    public FilmDtoCreate(String title, String description,
//                         short rentalDuration, BigDecimal rentalRate, Short length, BigDecimal replacementCost,
//                         String rating, String specialFeatures, Short language) {
//        this.title = title;
//        this.description = description;
//        this.rentalDuration = rentalDuration;
//        this.rentalRate = rentalRate;
//        this.length = length;
//        this.replacementCost = replacementCost;
//        this.rating = rating;
//        this.specialFeatures = specialFeatures;
//        this.languageId = language;
//    }


}