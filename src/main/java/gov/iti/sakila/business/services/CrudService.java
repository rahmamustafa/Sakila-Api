package gov.iti.sakila.business.services;

import java.util.List;

public interface CrudService<T, Id> {
         List<T> findAll() ;
         int deleteById(Id o);
         T findById(Id o);
         
    
}
