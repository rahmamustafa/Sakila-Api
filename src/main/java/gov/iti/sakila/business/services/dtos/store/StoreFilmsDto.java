package gov.iti.sakila.business.services.dtos.store;

import gov.iti.sakila.business.services.dtos.film.FilmDtoWithCountForStore;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * A DTO for the {@link gov.iti.sakila.presistence.entities.Store} entity
 */
@Data
@NoArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
@ToString
public class StoreFilmsDto implements Serializable {
    private Short storeId;
    private String storeName;
    //private AddressDto address;

    private List<FilmDtoWithCountForStore> filmList;

    public StoreFilmsDto(Short storeId, String storeName,  List<FilmDtoWithCountForStore> filmList) {
        this.storeId = storeId;
        this.storeName = storeName;
        //this.address = address;
        this.filmList = filmList;
    }
}