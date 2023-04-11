package gov.iti.sakila.presistence.dtos.actor;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

/**
 * A DTO for the {@link gov.iti.sakila.presistence.entities.Actor} entity
 */
@Data
@NoArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
@ToString
@XmlRootElement
public class ActorDto implements Serializable {
    private Short actorId;
    private String firstName;
    private String lastName;
    private Date lastUpdate;

}