package gov.iti.sakila.presistence.dtos.store;

import gov.iti.sakila.presistence.dtos.AddressDto;
import gov.iti.sakila.presistence.dtos.StaffDto;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * A DTO for the {@link gov.iti.sakila.presistence.entities.Store} entity
 */
@Data
@NoArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
@ToString
public class StoreDtoCreate implements Serializable {
    @XmlElement(required=true)
    private Short addressId;
    @XmlElement(required=true)
    private Short managerStaffId;
}