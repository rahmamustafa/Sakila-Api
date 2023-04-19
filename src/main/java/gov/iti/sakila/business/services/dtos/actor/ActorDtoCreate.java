package gov.iti.sakila.business.services.dtos.actor;

import jakarta.validation.constraints.NotBlank;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * A DTO for the {@link gov.iti.sakila.presistence.entities.Actor} entity
 */
@Data
@NoArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD) //try to change
@ToString
@XmlRootElement
public class ActorDtoCreate implements Serializable {
    @NotBlank(message = "please enter first name")
    @XmlElement(required=true)
    private String firstName;
    @NotBlank(message = "please enter last name")
    @XmlElement(required=true)
    private String lastName;

}