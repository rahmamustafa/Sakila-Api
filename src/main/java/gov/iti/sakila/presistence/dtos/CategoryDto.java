package gov.iti.sakila.presistence.dtos;

import gov.iti.sakila.presistence.dtos.film.FilmDto;
import gov.iti.sakila.presistence.entities.Film;
import jakarta.xml.bind.annotation.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * A DTO for the {@link gov.iti.sakila.presistence.entities.Category} entity
 */
@Data
@NoArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
@ToString
@XmlRootElement
public class CategoryDto implements Serializable {
    private Short categoryId;
    private String name;
    private Date lastUpdate;
    @XmlElementWrapper(name="filmList")
    @XmlElement(name="film")
    private List<FilmDto> filmList;
}