package gov.iti.sakila.business.services.dtos.store;


import gov.iti.sakila.business.mappers.AddressMapper;
import gov.iti.sakila.business.services.dtos.AddressDto;
import gov.iti.sakila.presistence.entities.Address;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class StoreInventoryDto {
    private Short storeId;
    private Integer inventoryId;
    private AddressDto addressDto;

    public StoreInventoryDto(Short storeId, Integer inventoryId , Address address) {
        this.storeId = storeId;
        this.inventoryId = inventoryId;
        addressDto = AddressMapper.INSTANCE.toDto(address);
    }
}
