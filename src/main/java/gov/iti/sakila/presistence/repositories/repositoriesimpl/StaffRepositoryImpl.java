package gov.iti.sakila.presistence.repositories.repositoriesimpl;

import gov.iti.sakila.presistence.entities.Staff;
import gov.iti.sakila.presistence.repositories.StaffRepository;

public class StaffRepositoryImpl extends GenericRepositoryImpl<Staff,Short > implements StaffRepository {

    public StaffRepositoryImpl(){
        super(Staff.class);
    }

    
}
