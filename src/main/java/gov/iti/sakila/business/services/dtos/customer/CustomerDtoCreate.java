package gov.iti.sakila.business.services.dtos.customer;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
public class CustomerDtoCreate implements Serializable {
    @NotBlank(message = "please enter first name")
    @XmlElement(required=true)
    private String firstName;
    @XmlElement(required=true)
    @NotBlank(message = "please enter last name")
    private String lastName;
    private String email;
    private Date lastUpdate;
    @NotNull(message = "please enter active status")
    @XmlElement(required=true)
    private boolean active;
    @Min(value = 1,message = "please enter store id")
    @XmlElement(required=true)
    private Short store;
//    @Size(min = 1,message = "please enter address id")
    @Min(value = 1,message = "please enter address id")
    @XmlElement(required=true)
    private Short address;

}