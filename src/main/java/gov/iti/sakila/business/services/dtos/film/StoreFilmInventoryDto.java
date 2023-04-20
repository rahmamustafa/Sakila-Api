package gov.iti.sakila.business.services.dtos.film;


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
public class StoreFilmInventoryDto {
    Short filmId;
    Short storeId;
    Integer inventoryId;

    public StoreFilmInventoryDto(Short filmId, Short storeId, Integer inventoryId) {
        this.filmId = filmId;
        this.storeId = storeId;
        this.inventoryId = inventoryId;
    }
}
