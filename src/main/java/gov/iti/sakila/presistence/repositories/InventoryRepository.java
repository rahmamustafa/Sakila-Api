package gov.iti.sakila.presistence.repositories;

import gov.iti.sakila.business.services.dtos.film.StoreFilmInventoryDto;
import gov.iti.sakila.business.services.dtos.store.StoreInventoryDto;
import gov.iti.sakila.presistence.entities.Inventory;

import java.util.List;

public interface InventoryRepository extends GenericRepository<Inventory, Short> {


    public List<StoreFilmInventoryDto> findAllAvailableFilmInventory();

    public List<StoreInventoryDto> findFilmRentedInventory(Short filmId);

    public List<Inventory> findAllRentedInventory();

}
