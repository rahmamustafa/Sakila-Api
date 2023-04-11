package gov.iti.sakila.presistence.repositories;

import gov.iti.sakila.presistence.dtos.film.FilmDtoWithCountForStore;
import gov.iti.sakila.presistence.dtos.store.StoreFilmsDto;
import gov.iti.sakila.presistence.entities.*;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StoreRepository extends GenericRepository<Store,Short > {

    public StoreRepository(){
        super(Store.class);
    }
    public Staff findStoreManagerStaff(Short storeId){
        return findById(storeId).getManagerStaffId();
    }
    public Address findStoreAddress(Short storeId){
        return findById(storeId).getAddressId();
    }
    public List<Inventory> findStoreInventories(Short storeId){
        return findById(storeId).getInventoryList();
    }
    public int findNumberStoreFilms(Short storeId){
        String q = "SELECT COUNT(i.filmId.id) FROM Inventory i WHERE i.storeId.id = :storeId GROUP BY i.storeId.id";
        TypedQuery<Long> query = entityManager.createQuery(q, Long.class);
        query.setParameter("storeId", storeId);
        long result = query.getSingleResult();
        return (int) result;
    }
//    public StoreFilmsDto findNumberOfEachFilm(Short storeId) {

        /*String q = "SELECT  NEW gov.iti.sakila.presistence.dtos.store.StoreFilmsDto(s.storeId,  s.addressId.address, " +
                //",NEW gov.iti.sakila.presistence.dtos.AddressDto(s.addressId.addressId, s.addressId.address) " +
                    "(" +
                    "SELECT " +
                    "NEW gov.iti.sakila.presistence.dtos.film.FilmDtoWithCountForStore(f.filmId, f.title," +
                    "f.description, f.languageId.name, count (il.filmId))" +
                    "FROM Store s2 " +
                    "join s2.inventoryList il " +
                    "join il.filmId f " +
                    "WHERE il.storeId = s " +
                    "GROUP BY f.filmId)" +
                ")" +
                "FROM Store s " +
                "WHERE s.id = :storeId ";*/
//        String q = "SELECT NEW gov.iti.sakila.presistence.dtos.store.StoreFilmsDto(s.storeId,  s.addressId.address, films) " +
//                //",NEW gov.iti.sakila.presistence.dtos.AddressDto(s.addressId.addressId, s.addressId.address) " +
//                "FROM Store s " +
//                " JOIN (" +
//                " SELECT f.filmId, f.title, f.description, f.languageId.name, count (il.filmId) As countInStore " +
//                "FROM Store s2 " +
//                "join s2.inventoryList il " +
//                "join il.filmId f " +
//                "WHERE s2.storeId = :storeId " +
//                "GROUP BY f.filmId" +
//                ") films " +
//                "ON films.filmId = s.inventoryList.filmId " +
//                "WHERE s.storeId = :storeId ";
//        TypedQuery<StoreFilmsDto> query = entityManager.createQuery(q , StoreFilmsDto.class);
//        query.setParameter("storeId", storeId);
//        List<StoreFilmsDto> resultList = query.getResultList();
//        StoreFilmsDto resultMap = (StoreFilmsDto) resultList.get(0);
/*        for (StoreFilmsDto[] result : resultList) {
            Short filmId = (Short) result[0];
            Long count = (Long) result[1];
            resultMap.put(filmId, count.intValue());
        }*/
//        return resultMap;
//    }

    /*public StoreFilmsDto findNumberOfEachFilm(Short storeId){
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<StoreFilmsDto> query = cb.createQuery(StoreFilmsDto.class);
        Root<Store> s = query.from(Store.class);

// Subquery for fetching films and their count for a particular store
        Subquery<FilmDtoWithCountForStore> filmSubquery = query.subquery(FilmDtoWithCountForStore.class);
        Root<Store> s2 = filmSubquery.from(Store.class);
        Join<Store, Inventory> il = s2.join(Store_.inventoryList);
        Join<Inventory, Film> f = il.join(Inventory_.filmId);
        filmSubquery.select(cb.construct(FilmDtoWithCountForStore.class, f.get(Film.filmId), f.get(Film_.title), f.get(Film_.description), f.get(Film_.languageId).get(Language_.name), cb.count(il.get(Inventory_.filmId))))
                .where(cb.equal(s2.get(Store_.id), storeId))
                .groupBy(f.get(Film_.filmId));

// Join store with the subquery to fetch the films and their count
        Join<Store, Inventory> il2 = s.join(Store_.inventoryList);
        Join<Inventory, Film> f2 = il2.join(Inventory_.filmId);
        Join<FilmDtoWithCountForStore, Film> films = filmSubquery.join(FilmDtoWithCountForStore_.film);
        query.select(cb.construct(StoreFilmsDto.class, s.get(Store_.storeId), s.get(Store_.addressId).get(Address_.address), cb.count(films)))
                .where(cb.equal(s.get(Store_.id), storeId))
                .groupBy(f2.get(Film_.filmId));

        List<StoreFilmsDto> results = entityManager.createQuery(query).getResultList();
    }*/
}
