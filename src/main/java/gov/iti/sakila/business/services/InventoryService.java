package gov.iti.sakila.business.services;


import gov.iti.sakila.business.mappers.ActorMapper;
import gov.iti.sakila.business.mappers.InventoryMapper;
import gov.iti.sakila.business.mappers.StoreMapper;
import gov.iti.sakila.presistence.dtos.InventoryDto;
import gov.iti.sakila.presistence.dtos.actor.ActorDto;
import gov.iti.sakila.presistence.dtos.film.StoreFilmInventoryDto;
import gov.iti.sakila.presistence.dtos.store.StoreDto;
import gov.iti.sakila.presistence.dtos.store.StoreDtoCreate;
import gov.iti.sakila.presistence.dtos.store.StoreInventoryDto;
import gov.iti.sakila.presistence.entities.Actor;
import gov.iti.sakila.presistence.entities.Film;
import gov.iti.sakila.presistence.entities.Inventory;
import gov.iti.sakila.presistence.entities.Store;
import gov.iti.sakila.presistence.repositories.FilmRepository;
import gov.iti.sakila.presistence.repositories.InventoryRepository;
import gov.iti.sakila.presistence.repositories.StoreRepository;
import jakarta.persistence.Query;
import jakarta.ws.rs.NotFoundException;
import lombok.NonNull;

import java.time.Instant;
import java.util.Date;
import java.util.List;

public class InventoryService {
    private final InventoryRepository inventoryRepository = new InventoryRepository();
    private final FilmRepository filmRepository = new FilmRepository();
    private final StoreRepository storeRepository = new StoreRepository();
    private final String ERROR_MESSAGE = "Please Enter Valid Data";

    public InventoryDto createInventory(@NonNull Short filmId, @NonNull Short storeId) {
        Store store = storeRepository.findById(storeId);
        Film film = filmRepository.findById(filmId);
        if (store == null || film == null)
            throw new NotFoundException("Not Found");
        Inventory inventory = new Inventory();
        inventory.setLastUpdate(Date.from(Instant.now()));
        inventory.setFilmId(film);
        inventory.setStoreId(store);
        inventoryRepository.create(inventory);
        return InventoryMapper.INSTANCE.toDto(inventory);
    }

    public InventoryDto findInventoryById(@NonNull Short inventoryId) {
        if (inventoryId <= 0)
            throw new IllegalArgumentException(ERROR_MESSAGE);
        Inventory inventory = inventoryRepository.findById(inventoryId);
        if (inventory == null)
            throw new NotFoundException("Inventory Not Found");
        return InventoryMapper.INSTANCE.toDto(inventory);
    }

    public int deleteInventoryById(@NonNull Short inventoryId) {
        if (inventoryId <= 0)
            throw new IllegalArgumentException(ERROR_MESSAGE);
        return inventoryRepository.deleteById(inventoryId);
    }

    public List<InventoryDto> findAllInventories() {
        List<Inventory> inventories = inventoryRepository.findAll();
        if (inventories.size() == 0)
            throw new NotFoundException("Not Found");
        return inventories.stream().map(InventoryMapper.INSTANCE::toDto).toList();
    }

    public List<StoreFilmInventoryDto> findAllAvailableFilmInventory() {
        return inventoryRepository.findAllAvailableFilmInventory();
    }

    public List<StoreInventoryDto> findFilmRentedInventory(Short filmId) {
        if (filmId <= 0)
            throw new IllegalArgumentException(ERROR_MESSAGE);
        return inventoryRepository.findFilmRentedInventory(filmId);

    }

    public List<InventoryDto> findAllRentedInventory() {
        List<Inventory> inventories = inventoryRepository.findAllRentedInventory();
        return inventories.stream().map(InventoryMapper.INSTANCE::toDto).toList();
    }
}
