package gov.iti.sakila.business.mappers;

import gov.iti.sakila.business.services.dtos.StaffDto;
import gov.iti.sakila.presistence.entities.Staff;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper
public interface StaffMapper {

    StaffMapper INSTANCE = Mappers.getMapper( StaffMapper.class );

    Staff toEntity(StaffDto staffDto);

    StaffDto toDto(Staff staff);

}