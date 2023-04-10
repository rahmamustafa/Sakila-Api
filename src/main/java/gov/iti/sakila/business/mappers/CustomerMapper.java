package gov.iti.sakila.business.mappers;

import gov.iti.sakila.presistence.dtos.customer.CustomerDto;
import gov.iti.sakila.presistence.dtos.customer.CustomerDtoCreate;
import gov.iti.sakila.presistence.dtos.store.StoreDto;
import gov.iti.sakila.presistence.entities.Customer;
import gov.iti.sakila.presistence.entities.Store;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerMapper {
    CustomerMapper   INSTANCE = Mappers.getMapper( CustomerMapper.class );

    Customer customerDtoCreateToCustomer(CustomerDtoCreate customerDtoCreate);
    CustomerDto customertoCustomerDto(Customer customer);

}