package gov.iti.sakila.presistence.repositories;

import gov.iti.sakila.presistence.entities.Staff;

public class StaffRepository extends GenericRepository<Staff,Short > {

    public StaffRepository(){
        super(Staff.class);
    }

    
}
