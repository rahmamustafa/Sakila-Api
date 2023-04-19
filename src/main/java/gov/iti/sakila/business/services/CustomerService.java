package gov.iti.sakila.business.services;

import gov.iti.sakila.business.services.dtos.customer.CustomerDto;
import gov.iti.sakila.business.services.dtos.customer.CustomerDtoCreate;
import gov.iti.sakila.business.services.dtos.film.FilmDto;
import lombok.NonNull;

import java.util.Date;
import java.util.List;

public interface CustomerService {
    CustomerDto createCustomer(CustomerDtoCreate customerDtoCreate);

    CustomerDto findCustomerById(@NonNull Short customerId);

    int deleteCustomerById(Short customerId);

    //    public Customer update(Customer customer){
    //        if(findById(customer.getCustomerId()) == null)
    //            return null;
    //        return customerRepository.update(customer);
    //    }
    List<CustomerDto> findAllCustomers();

    List<CustomerDto> findCustomerByFirstName(@NonNull String firstName);

    List<CustomerDto> findCustomerByEmail(@NonNull String email);

    List<CustomerDto> findActiveCustomers(@NonNull Boolean active);

    List<CustomerDto> findCustomerByCreateDate(@NonNull Date createDate);

    List<FilmDto> findCustomerRentedFilms(@NonNull Short customerId);

    List<FilmDto> findCustomerUnReturnedFilms(@NonNull Short customerId);
}
