package gov.iti.sakila.presistence.repositories.repositoriesimpl;

import gov.iti.sakila.presistence.entities.Address;
import gov.iti.sakila.presistence.repositories.AddressRepository;

public class AddressRepositoryImpl extends GenericRepositoryImpl<Address,Short > implements AddressRepository {

    public AddressRepositoryImpl(){
        super(Address.class);
    }

    
}
