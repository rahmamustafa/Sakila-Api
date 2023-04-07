package gov.iti.sakila.presistence.repositories;

import java.util.List;

import gov.iti.sakila.presistence.entities.Customer;

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
    

}
