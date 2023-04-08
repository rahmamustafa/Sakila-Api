package gov.iti.sakila.presistence.repositories;

import gov.iti.sakila.presistence.entities.Language;

public class StoreRepository extends GenericRepository<Language,Short > {

    public StoreRepository(){
        super(Language.class);
    }

    
}
