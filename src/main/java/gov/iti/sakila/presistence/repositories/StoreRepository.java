package gov.iti.sakila.presistence.repositories;

import gov.iti.sakila.presistence.entities.*;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.ws.rs.NotFoundException;

public interface StoreRepository extends GenericRepository<Store,Short > {

    public Staff findStoreManagerStaff(Short storeId);

    public Address findStoreAddress(Short storeId);

    public int findNumberStoreFilms(Short storeId);

    public long findRentalCountAllStores();

    public long findRentalCountInStore(Short storeId);

    public long findFilmCountInStores(Short filmId);

    public long findFilmCountInStore(Short filmId, Short storeId);


}
