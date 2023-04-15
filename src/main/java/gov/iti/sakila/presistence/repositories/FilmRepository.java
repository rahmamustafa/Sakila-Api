package gov.iti.sakila.presistence.repositories;

import java.util.Date;
import java.util.List;

import gov.iti.sakila.presistence.dtos.film.FilmDtoWithCountForStore;
import gov.iti.sakila.presistence.dtos.film.StoreFilmInventoryDto;
import gov.iti.sakila.presistence.dtos.store.StoreInventoryDto;
import gov.iti.sakila.presistence.entities.*;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import jakarta.ws.rs.NotFoundException;

public class FilmRepository extends GenericRepository<Film , Short> {

    FilmActorRepository filmActorRepository = new FilmActorRepository();
    ActorRepository actorRepository;
    public FilmRepository(ActorRepository actorRepository){
        super(Film.class);
        this.actorRepository = actorRepository;
    }
    public FilmRepository(){
        super(Film.class);
        actorRepository = new ActorRepository(this);
    }
    public List<Film> findByReleaseYear(Date releaseYear){
        return findListObjByNamedQuery("Film.findByReleaseYear", "releaseYear", releaseYear);

    }
    public List<Film> findByTitle(String title){
        return findListObjByNamedQuery("Film.findByTitle", "title", title);

    }
    public List<Film> findByRating(String rating){
        return findListObjByNamedQuery("Film.findByRating", "rating", rating);
    }
    public List<Film> findByRentalDuration(String rentalDuration){
        return findListObjByNamedQuery("Film.findByRentalDuration", "rentalDuration", rentalDuration);

    }
    public List<Film> findByReplacementCost(String replacementCost){
        return findListObjByNamedQuery("Film.findByReplacementCost", "replacementCost", replacementCost);

    }
    public List<Film> findByLength(Short length){
        return findListObjByNamedQuery("Film.findByLength", "length", length);

    }
    public List<Actor> findFilmActors(Short filmId){
        Film film = findById(filmId);
        if(film==null)
            throw new NotFoundException("Film Not Found");
       return film.getFilmActorList().stream().map(FilmActor::getActor).toList();
    }
    public int addActorToFilm(Short filmId, Short actorId){
      return actorRepository.addFilmToActor(actorId,filmId);
    }
    public FilmDtoWithCountForStore findNumberOfActors(Short filmId) {

/*        String q = "SELECT NEW gov.iti.sakila.presistence.dtos.film.FilmDtoWithCountForStore(f, count(a)," +
                " ( SELECT ac2 " +
                " from Actor ac2 " +
                "where ac2 = a.actor) " +
                " )  " +
                "FROM Film f " +
                "JOIN f.filmActorList a " +
                "WHERE f.filmId = :filmId "
               + "GROUP BY f"*/
                ;
        /*String q = "SELECT NEW gov.iti.sakila.presistence.dtos.film.FilmDtoWithCountForStore(f, count(a), collect(ac)) " +
                "FROM Film f " +
                "JOIN f.filmActorList a " +
                "JOIN  a.actor ac "+
                "WHERE f.filmId = :filmId " +
                "GROUP BY f";*/

        String q = "SELECT NEW gov.iti.sakila.presistence.dtos.film.FilmDtoWithCountForStore(f, count(a)) " +
                "FROM Film f " +
                "JOIN f.filmActorList a " +
                "WHERE f.filmId = :filmId " +
                "GROUP BY f";
        TypedQuery<FilmDtoWithCountForStore> query = entityManager.createQuery(q , FilmDtoWithCountForStore.class);
        query.setParameter("filmId", filmId);
        List<FilmDtoWithCountForStore> resultList = query.getResultList();
        FilmDtoWithCountForStore resultMap = (FilmDtoWithCountForStore) resultList.get(0);
        return resultMap;
    }


    public long findFilmCountInStock(Short filmId){
        String jpql =
                "select" +
                "( (select count(i.inventoryId) from Inventory i where i.filmId.id=:filmId) - count(r.inventoryId) ) " +
                "from Inventory inv " +
                "join Rental r " +
                "on r.inventoryId.id = inv.inventoryId " +
                "where inv.filmId.id=:filmId and r.returnDate is null";
        Query query = entityManager.createQuery(jpql);
        query.setParameter("filmId",filmId);
        return ((Long) query.getSingleResult());
    }
    public long filmCountInStockV2(Short filmId){
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Inventory> inventory = cq.from(Inventory.class);
        Join<Inventory, Rental> rental = inventory.join("rentalList", JoinType.INNER);
        cq.select(cb.diff(
                cb.count(inventory.get("id")),
                cb.count(rental.get("inventoryId"))
        )).where(
                cb.and(
                        cb.equal(inventory.get("filmId").get("id"), filmId),
                        cb.isNull(rental.get("returnDate"))
                )
        ).groupBy(inventory.get("id"));
        TypedQuery<Long> query = entityManager.createQuery(cq);
        return ((Long) query.getSingleResult());
    }


    public long findCountFilmRentals(Short filmId){
        String jpql = "select count(inv.inventoryId)" +
                "from Inventory inv " +
                "join Rental r " +
                "ON r.inventoryId.inventoryId = inv.inventoryId "+
                "where inv.filmId.id=:filmId";
        Query query = entityManager.createQuery(jpql);
        query.setParameter("filmId",filmId);
        return ((Long) query.getSingleResult());
    }
    public List<StoreInventoryDto> findWhereFilmAvailable(Short filmId){
        String jpql ="select new gov.iti.sakila.presistence.dtos.store." +
                "StoreInventoryDto(i.storeId.id, i.inventoryId,i.storeId.addressId) " +
                "from Inventory i " +
                "where i.inventoryId not in " +
                "(select r.inventoryId.id " +
                " from Rental r" +
                " where r.returnDate is null) " +
                " and i.filmId.id=:filmId";
        Query query = entityManager.createQuery(jpql,List.class);
        query.setParameter("filmId",filmId);
        return (List<StoreInventoryDto>) query.getResultList();
    }
    public List<Store> findFilmAvailableInWhichStore(Short filmId){
        String jpql ="select distinct(i.storeId) from Inventory i " +
                " where i.inventoryId not in " +
                " (select r.inventoryId.id" +
                " from Rental r" +
                " where r.returnDate is null) " +
                " and i.filmId.id=:filmId";
        Query query = entityManager.createQuery(jpql,List.class);
        query.setParameter("filmId",filmId);
        return (List<Store>) query.getResultList();
    }


}
