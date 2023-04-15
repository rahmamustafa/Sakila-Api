package gov.iti.sakila.business.endpoints.soap;

import gov.iti.sakila.business.mappers.InventoryMapper;
import gov.iti.sakila.business.services.InventoryService;
import gov.iti.sakila.presistence.dtos.InventoryDto;
import gov.iti.sakila.presistence.dtos.film.StoreFilmInventoryDto;
import gov.iti.sakila.presistence.dtos.store.StoreInventoryDto;
import gov.iti.sakila.presistence.entities.Inventory;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import jakarta.ws.rs.NotFoundException;
import lombok.NonNull;

import java.util.List;

@WebService
public class InventoryEndPoint {
    InventoryService inventoryService = new InventoryService();

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
