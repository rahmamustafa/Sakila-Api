package gov.iti.sakila.presistence.repositories;

import java.util.List;

public interface GenericRepository<T, Id> {

    List<T> findAll();

    T create(T obj);

    T update(T obj);

    int deleteById(Id id);

    T findById(Id id);

    boolean checkExist(Id id);

    <TP> List<T> findListObjByNamedQuery(String namedQuery, String parameterName, TP paramter);

}