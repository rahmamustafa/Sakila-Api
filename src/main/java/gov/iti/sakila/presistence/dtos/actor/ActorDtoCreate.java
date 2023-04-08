package gov.iti.sakila.presistence.dtos.actor;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
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
    private String firstName;
    private String lastName;
    public ActorDtoCreate(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}