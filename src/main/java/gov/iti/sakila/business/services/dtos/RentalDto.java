package gov.iti.sakila.business.services.dtos;

import gov.iti.sakila.business.services.dtos.customer.CustomerDto;
import gov.iti.sakila.business.services.dtos.film.FilmDto;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * A DTO for the {@link gov.iti.sakila.presistence.entities.Rental} entity
 */
@Data
@NoArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
@ToString
@XmlRootElement
public class RentalDto implements Serializable {
    private Integer rentalId;
    private Date rentalDate;
    private Date returnDate;
    private Date lastUpdate;
    private CustomerDto customerId;
    private FilmDto filmId;
//    private List<BigDecimal> paymentListAmounts;
}