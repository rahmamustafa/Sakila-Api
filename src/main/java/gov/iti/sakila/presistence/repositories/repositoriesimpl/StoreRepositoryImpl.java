package gov.iti.sakila.presistence.repositories.repositoriesimpl;

import gov.iti.sakila.presistence.entities.Address;
import gov.iti.sakila.presistence.entities.Staff;
import gov.iti.sakila.presistence.entities.Store;
import gov.iti.sakila.presistence.repositories.StoreRepository;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.ws.rs.NotFoundException;

public class StoreRepositoryImpl extends GenericRepositoryImpl<Store,Short > implements StoreRepository {

    public StoreRepositoryImpl(){
        super(Store.class);
    }
    @Override
    public Staff findStoreManagerStaff(Short storeId){
        Store store = findById(storeId);
        return store.getManagerStaffId();
    }
    @Override
    public Address findStoreAddress(Short storeId){
        Store store = findById(storeId);
        return store.getAddressId();
    }
    @Override
    public int findNumberStoreFilms(Short storeId){
        String q = "SELECT COUNT(i.filmId.id) FROM Inventory i WHERE i.storeId.id = :storeId";
        TypedQuery<Long> query = entityManager.createQuery(q, Long.class);
        query.setParameter("storeId", storeId);
        long result = query.getSingleResult();
        return (int) result;
    }
    @Override
    public long findRentalCountAllStores(){
        String jpql = "select count(r.id)" +
                "from Rental r ";
        Query query = entityManager.createQuery(jpql);
        return ((Long) query.getSingleResult());
    }
    @Override
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
    @Override
    public long findFilmCountInStores(Short filmId){
        String jpql = "select count(i.filmId) from Inventory i where i.filmId.id=:filmId";
        Query query = entityManager.createQuery(jpql);
        query.setParameter("filmId",filmId);
        return ((Long) query.getSingleResult());
    }
    @Override
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
