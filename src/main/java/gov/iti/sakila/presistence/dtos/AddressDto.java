package gov.iti.sakila.presistence.dtos;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * A DTO for the {@link gov.iti.sakila.presistence.entities.Address} entity
 */
@Data
@NoArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
@ToString
@XmlRootElement
public class AddressDto implements Serializable {
    private Short addressId;
    private String address;
    private String address2;
    private String district;
    private String postalCode;
    private byte[] location;
    private String phone;
    private Date lastUpdate;

}