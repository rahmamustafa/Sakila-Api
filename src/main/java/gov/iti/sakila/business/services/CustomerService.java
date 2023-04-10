package gov.iti.sakila.business.services;

import java.time.Instant;
import java.util.Date;
import java.util.List;

import gov.iti.sakila.business.mappers.CustomerMapper;
import gov.iti.sakila.business.mappers.FilmMapper;
import gov.iti.sakila.presistence.dtos.customer.CustomerDto;
import gov.iti.sakila.presistence.dtos.customer.CustomerDtoCreate;
import gov.iti.sakila.presistence.dtos.film.FilmDto;
import gov.iti.sakila.presistence.entities.Address;
import gov.iti.sakila.presistence.entities.Customer;
import gov.iti.sakila.presistence.entities.Film;
import gov.iti.sakila.presistence.repositories.AddressRepository;
import gov.iti.sakila.presistence.repositories.CustomerRepository;
import gov.iti.sakila.presistence.repositories.StoreRepository;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
@WebService
public class CustomerService {
    private CustomerRepository customerRepository = new CustomerRepository();
    private AddressRepository addressRepository = new AddressRepository();
    private StoreRepository storeRepository = new StoreRepository();

    public CustomerDto createCustomer(@WebParam(name = "customer") CustomerDtoCreate customerDtoCreate){
        Customer customer = CustomerMapper.INSTANCE.customerDtoCreateToCustomer(customerDtoCreate);
        customer.setAddressId(addressRepository.findById(customerDtoCreate.getAddress()));
        customer.setStoreId(storeRepository.findById(customerDtoCreate.getStore()));
        customer.setCreateDate(Date.from(Instant.now()));
        customerRepository.create(customer);
        return CustomerMapper.INSTANCE.customertoCustomerDto(customer);
    }
    public CustomerDto findCustomerById(@WebParam(name = "id")Short customerId){
        return CustomerMapper.INSTANCE.customertoCustomerDto(customerRepository.findById(customerId));
    }
    public boolean deleteCustomerById(@WebParam(name = "id")Short customerId){
        return customerRepository.deleteById(customerId);
    }
//    public Customer update(Customer customer){
//        if(findById(customer.getCustomerId()) == null)
//            return null;
//        return customerRepository.update(customer);
//    }
    public List<CustomerDto> findAllCustomers(){
        List<Customer> customers = customerRepository.findAll();
        return customers.stream().map(CustomerMapper.INSTANCE::customertoCustomerDto).toList();
    }
    public List<CustomerDto> findCustomerByFirstName(@WebParam(name = "firstName")String firstName){
        List<Customer> customers = customerRepository.findByFirstName(firstName);
        return customers.stream().map(CustomerMapper.INSTANCE::customertoCustomerDto).toList();
    }
    public List<CustomerDto> findCustomerByEmail(@WebParam(name = "email")String email ){
        List<Customer> customers = customerRepository.findByEmail(email);
        return customers.stream().map(CustomerMapper.INSTANCE::customertoCustomerDto).toList();

    }
    public List<CustomerDto> findActiveCustomers(@WebParam(name = "active")Boolean active ){
        List<Customer> customers = customerRepository.findByActive(active);
        return customers.stream().map(CustomerMapper.INSTANCE::customertoCustomerDto).toList();
    }
    public List<CustomerDto> findCustomerByCreateDate(@WebParam(name = "createDate")Date createDate ){
        List<Customer> customers = customerRepository.findByCreateDate(createDate);
        return customers.stream().map(CustomerMapper.INSTANCE::customertoCustomerDto).toList();
    }
    public List<FilmDto> findCustomerRentedFilms(@WebParam(name = "id")Short customerId){
        List<Film> films = customerRepository.findCustomerRentedFilms(customerId);
        return films.stream().map(FilmMapper.INSTANCE::filmToFilmDto).toList();
    }


    


}