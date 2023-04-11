package gov.iti.sakila.presistence.dtos;

import gov.iti.sakila.presistence.dtos.customer.CustomerDto;
import gov.iti.sakila.presistence.dtos.film.FilmDto;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * A DTO for the {@link gov.iti.sakila.presistence.entities.Inventory} entity
 */
@Data
@NoArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
@ToString
@XmlRootElement
public class InventoryDto implements Serializable {
    private FilmDto film;
    private CustomerDto customer;
}