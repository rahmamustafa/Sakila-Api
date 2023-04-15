package gov.iti.sakila.presistence.repositories;

import gov.iti.sakila.presistence.entities.*;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.ws.rs.NotFoundException;

public class StoreRepository extends GenericRepository<Store,Short > {

    public StoreRepository(){
        super(Store.class);
    }
    public Staff findStoreManagerStaff(Short storeId){
        Store store = findById(storeId);
        if(store==null)
            throw new NotFoundException("Store Not Found");
        return store.getManagerStaffId();
    }
    public Address findStoreAddress(Short storeId){
        Store store = findById(storeId);
        if(store==null)
            throw new NotFoundException("Store Not Found");
        return store.getAddressId();
    }
    public int findNumberStoreFilms(Short storeId){
        String q = "SELECT COUNT(i.filmId.id) FROM Inventory i WHERE i.storeId.id = :storeId";
        TypedQuery<Long> query = entityManager.createQuery(q, Long.class);
        query.setParameter("storeId", storeId);
        long result = query.getSingleResult();
        return (int) result;
    }
    public long findRentalCountAllStores(){
        String jpql = "select count(r.id)" +
                "from Rental r ";
        Query query = entityManager.createQuery(jpql);
        return ((Long) query.getSingleResult());
    }
    public long findRentalCountInStore(Short storeId){
        String jpql = "select count(inv.inventoryId)" +
                "from Inventory inv " +
                "join Rental r " +
                "ON r.inventoryId.inventoryId = inv.inventoryId " +
                "where inv.storeId.id=:storeId";
        Query query = entityManager.createQuery(jpql);
        query.setParameter("storeId",storeId);

        return ((Long) query.getSingleResult());
    }
    public long findFilmCountInStores(Short filmId){
        String jpql = "select count(i.filmId) from Inventory i where i.filmId.id=:filmId";
        Query query = entityManager.createQuery(jpql);
        query.setParameter("filmId",filmId);
        return ((Long) query.getSingleResult());
    }
    public long findFilmCountInStore(Short filmId, Short storeId){
        String jpql = "select count(i.filmId) from Inventory i " +
                "where i.filmId.id=:filmId " +
                "and i.storeId.id=:storeId";
        Query query = entityManager.createQuery(jpql);
        query.setParameter("filmId",filmId);
        query.setParameter("storeId",storeId);
        return ((Long) query.getSingleResult());
    }


}
