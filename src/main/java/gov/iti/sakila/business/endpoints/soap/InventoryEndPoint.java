package gov.iti.sakila.business.endpoints.soap;

import gov.iti.sakila.business.services.InventoryService;
import gov.iti.sakila.business.services.servicesimpl.InventoryServiceImpl;
import gov.iti.sakila.business.services.dtos.InventoryDto;
import gov.iti.sakila.business.services.dtos.film.StoreFilmInventoryDto;
import gov.iti.sakila.business.services.dtos.store.StoreInventoryDto;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;

import java.util.List;

@WebService
public class InventoryEndPoint {
    InventoryService inventoryService = new InventoryServiceImpl();

    public InventoryDto createInventory(@WebParam(name = "filmId") Short filmId,@WebParam(name = "storeId")  Short storeId){
        return inventoryService.createInventory(filmId,storeId);
    }
    public InventoryDto findInventoryById(@WebParam(name = "id")  Short inventoryId){
        return inventoryService.findInventoryById(inventoryId);
    }
    public int deleteInventoryById(@WebParam(name = "id")  Short inventoryId){
        return inventoryService.deleteInventoryById(inventoryId);

    }
    public List<InventoryDto> findAllInventories(){
        return inventoryService.findAllInventories();

    }
    public List<StoreFilmInventoryDto> findAllAvailableFilmInventory(){
        return inventoryService.findAllAvailableFilmInventory();
    }
    public List<StoreInventoryDto> findFilmRentedInventory(@WebParam(name = "filmId") Short filmId){
        return inventoryService.findFilmRentedInventory(filmId);

    }
    public List<InventoryDto> findAllRentedInventory(){
        return inventoryService.findAllRentedInventory();

    }
}
