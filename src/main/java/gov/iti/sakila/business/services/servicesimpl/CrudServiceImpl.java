package gov.iti.sakila.business.services.servicesimpl;

import gov.iti.sakila.business.mappers.ActorMapper;
import gov.iti.sakila.business.services.CrudService;
import gov.iti.sakila.presistence.entities.Actor;
import gov.iti.sakila.presistence.repositories.GenericRepository;

import java.util.List;

public class CrudServiceImpl<T,Id> implements CrudService<T,Id> {

    private final String ERROR_MESSAGE = "Please Enter Valid Data";
    GenericRepository repository;
    public CrudServiceImpl(GenericRepository genericRepository) {
        this.repository = genericRepository;
    }

    @Override
    public List<T> findAll() {
       return null;
    }

    @Override
    public int deleteById(Id o) {
        return 0;
    }

    @Override
    public T findById(Id o) {
      return null;
    }
}
