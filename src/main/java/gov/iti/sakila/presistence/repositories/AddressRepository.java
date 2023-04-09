package gov.iti.sakila.presistence.repositories;

import gov.iti.sakila.presistence.entities.Address;

public class AddressRepository extends GenericRepository<Address,Short > {

    public AddressRepository(){
        super(Address.class);
    }

    
}
