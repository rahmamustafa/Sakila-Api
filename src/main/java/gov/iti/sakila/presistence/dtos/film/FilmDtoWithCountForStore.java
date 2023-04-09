package gov.iti.sakila.presistence.dtos.film;

import jakarta.ws.rs.Path;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
@ToString
public class FilmDtoWithCountForStore {
    private Short filmId;
    private String title;
    private String description;
    private String language;
    private Short countInStore;
}
