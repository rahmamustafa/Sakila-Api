package gov.iti.sakila.business.endpoints.soap;

import gov.iti.sakila.business.services.CustomerService;
import gov.iti.sakila.business.services.servicesimpl.CustomerServiceImpl;
import gov.iti.sakila.business.services.dtos.customer.CustomerDto;
import gov.iti.sakila.business.services.dtos.customer.CustomerDtoCreate;
import gov.iti.sakila.business.services.dtos.film.FilmDto;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;

import java.util.Date;
import java.util.List;
@WebService
public class CustomerEndPoint {
    private final CustomerService customerServiceImpl = new CustomerServiceImpl();
    public CustomerDto createCustomer(@WebParam(name = "customer") CustomerDtoCreate customerDtoCreate){
        return customerServiceImpl.createCustomer(customerDtoCreate);

    }
    public CustomerDto findCustomerById(@WebParam(name = "id")Short customerId){
        return customerServiceImpl.findCustomerById(customerId);
    }
    public int deleteCustomerById(@WebParam(name = "id")Short customerId){
        return customerServiceImpl.deleteCustomerById(customerId);
    }
    //    public Customer update(Customer customer){
//        if(findById(customer.getCustomerId()) == null)
//            return null;
//        return customerRepository.update(customer);
//    }
    public List<CustomerDto> findAllCustomers(){
        return customerServiceImpl.findAllCustomers();
    }
    public List<CustomerDto> findCustomerByFirstName(@WebParam(name = "firstName")String firstName){
        return customerServiceImpl.findCustomerByFirstName(firstName);
    }
    public List<CustomerDto> findCustomerByEmail(@WebParam(name = "email")String email ){
        return customerServiceImpl.findCustomerByEmail(email);

    }
    public List<CustomerDto> findActiveCustomers(@WebParam(name = "active")Boolean active ){
        return customerServiceImpl.findActiveCustomers(active);
    }
    public List<CustomerDto> findCustomerByCreateDate(@WebParam(name = "createDate")Date createDate ){
        return customerServiceImpl.findCustomerByCreateDate(createDate);
    }
    public List<FilmDto> findCustomerRentedFilms(@WebParam(name = "id")Short customerId){
        return customerServiceImpl.findCustomerRentedFilms(customerId);
    }
    public List<FilmDto> findCustomerUnReturnedFilms(@WebParam(name = "id")Short customerId){
        return customerServiceImpl.findCustomerUnReturnedFilms(customerId);
    }

}
