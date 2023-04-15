package gov.iti.sakila.business.endpoints.soap;

import gov.iti.sakila.business.mappers.*;
import gov.iti.sakila.business.services.StoreService;
import gov.iti.sakila.presistence.dtos.AddressDto;
import gov.iti.sakila.presistence.dtos.RentalDto;
import gov.iti.sakila.presistence.dtos.StaffDto;
import gov.iti.sakila.presistence.dtos.film.FilmDto;
import gov.iti.sakila.presistence.dtos.store.StoreDto;
import gov.iti.sakila.presistence.dtos.store.StoreDtoCreate;
import gov.iti.sakila.presistence.entities.Film;
import gov.iti.sakila.presistence.entities.Inventory;
import gov.iti.sakila.presistence.entities.Rental;
import gov.iti.sakila.presistence.entities.Store;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import lombok.NonNull;

import java.time.Instant;
import java.util.Date;
import java.util.List;

@WebService
public class StoreEndPoint {

    private final StoreService storeService = new StoreService();
    public StoreDto createStore(@WebParam(name = "store") StoreDtoCreate storeDtoCreate){
        return storeService.createStore(storeDtoCreate);
    }
    public StoreDto findStoreById(@WebParam(name = "id")Short storeId){
        return storeService.findStoreById(storeId);
    }
    public int deleteStoreById(@WebParam(name = "id")Short storeId){
        return storeService.deleteStoreById(storeId);
    }
    public List<StoreDto> findAllStores(){
        return storeService.findAllStores();
    }
    public StaffDto findStoreManagerStaff(@WebParam(name = "id")Short storeId){
        return storeService.findStoreManagerStaff(storeId);

    }
    public AddressDto findStoreAddress(@WebParam(name = "id")Short storeId){
        return storeService.findStoreAddress(storeId);
    }
    public List<FilmDto> findStoreFilms(@WebParam(name = "id")Short storeId){
        return storeService.findStoreFilms(storeId);
    }
    public List<RentalDto> findStoreRentals(@WebParam(name = "id")Short storeId){
        return storeService.findStoreRentals(storeId);
    }
    public int findNumberStoreFilms(@WebParam(name = "id")Short storeId){
        return storeService.findNumberStoreFilms(storeId);
    }
    public long findRentalCountAllStores(){
        return storeService.findRentalCountAllStores();
    }
    public long findRentalCountInStore(@WebParam(name = "id")Short storeId){
        return storeService.findRentalCountInStore(storeId);
    }
    public long findFilmCountInStores(@WebParam(name = "id")Short filmId){
        return storeService.findFilmCountInStores(filmId);
    }
    public long findFilmCountInStore(@WebParam(name = "filmId")Short filmId, @WebParam(name = "storeId")Short storeId){
        return storeService.findFilmCountInStore(filmId,storeId);
    }

}
