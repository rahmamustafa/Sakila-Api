package gov.iti.sakila.business.services.servicesimpl;

import gov.iti.sakila.business.mappers.*;
import gov.iti.sakila.business.util.ValidatorHandler;
import gov.iti.sakila.business.services.dtos.AddressDto;
import gov.iti.sakila.business.services.dtos.RentalDto;
import gov.iti.sakila.business.services.dtos.StaffDto;
import gov.iti.sakila.business.services.dtos.film.FilmDto;
import gov.iti.sakila.business.services.dtos.store.StoreDto;
import gov.iti.sakila.business.services.dtos.store.StoreDtoCreate;
import gov.iti.sakila.presistence.entities.*;
import gov.iti.sakila.presistence.repositories.AddressRepository;
import gov.iti.sakila.presistence.repositories.StaffRepository;
import gov.iti.sakila.presistence.repositories.StoreRepository;
import gov.iti.sakila.presistence.repositories.repositoriesimpl.AddressRepositoryImpl;
import gov.iti.sakila.presistence.repositories.repositoriesimpl.StaffRepositoryImpl;
import gov.iti.sakila.presistence.repositories.repositoriesimpl.StoreRepositoryImpl;
import jakarta.ws.rs.NotFoundException;
import lombok.NonNull;

import java.time.Instant;
import java.util.Date;
import java.util.List;

public class StoreServiceImpl implements gov.iti.sakila.business.services.StoreService {
    private final StoreRepository storeRepository = new StoreRepositoryImpl();
    private final AddressRepository addressRepository = new AddressRepositoryImpl();
    private final StaffRepository staffRepository = new StaffRepositoryImpl();
    private final ValidatorHandler validatorHandler = ValidatorHandler.getInstance();
    private final String ERROR_MESSAGE = "Please Enter Valid Data";


    @Override
    public StoreDto createStore(StoreDtoCreate storeDtoCreate){
        validatorHandler.validate(storeDtoCreate);
        Store store = new Store();
        store.setLastUpdate(Date.from(Instant.now()));
        store.setAddressId(addressRepository.findById(storeDtoCreate.getAddressId()));
        store.setManagerStaffId(staffRepository.findById(storeDtoCreate.getManagerStaffId()));
        try {
            store = storeRepository.create(store);
        }catch (RuntimeException e){
            throw new IllegalArgumentException("this manager already exist");
        }
        return StoreMapper.INSTANCE.storetoStoreDto(store);
    }
    @Override
    public StoreDto findStoreById(@NonNull Short storeId){
        if(storeId<=0)
            throw new IllegalArgumentException(ERROR_MESSAGE);
        Store store  = storeRepository.findById(storeId);
        return StoreMapper.INSTANCE.storetoStoreDto(store);
    }
    @Override
    public int deleteStoreById(@NonNull Short storeId){
        if(storeId<=0)
            throw new IllegalArgumentException(ERROR_MESSAGE);
        return storeRepository.deleteById(storeId);
    }
    @Override
    public List<StoreDto> findAllStores(){
        List<Store> stores  = storeRepository.findAll();
        if(stores.size()==0)
            throw new NotFoundException("Not Found");
        return stores.stream().map(StoreMapper.INSTANCE::storetoStoreDto).toList();
    }
    @Override
    public StaffDto findStoreManagerStaff(@NonNull Short storeId){
        if(storeId<=0)
            throw new IllegalArgumentException(ERROR_MESSAGE);
        Staff staff = storeRepository.findStoreManagerStaff(storeId);
        return StaffMapper.INSTANCE.toDto(staff);

    }
    @Override
    public AddressDto findStoreAddress(@NonNull Short storeId){
        if(storeId<=0)
            throw new IllegalArgumentException(ERROR_MESSAGE);
        return AddressMapper.INSTANCE.toDto(storeRepository.findStoreAddress(storeId));
    }
    @Override
    public List<FilmDto> findStoreFilms(@NonNull Short storeId){
        if(storeId<=0)
            throw new IllegalArgumentException(ERROR_MESSAGE);
        Store store = storeRepository.findById(storeId);
        List<Film> films = store.getInventoryList().stream().map(Inventory::getFilmId).toList();
        if(films.size()==0)
            throw new NotFoundException("Not Found");
        return films.stream().map(FilmMapper.INSTANCE::filmToFilmDto).toList();
    }
    @Override
    public List<RentalDto> findStoreRentals(@NonNull Short storeId){
        if(storeId<=0)
            throw new IllegalArgumentException(ERROR_MESSAGE);
        Store store = storeRepository.findById(storeId);
        List<Rental> rentals = store.getInventoryList().stream().flatMap(i -> i.getRentalList().stream()).toList();
        if(rentals.size()==0)
            throw new NotFoundException("Not Found");
        return rentals.stream().map(RentalMapper.INSTANCE::rentalToRentalDto).toList();
    }
    @Override
    public int findNumberStoreFilms(@NonNull Short storeId){
        if(storeId<=0)
            throw new IllegalArgumentException(ERROR_MESSAGE);
        return storeRepository.findNumberStoreFilms(storeId);
    }
    @Override
    public long findRentalCountAllStores(){
        return storeRepository.findRentalCountAllStores();
    }
    @Override
    public long findRentalCountInStore(@NonNull Short storeId){
        if(storeId<=0)
            throw new IllegalArgumentException(ERROR_MESSAGE);
        return storeRepository.findRentalCountInStore(storeId);
    }
    @Override
    public long findFilmCountInStores(@NonNull Short filmId){
        if(filmId<=0)
            throw new IllegalArgumentException(ERROR_MESSAGE);
        return storeRepository.findFilmCountInStores(filmId);
    }
    @Override
    public long findFilmCountInStore(@NonNull Short filmId, @NonNull Short storeId){
        if(storeId<=0 || filmId<0)
            throw new IllegalArgumentException(ERROR_MESSAGE);
        return storeRepository.findFilmCountInStore(filmId,storeId);
    }

}
