package gov.iti.sakila.business.endpoints.soap;

import gov.iti.sakila.business.mappers.CustomerMapper;
import gov.iti.sakila.business.mappers.FilmMapper;
import gov.iti.sakila.business.services.CustomerService;
import gov.iti.sakila.business.util.ValidatorHandler;
import gov.iti.sakila.presistence.dtos.customer.CustomerDto;
import gov.iti.sakila.presistence.dtos.customer.CustomerDtoCreate;
import gov.iti.sakila.presistence.dtos.film.FilmDto;
import gov.iti.sakila.presistence.entities.Customer;
import gov.iti.sakila.presistence.entities.Film;
import gov.iti.sakila.presistence.repositories.AddressRepository;
import gov.iti.sakila.presistence.repositories.CustomerRepository;
import gov.iti.sakila.presistence.repositories.StoreRepository;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;

import java.time.Instant;
import java.util.Date;
import java.util.List;
@WebService
public class CustomerEndPoint {
    private final CustomerService customerService = new CustomerService();
    public CustomerDto createCustomer(@WebParam(name = "customer") CustomerDtoCreate customerDtoCreate){
        return customerService.createCustomer(customerDtoCreate);

    }
    public CustomerDto findCustomerById(@WebParam(name = "id")Short customerId){
        return customerService.findCustomerById(customerId);
    }
    public int deleteCustomerById(@WebParam(name = "id")Short customerId){
        return customerService.deleteCustomerById(customerId);
    }
    //    public Customer update(Customer customer){
//        if(findById(customer.getCustomerId()) == null)
//            return null;
//        return customerRepository.update(customer);
//    }
    public List<CustomerDto> findAllCustomers(){
        return customerService.findAllCustomers();
    }
    public List<CustomerDto> findCustomerByFirstName(@WebParam(name = "firstName")String firstName){
        return customerService.findCustomerByFirstName(firstName);
    }
    public List<CustomerDto> findCustomerByEmail(@WebParam(name = "email")String email ){
        return customerService.findCustomerByEmail(email);

    }
    public List<CustomerDto> findActiveCustomers(@WebParam(name = "active")Boolean active ){
        return customerService.findActiveCustomers(active);
    }
    public List<CustomerDto> findCustomerByCreateDate(@WebParam(name = "createDate")Date createDate ){
        return customerService.findCustomerByCreateDate(createDate);
    }
    public List<FilmDto> findCustomerRentedFilms(@WebParam(name = "id")Short customerId){
        return customerService.findCustomerRentedFilms(customerId);
    }

}
