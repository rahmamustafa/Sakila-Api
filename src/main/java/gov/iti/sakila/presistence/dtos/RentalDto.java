package gov.iti.sakila.presistence.dtos;

import gov.iti.sakila.presistence.dtos.customer.CustomerDto;
import gov.iti.sakila.presistence.dtos.film.FilmDto;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * A DTO for the {@link gov.iti.sakila.presistence.entities.Rental} entity
 */
@Data
@NoArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
@ToString
public class RentalDto implements Serializable {
    private Integer rentalId;
    private Date rentalDate;
    private Date returnDate;
    private Date lastUpdate;
    private CustomerDto customerId;
    private FilmDto filmId;
//    private List<BigDecimal> paymentListAmounts;
}