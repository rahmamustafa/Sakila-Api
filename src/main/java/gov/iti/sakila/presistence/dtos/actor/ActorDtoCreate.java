package gov.iti.sakila.presistence.dtos.actor;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * A DTO for the {@link gov.iti.sakila.presistence.entities.Actor} entity
 */
@Data
@NoArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
@ToString
public class ActorDtoCreate implements Serializable {
    @XmlElement(required=true)
    private String firstName;
    @XmlElement(required=true)
    private String lastName;
    public ActorDtoCreate(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}