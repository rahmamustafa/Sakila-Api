package gov.iti.sakila.business.mappers;

import gov.iti.sakila.presistence.dtos.AddressDto;
import gov.iti.sakila.presistence.dtos.StaffDto;
import gov.iti.sakila.presistence.dtos.StoreDto;
import gov.iti.sakila.presistence.entities.Address;
import gov.iti.sakila.presistence.entities.Language;
import gov.iti.sakila.presistence.entities.Staff;
import gov.iti.sakila.presistence.entities.Store;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper
public interface StoreMapper {
    StoreMapper INSTANCE = Mappers.getMapper( StoreMapper.class );

    Store toEntity(StoreDto storeDto);
    @Mapping(source = "addressId", target = "addressDto", qualifiedByName = "mapAddress")
    @Mapping(source = "managerStaffId", target = "managerStaffDto", qualifiedByName = "mapManagerStaff")
    StoreDto toDto(Store store);

    @Named("mapAddress")
    default AddressDto mapLanguageName(Address address) {
        return AddressMapper.INSTANCE.toDto(address);
    }
    @Named("mapManagerStaff")
    default StaffDto mapManagerStaff(Staff staff) {
        return StaffMapper.INSTANCE.toDto(staff);
    }
}