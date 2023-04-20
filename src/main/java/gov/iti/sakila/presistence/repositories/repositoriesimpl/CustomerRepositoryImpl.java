package gov.iti.sakila.presistence.repositories.repositoriesimpl;

import gov.iti.sakila.presistence.entities.Customer;
import gov.iti.sakila.presistence.entities.Film;
import gov.iti.sakila.presistence.entities.Inventory;
import gov.iti.sakila.presistence.entities.Rental;
import gov.iti.sakila.presistence.repositories.CustomerRepository;
import jakarta.persistence.Query;

import java.util.Date;
import java.util.List;

public class CustomerRepositoryImpl extends GenericRepositoryImpl<Customer , Short> implements CustomerRepository {

    public CustomerRepositoryImpl(){
        super(Customer.class);
    }

    @Override
    public List<Customer> findByFirstName(String firstName ){
        return findListObjByNamedQuery("Customer.findByFirstName", "firstName", firstName);

    }
    @Override
    public List<Customer> findByEmail(String email ){
        return findListObjByNamedQuery("Customer.findByEmail", "email", email);

    }
    @Override
    public List<Customer> findByActive(Boolean active ){
        return findListObjByNamedQuery("Customer.findByActive", "active", active);

    }
    @Override
    public List<Customer> findByCreateDate(Date createDate ){
        return findListObjByNamedQuery("Customer.findByCreateDate", "createDate", createDate);

    }
    @Override
    public List<Film> findCustomerRentedFilms(Short CustomerId){
        Customer customer = findById(CustomerId);
        List<Film> films = customer.getRentalList().stream().map(Rental::getInventoryId).map(Inventory::getFilmId).toList();
        return films;
    }
    @Override
    public List<Film> findCustomerUnReturnedFilms(Short customerId){
        String jpql ="select i.filmId " +
                "from Rental r " +
                "join Inventory i " +
                "on i.inventoryId = r.inventoryId.id " +
                "where r.returnDate is null " +
                "and r.customerId.id=:customerId";
        Query query = entityManager.createQuery(jpql,List.class);
        query.setParameter("customerId",customerId);
        return (List<Film>) query.getResultList();

    }

}
