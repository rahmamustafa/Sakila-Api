package gov.iti.sakila.presistence.dtos;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
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
public class StoreDto implements Serializable {
    private Short storeId;
    private Date lastUpdate;
    private AddressDto addressDto;
    private StaffDto managerStaffDto;
}