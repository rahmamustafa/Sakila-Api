package gov.iti.sakila.business.mappers;

import gov.iti.sakila.presistence.dtos.AddressDto;
import gov.iti.sakila.presistence.entities.Address;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AddressMapper {
    AddressMapper INSTANCE = Mappers.getMapper( AddressMapper.class );

    Address toEntity(AddressDto addressDto);

    AddressDto toDto(Address address);


}