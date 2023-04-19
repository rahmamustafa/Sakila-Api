package gov.iti.sakila.business.services.dtos;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * A DTO for the {@link gov.iti.sakila.presistence.entities.Staff} entity
 */
@Data
@NoArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
@ToString
@XmlRootElement
public class StaffDto implements Serializable {
    private String firstName;
    private String lastName;
//    private byte[] picture;
    private String email;
    private boolean active;
}