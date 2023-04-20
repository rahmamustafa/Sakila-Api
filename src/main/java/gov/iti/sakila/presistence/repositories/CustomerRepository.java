package gov.iti.sakila.presistence.repositories;

import java.util.Date;
import java.util.List;

import gov.iti.sakila.presistence.entities.Customer;
import gov.iti.sakila.presistence.entities.Film;

public interface CustomerRepository extends GenericRepository<Customer , Short> {


       List<Customer> findByFirstName(String firstName );
       List<Customer> findByEmail(String email );
       List<Customer> findByActive(Boolean active );
       List<Customer> findByCreateDate(Date createDate );
       List<Film> findCustomerRentedFilms(Short CustomerId);
       List<Film> findCustomerUnReturnedFilms(Short customerId);

}
