package gov.iti.sakila.business.services.dtos.film;

import jakarta.validation.constraints.*;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * A DTO for the {@link gov.iti.sakila.presistence.entities.Film} entity
 */
@Data
@NoArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
@ToString
@XmlRootElement
public class FilmDtoCreate implements Serializable {
    @NotBlank(message = "please enter title")
    @XmlElement(required=true)
    private String title;
    private String description;
//    private int releaseYear;
    @Min(value = 1,message = "please enter rental duration")
    @XmlElement(required=true)
    private Short rentalDuration;
    @Min(value = 1,message = "please enter rental rate")
    @XmlElement(required=true)
    private BigDecimal rentalRate;
    private Short length;
//    @Size(min =1,message = "please enter replacement cost")
    @DecimalMin("1")
    @XmlElement(required=true)
    private BigDecimal replacementCost;
    private String specialFeatures;
    @Min(value = 1,message = "please enter language id")
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