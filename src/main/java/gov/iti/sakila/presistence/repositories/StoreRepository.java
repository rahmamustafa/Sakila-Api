package gov.iti.sakila.presistence.repositories;

import gov.iti.sakila.presistence.entities.*;
import jakarta.persistence.TypedQuery;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StoreRepository extends GenericRepository<Store,Short > {

    public StoreRepository(){
        super(Store.class);
    }
    public Staff findStoreManagerStaff(Short storeId){
        return findById(storeId).getManagerStaffId();
    }
    public Address findStoreAddress(Short storeId){
        return findById(storeId).getAddressId();
    }
    public List<Inventory> findStoreInventories(Short storeId){
        return findById(storeId).getInventoryList();
    }
    public int findNumberStoreFilms(Short storeId){
        String q = "SELECT COUNT(i.filmId.id) FROM Inventory i WHERE i.storeId.id = :storeId GROUP BY i.storeId.id";
        TypedQuery<Long> query = entityManager.createQuery(q, Long.class);
        query.setParameter("storeId", storeId);
        long result = query.getSingleResult();
        return (int) result;
    }
    public Map<Short, Integer> findNumberOfEachFilm(Short storeId) {
        String q = "SELECT i.filmId.id, COUNT(i.filmId.id) FROM Inventory i WHERE i.storeId.id = :storeId GROUP BY i.filmId.id";
        TypedQuery<Object[]> query = entityManager.createQuery(q, Object[].class);
        query.setParameter("storeId", storeId);
        List<Object[]> resultList = query.getResultList();
        Map<Short, Integer> resultMap = new HashMap<>();
        for (Object[] result : resultList) {
            Short filmId = (Short) result[0];
            Long count = (Long) result[1];
            resultMap.put(filmId, count.intValue());
        }
        return resultMap;
    }
}
