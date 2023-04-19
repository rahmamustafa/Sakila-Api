package gov.iti.sakila.business.mappers;

import gov.iti.sakila.business.services.dtos.AddressDto;
import gov.iti.sakila.business.services.dtos.StaffDto;
import gov.iti.sakila.business.services.dtos.store.StoreDto;
import gov.iti.sakila.presistence.entities.Address;
import gov.iti.sakila.presistence.entities.Inventory;
import gov.iti.sakila.presistence.entities.Staff;
import gov.iti.sakila.presistence.entities.Store;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface StoreMapper {
    StoreMapper INSTANCE = Mappers.getMapper(StoreMapper.class);

    Store storeDtotoStore(StoreDto storeDto);

    @Mapping(source = "addressId", target = "addressDto", qualifiedByName = "mapAddress")
    @Mapping(source = "managerStaffId", target = "managerStaffDto", qualifiedByName = "mapManagerStaff")
    @Mapping(source = "inventoryList", target = "numberOfFilms", qualifiedByName = "mapNumberOfFilms")
    StoreDto storetoStoreDto(Store store);

    @Named("mapAddress")
    default AddressDto mapLanguageName(Address address) {
        return AddressMapper.INSTANCE.toDto(address);
    }

    @Named("mapManagerStaff")
    default StaffDto mapManagerStaff(Staff staff) {
        return StaffMapper.INSTANCE.toDto(staff);
    }
    @Named("mapNumberOfFilms")
    default int mapNumberOfFilms(List<Inventory> inventoryList) {
        return inventoryList.size();
    }

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Store partialUpdate(StoreDto storeDto, @MappingTarget Store store);
}