package gov.iti.sakila.business.services.dtos.store;

import jakarta.validation.constraints.Min;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * A DTO for the {@link gov.iti.sakila.presistence.entities.Store} entity
 */
@Data
@NoArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
@ToString
@XmlRootElement
public class StoreDtoCreate implements Serializable {
    @Min(value = 1,message = "please enter address id")
    @XmlElement(required=true)
    private Short addressId;
    @Min(value = 1,message = "please enter manager id")
    @XmlElement(required=true)
    private Short managerStaffId;
}