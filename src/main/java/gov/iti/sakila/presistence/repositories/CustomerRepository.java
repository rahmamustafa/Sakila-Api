package gov.iti.sakila.presistence.repositories;

import java.util.Date;
import java.util.List;

import gov.iti.sakila.business.mappers.FilmMapper;
import gov.iti.sakila.presistence.dtos.film.FilmDto;
import gov.iti.sakila.presistence.entities.Customer;
import gov.iti.sakila.presistence.entities.Film;
import gov.iti.sakila.presistence.entities.Inventory;
import gov.iti.sakila.presistence.entities.Rental;
import jakarta.ws.rs.NotFoundException;

public class CustomerRepository extends GenericRepository<Customer , Short> {

    public CustomerRepository(){
        super(Customer.class);
    }
   
    public List<Customer> findByFirstName(String firstName ){
        return findListObjByNamedQuery("Customer.findByFirstName", "firstName", firstName);

    }
    public List<Customer> findByEmail(String email ){
        return findListObjByNamedQuery("Customer.findByEmail", "email", email);

    }
    public List<Customer> findByActive(Boolean active ){
        return findListObjByNamedQuery("Customer.findByActive", "active", active);

    }
    public List<Customer> findByCreateDate(Date createDate ){
        return findListObjByNamedQuery("Customer.findByCreateDate", "createDate", createDate);

    }
    public List<Film> findCustomerRentedFilms(Short CustomerId){
        Customer customer = findById(CustomerId);
        if(customer==null)
                throw new NotFoundException("Customer Not Found");
        List<Film> films = customer.getRentalList().stream().map(Rental::getInventoryId).map(Inventory::getFilmId).toList();
        return films;
    }
    

}
