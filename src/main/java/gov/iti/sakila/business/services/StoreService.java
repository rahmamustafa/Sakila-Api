package gov.iti.sakila.business.services;

import gov.iti.sakila.business.mappers.*;
import gov.iti.sakila.presistence.dtos.AddressDto;
import gov.iti.sakila.presistence.dtos.RentalDto;
import gov.iti.sakila.presistence.dtos.StaffDto;
import gov.iti.sakila.presistence.dtos.customer.CustomerDto;
import gov.iti.sakila.presistence.dtos.film.FilmDto;
import gov.iti.sakila.presistence.dtos.store.StoreDto;
import gov.iti.sakila.presistence.dtos.store.StoreDtoCreate;
import gov.iti.sakila.presistence.dtos.store.StoreFilmsDto;
import gov.iti.sakila.presistence.entities.*;
import gov.iti.sakila.presistence.repositories.AddressRepository;
import gov.iti.sakila.presistence.repositories.StaffRepository;
import gov.iti.sakila.presistence.repositories.StoreRepository;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Map;

@WebService
public class StoreService {
    private final StoreRepository storeRepository = new StoreRepository();
    private final AddressRepository addressRepository = new AddressRepository();
    private final StaffRepository staffRepository = new StaffRepository();


    public StoreDto createStore(@WebParam(name = "store") StoreDtoCreate storeDtoCreate){
        Store store = new Store();
        store.setLastUpdate(Date.from(Instant.now()));
        store.setAddressId(addressRepository.findById(storeDtoCreate.getAddressId()));
        store.setManagerStaffId(staffRepository.findById(storeDtoCreate.getManagerStaffId()));
        store = storeRepository.create(store);
        return StoreMapper.INSTANCE.storetoStoreDto(store);
    }
    public StoreDto findStoreById(@WebParam(name = "id")Short storeId){
        Store store  = storeRepository.findById(storeId);
        return StoreMapper.INSTANCE.storetoStoreDto(store);
    }
    public boolean deleteStoreById(@WebParam(name = "id")Short storeId){
        return storeRepository.deleteById(storeId);
    }
    public List<StoreDto> findAllStores(){
        List<Store> stores  = storeRepository.findAll();
        return stores.stream().map(StoreMapper.INSTANCE::storetoStoreDto).toList();
    }
    public StaffDto findStoreManagerStaff(@WebParam(name = "id")Short storeId){
        return StaffMapper.INSTANCE.toDto(storeRepository.findStoreManagerStaff(storeId));

    }
    public AddressDto findStoreAddress(@WebParam(name = "id")Short storeId){
        return AddressMapper.INSTANCE.toDto(storeRepository.findStoreAddress(storeId));
    }
    public List<FilmDto> findStoreFilms(@WebParam(name = "id")Short storeId){
        Store store = storeRepository.findById(storeId);
        List<Film> films = store.getInventoryList().stream().map(Inventory::getFilmId).toList();
        return films.stream().map(FilmMapper.INSTANCE::filmToFilmDto).toList();
    }
    public List<RentalDto> findStoreRentals(@WebParam(name = "id")Short storeId){
        Store store = storeRepository.findById(storeId);
        if(store==null)
            return null;
        List<Rental> rentals = store.getInventoryList().stream().flatMap(i -> i.getRentalList().stream()).toList();
        return rentals.stream().map(RentalMapper.INSTANCE::rentalToRentalDto).toList();
    }
    public int findNumberStoreFilms(@WebParam(name = "id")Short storeId){
        return storeRepository.findNumberStoreFilms(storeId);
    }
//    public StoreFilmsDto findNumberOfEachFilm(@WebParam(name = "id")Short storeId){
//        return storeRepository.findNumberOfEachFilm(storeId);
//    }



}
