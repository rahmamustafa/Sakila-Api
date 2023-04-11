package gov.iti.sakila.presistence.dtos.customer;

import gov.iti.sakila.presistence.dtos.store.StoreDto;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * A DTO for the {@link gov.iti.sakila.presistence.entities.Customer} entity
 */
@Data
@NoArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
@ToString
@XmlRootElement
public class CustomerDto implements Serializable {
    private Short customerId;
    private String firstName;
    private String lastName;
    private String email;
    private boolean active;
    private Date createDate;
    private Date lastUpdate;
}