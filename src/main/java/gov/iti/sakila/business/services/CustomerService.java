package gov.iti.sakila.business.services;

import java.util.Collections;
import java.util.List;

import gov.iti.sakila.presistence.entities.Customer;
import gov.iti.sakila.presistence.repositories.CustomerRepository;
import jakarta.jws.WebService;
@WebService
public class CustomerService {
    private CustomerRepository customerRepository = new CustomerRepository();

    public Customer create(Customer customer){
        return customerRepository.create(customer);
    }
    public Customer findById(Short customerId){
        return customerRepository.findById(customerId);
    }
    public boolean deleteById(Short customerId){
        return customerRepository.deleteById(customerId);
    }
    public Customer update(Customer customer){
        if(findById(customer.getCustomerId()) == null)
            return null;
        return customerRepository.update(customer);
    }
    public List<Customer> findAll(){
        return Collections.unmodifiableList(customerRepository.findAll());
    }
    public List<Customer> findByFirstName(String firstName){
        return customerRepository.findByFirstName(firstName);

    }
    public List<Customer> findByEmail(String email ){
        return customerRepository.findByEmail(email);

    }
    public List<Customer> findByActive(Boolean active ){
        return customerRepository.findByActive(active);
    }
    // public List<Film> findCustomerRentedFilms(Short CustomerId){
    //     Customer customer = findById(CustomerId);
    //     return customer.
    // }

    


}