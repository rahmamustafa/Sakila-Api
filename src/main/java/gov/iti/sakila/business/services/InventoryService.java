package gov.iti.sakila.business.services;

import gov.iti.sakila.business.services.dtos.InventoryDto;
import gov.iti.sakila.business.services.dtos.film.StoreFilmInventoryDto;
import gov.iti.sakila.business.services.dtos.store.StoreInventoryDto;
import lombok.NonNull;

import java.util.List;

public interface InventoryService {
    InventoryDto createInventory(@NonNull Short filmId, @NonNull Short storeId);

    InventoryDto findInventoryById(@NonNull Short inventoryId);

    int deleteInventoryById(@NonNull Short inventoryId);

    List<InventoryDto> findAllInventories();

    List<StoreFilmInventoryDto> findAllAvailableFilmInventory();

    List<StoreInventoryDto> findFilmRentedInventory(Short filmId);

    List<InventoryDto> findAllRentedInventory();
}
