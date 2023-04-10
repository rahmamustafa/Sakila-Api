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
public class StoreDto implements Serializable {
    private Short storeId;
    private Date lastUpdate;
    @XmlElement(required=true,name = "address")
    private AddressDto addressDto;
    @XmlElement(required=true,name ="managerStaff" )
    private StaffDto managerStaffDto;
    private int numberOfFilms;


}