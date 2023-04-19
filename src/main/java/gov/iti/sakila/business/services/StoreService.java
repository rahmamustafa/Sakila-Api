package gov.iti.sakila.business.services;

import gov.iti.sakila.business.services.dtos.AddressDto;
import gov.iti.sakila.business.services.dtos.RentalDto;
import gov.iti.sakila.business.services.dtos.StaffDto;
import gov.iti.sakila.business.services.dtos.film.FilmDto;
import gov.iti.sakila.business.services.dtos.store.StoreDto;
import gov.iti.sakila.business.services.dtos.store.StoreDtoCreate;
import lombok.NonNull;

import java.util.List;

public interface StoreService {
    StoreDto createStore(StoreDtoCreate storeDtoCreate);

    StoreDto findStoreById(@NonNull Short storeId);

    int deleteStoreById(@NonNull Short storeId);

    List<StoreDto> findAllStores();

    StaffDto findStoreManagerStaff(@NonNull Short storeId);

    AddressDto findStoreAddress(@NonNull Short storeId);

    List<FilmDto> findStoreFilms(@NonNull Short storeId);

    List<RentalDto> findStoreRentals(@NonNull Short storeId);

    int findNumberStoreFilms(@NonNull Short storeId);

    long findRentalCountAllStores();

    long findRentalCountInStore(@NonNull Short storeId);

    long findFilmCountInStores(@NonNull Short filmId);

    long findFilmCountInStore(@NonNull Short filmId, @NonNull Short storeId);
}
