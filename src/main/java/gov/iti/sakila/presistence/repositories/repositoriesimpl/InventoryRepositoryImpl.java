package gov.iti.sakila.presistence.repositories.repositoriesimpl;

import gov.iti.sakila.business.services.dtos.film.StoreFilmInventoryDto;
import gov.iti.sakila.business.services.dtos.store.StoreInventoryDto;
import gov.iti.sakila.presistence.entities.Inventory;
import gov.iti.sakila.presistence.repositories.InventoryRepository;
import jakarta.persistence.Query;

import java.util.List;

public class InventoryRepositoryImpl extends GenericRepositoryImpl<Inventory, Short> implements InventoryRepository {

    public InventoryRepositoryImpl() {
        super(Inventory.class);
    }

    @Override
    public List<StoreFilmInventoryDto> findAllAvailableFilmInventory(){
        String jpql ="select new gov.iti.sakila.presistence.dtos.film.StoreFilmInventoryDto(i.filmId.id, i.storeId.id, i.inventoryId) " +
                "from Inventory i " +
                "where i.inventoryId not in " +
                "(select r.inventoryId.id " +
                " from Rental r" +
                " where r.returnDate is null) ";
        Query query = entityManager.createQuery(jpql,List.class);
        return (List<StoreFilmInventoryDto>) query.getResultList();
    }
    @Override
    public List<StoreInventoryDto> findFilmRentedInventory(Short filmId){
        String jpql ="select new gov.iti.sakila.presistence.dtos.store." +
                "StoreInventoryDto(i.storeId.id , i.inventoryId,i.storeId.addressId) " +
                "from Rental r " +
                "join Inventory i " +
                "on i.inventoryId = r.inventoryId.id " +
                "where r.returnDate is null " +
                "and i.filmId.id=:filmId";
        Query query = entityManager.createQuery(jpql,List.class);
        query.setParameter("filmId",filmId);
        return (List<StoreInventoryDto>) query.getResultList();
    }
    @Override
    public List<Inventory> findAllRentedInventory(){
        String jpql ="select  i " +
                "from Rental r " +
                "join Inventory i " +
                "on i.inventoryId = r.inventoryId.id " +
                "where r.returnDate is null ";
        Query query = entityManager.createQuery(jpql,List.class);
        return (List<Inventory>) query.getResultList();
    }

}
