package gov.iti.sakila.business.services.servicesimpl;


import gov.iti.sakila.business.mappers.InventoryMapper;
import gov.iti.sakila.business.services.dtos.InventoryDto;
import gov.iti.sakila.business.services.dtos.film.StoreFilmInventoryDto;
import gov.iti.sakila.business.services.dtos.store.StoreInventoryDto;
import gov.iti.sakila.presistence.entities.Film;
import gov.iti.sakila.presistence.entities.Inventory;
import gov.iti.sakila.presistence.entities.Store;
import gov.iti.sakila.presistence.repositories.FilmRepository;
import gov.iti.sakila.presistence.repositories.InventoryRepository;
import gov.iti.sakila.presistence.repositories.StoreRepository;
import gov.iti.sakila.presistence.repositories.repositoriesimpl.FilmRepositoryImpl;
import gov.iti.sakila.presistence.repositories.repositoriesimpl.InventoryRepositoryImpl;
import gov.iti.sakila.presistence.repositories.repositoriesimpl.StoreRepositoryImpl;
import jakarta.ws.rs.NotFoundException;
import lombok.NonNull;

import java.time.Instant;
import java.util.Date;
import java.util.List;

public class InventoryServiceImpl implements gov.iti.sakila.business.services.InventoryService {
    private final InventoryRepository inventoryRepository = new InventoryRepositoryImpl();
    private final FilmRepository filmRepository = new FilmRepositoryImpl();
    private final StoreRepository storeRepository = new StoreRepositoryImpl();
    private final String ERROR_MESSAGE = "Please Enter Valid Data";

    @Override
    public InventoryDto createInventory(@NonNull Short filmId, @NonNull Short storeId) {
        Store store = storeRepository.findById(storeId);
        Film film = filmRepository.findById(filmId);
        Inventory inventory = new Inventory();
        inventory.setLastUpdate(Date.from(Instant.now()));
        inventory.setFilmId(film);
        inventory.setStoreId(store);
        inventoryRepository.create(inventory);
        return InventoryMapper.INSTANCE.toDto(inventory);
    }

    @Override
    public InventoryDto findInventoryById(@NonNull Short inventoryId) {
        if (inventoryId <= 0)
            throw new IllegalArgumentException(ERROR_MESSAGE);
        Inventory inventory = inventoryRepository.findById(inventoryId);
        return InventoryMapper.INSTANCE.toDto(inventory);
    }

    @Override
    public int deleteInventoryById(@NonNull Short inventoryId) {
        if (inventoryId <= 0)
            throw new IllegalArgumentException(ERROR_MESSAGE);
        return inventoryRepository.deleteById(inventoryId);
    }

    @Override
    public List<InventoryDto> findAllInventories() {
        List<Inventory> inventories = inventoryRepository.findAll();
        if (inventories.size() == 0)
            throw new NotFoundException("Not Found");
        return inventories.stream().map(InventoryMapper.INSTANCE::toDto).toList();
    }

    @Override
    public List<StoreFilmInventoryDto> findAllAvailableFilmInventory() {
        return inventoryRepository.findAllAvailableFilmInventory();
    }

    @Override
    public List<StoreInventoryDto> findFilmRentedInventory(Short filmId) {
        if (filmId <= 0)
            throw new IllegalArgumentException(ERROR_MESSAGE);
        return inventoryRepository.findFilmRentedInventory(filmId);

    }

    @Override
    public List<InventoryDto> findAllRentedInventory() {
        List<Inventory> inventories = inventoryRepository.findAllRentedInventory();
        return inventories.stream().map(InventoryMapper.INSTANCE::toDto).toList();
    }
}
