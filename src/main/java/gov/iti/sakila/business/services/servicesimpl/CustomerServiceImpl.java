package gov.iti.sakila.business.services.servicesimpl;

import java.time.Instant;
import java.util.Date;
import java.util.List;

import gov.iti.sakila.business.mappers.CustomerMapper;
import gov.iti.sakila.business.mappers.FilmMapper;
import gov.iti.sakila.business.services.CustomerService;
import gov.iti.sakila.business.util.ValidatorHandler;
import gov.iti.sakila.business.services.dtos.customer.CustomerDto;
import gov.iti.sakila.business.services.dtos.customer.CustomerDtoCreate;
import gov.iti.sakila.business.services.dtos.film.FilmDto;
import gov.iti.sakila.presistence.entities.Customer;
import gov.iti.sakila.presistence.entities.Film;
import gov.iti.sakila.presistence.repositories.AddressRepository;
import gov.iti.sakila.presistence.repositories.CustomerRepository;
import gov.iti.sakila.presistence.repositories.StoreRepository;
import gov.iti.sakila.presistence.repositories.repositoriesimpl.AddressRepositoryImpl;
import gov.iti.sakila.presistence.repositories.repositoriesimpl.CustomerRepositoryImpl;
import gov.iti.sakila.presistence.repositories.repositoriesimpl.StoreRepositoryImpl;
import jakarta.ws.rs.NotFoundException;
import lombok.NonNull;

public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository = new CustomerRepositoryImpl();
    private final AddressRepository addressRepository = new AddressRepositoryImpl();
    private final StoreRepository storeRepository = new StoreRepositoryImpl();
    private final ValidatorHandler validatorHandler = ValidatorHandler.getInstance();
    private final String ERROR_MESSAGE = "Please Enter Valid Data";


    @Override
    public CustomerDto createCustomer(CustomerDtoCreate customerDtoCreate){
        validatorHandler.validate(customerDtoCreate); // throw exception if failed
        Customer customer = CustomerMapper.INSTANCE.customerDtoCreateToCustomer(customerDtoCreate);
        customer.setAddressId(addressRepository.findById(customerDtoCreate.getAddress()));
        customer.setStoreId(storeRepository.findById(customerDtoCreate.getStore()));
        customer.setCreateDate(Date.from(Instant.now()));
        customerRepository.create(customer);
        return CustomerMapper.INSTANCE.customertoCustomerDto(customer);
    }
    @Override
    public CustomerDto findCustomerById(@NonNull Short customerId){
        if(customerId<=0)
            throw new IllegalArgumentException(ERROR_MESSAGE);
        Customer customer = customerRepository.findById(customerId);
        return CustomerMapper.INSTANCE.customertoCustomerDto(customer);
    }
    @Override
    public int deleteCustomerById(Short customerId){
        if(customerId<=0)
            throw new IllegalArgumentException(ERROR_MESSAGE);
        return customerRepository.deleteById(customerId);
    }
//    public Customer update(Customer customer){
//        if(findById(customer.getCustomerId()) == null)
//            return null;
//        return customerRepository.update(customer);
//    }
    @Override
    public List<CustomerDto> findAllCustomers(){
        List<Customer> customers = customerRepository.findAll();
        if(customers.size()==0)
            throw new NotFoundException("Not Found");
        return customers.stream().map(CustomerMapper.INSTANCE::customertoCustomerDto).toList();
    }
    @Override
    public List<CustomerDto> findCustomerByFirstName(@NonNull String firstName){
        if(firstName.isBlank())
            throw new IllegalArgumentException(ERROR_MESSAGE);
        List<Customer> customers = customerRepository.findByFirstName(firstName);
        return customers.stream().map(CustomerMapper.INSTANCE::customertoCustomerDto).toList();
    }
    @Override
    public List<CustomerDto> findCustomerByEmail(@NonNull String email){
        if(email.isBlank())
            throw new IllegalArgumentException(ERROR_MESSAGE);
        List<Customer> customers = customerRepository.findByEmail(email);
        return customers.stream().map(CustomerMapper.INSTANCE::customertoCustomerDto).toList();

    }
    @Override
    public List<CustomerDto> findActiveCustomers(@NonNull Boolean active){
        if(active.toString().isBlank())
            throw new IllegalArgumentException(ERROR_MESSAGE);
        List<Customer> customers = customerRepository.findByActive(active);
        return customers.stream().map(CustomerMapper.INSTANCE::customertoCustomerDto).toList();
    }
    @Override
    public List<CustomerDto> findCustomerByCreateDate(@NonNull Date createDate){
        if(createDate.toString().isBlank())
            throw new IllegalArgumentException(ERROR_MESSAGE);
        List<Customer> customers = customerRepository.findByCreateDate(createDate);
        return customers.stream().map(CustomerMapper.INSTANCE::customertoCustomerDto).toList();
    }
    @Override
    public List<FilmDto> findCustomerRentedFilms(@NonNull Short customerId){
        if(customerId<=0)
            throw new IllegalArgumentException(ERROR_MESSAGE);
        List<Film> films = customerRepository.findCustomerRentedFilms(customerId);
        if(films.size()==0)
            throw new NotFoundException("Not Found");
        return films.stream().map(FilmMapper.INSTANCE::filmToFilmDto).toList();
    }
    @Override
    public List<FilmDto> findCustomerUnReturnedFilms(@NonNull Short customerId){
        if(customerId<=0)
            throw new IllegalArgumentException(ERROR_MESSAGE);
        List<Film> films = customerRepository.findCustomerUnReturnedFilms(customerId);
        if(films.size()==0)
            throw new NotFoundException("Not Found");
        return films.stream().map(FilmMapper.INSTANCE::filmToFilmDto).toList();
    }


    


}