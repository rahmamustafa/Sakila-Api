package gov.iti.sakila.presistence.repositories.repositoriesimpl;

import gov.iti.sakila.presistence.dbconnection.JpaManagerSingleton;
import gov.iti.sakila.presistence.repositories.GenericRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.ws.rs.NotFoundException;

import java.util.List;

public abstract class GenericRepositoryImpl<T, Id> implements GenericRepository<T,Id> {
    protected Class<T> entityClass;
    protected EntityManager entityManager;

    protected GenericRepositoryImpl(Class<T> entityClass) {
        this.entityManager = JpaManagerSingleton
                .INSTANCE.getEntityManager();
        this.entityClass = entityClass;
    }
    @Override
    public List<T> findAll(){
        String jpql = "SELECT o FROM " + entityClass.getSimpleName() +" o";
        Query query = entityManager.createQuery(jpql, List.class);
        return (List<T>) query.getResultList();
    }
    @Override
    public T create(T obj){
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(obj);
        transaction.commit();
        entityManager.refresh(obj);
        return obj;
    }
    @Override
    public T update(T obj){
        // need to check if Category exists ?
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.merge(obj);
        transaction.commit();
        entityManager.refresh(obj);
        return obj;
    }
    @Override
    public int deleteById( Id id){

        EntityTransaction transaction = entityManager.getTransaction();;
        Object obj = entityManager.find(entityClass, id);
        if(obj ==null)
            throw new NotFoundException(entityClass.getSimpleName()+" Not Found");
        transaction.begin();
        entityManager.remove(obj);
        transaction.commit();
        return 1;
    }
    @Override
    public T findById( Id id){
        T obj =  (T) entityManager.find(entityClass, id);
        if(obj==null)
            throw new NotFoundException(entityClass.getSimpleName() +" Not Found");
        return obj;
    }
    @Override
    public boolean checkExist(Id id){
        //System.out.println(entityClass.getSimpleName());
        return (T) entityManager.find(entityClass, id) != null;
    }
    @Override

    public  <TP> List<T> findListObjByNamedQuery (String namedQuery , String parameterName, TP paramter){
        TypedQuery<T> query = entityManager.createNamedQuery(namedQuery, entityClass);
        query.setParameter(parameterName, paramter);
        List<T> results = query.getResultList();
        if(results.size()==0)
            throw new NotFoundException("Not Found");
        return results;
    }

}