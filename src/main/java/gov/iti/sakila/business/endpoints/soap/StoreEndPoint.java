package gov.iti.sakila.business.endpoints.soap;

import gov.iti.sakila.business.services.StoreService;
import gov.iti.sakila.business.services.servicesimpl.StoreServiceImpl;
import gov.iti.sakila.business.services.dtos.AddressDto;
import gov.iti.sakila.business.services.dtos.RentalDto;
import gov.iti.sakila.business.services.dtos.StaffDto;
import gov.iti.sakila.business.services.dtos.film.FilmDto;
import gov.iti.sakila.business.services.dtos.store.StoreDto;
import gov.iti.sakila.business.services.dtos.store.StoreDtoCreate;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;

import java.util.List;

@WebService
public class StoreEndPoint {

    private final StoreService storeService = new StoreServiceImpl();
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
