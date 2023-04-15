package gov.iti.sakila;

import gov.iti.sakila.presistence.repositories.FilmRepository;
import gov.iti.sakila.presistence.repositories.InventoryRepository;
import gov.iti.sakila.presistence.repositories.StoreRepository;

public class Main {
    public static void main(String[] args) {
       // FilmService filmService = new FilmService();
        FilmRepository filmRepository = new FilmRepository();
        StoreRepository storeRepository = new StoreRepository();
        InventoryRepository inventoryRepository = new InventoryRepository();
//        ActorService actorService = new ActorService();
//        StoreService storeService = new StoreService();
       
//        Film film = new Film("Rahma",Short.valueOf("6"),BigDecimal.valueOf(20.99),
//                                BigDecimal.valueOf(20.99),Date.from(Instant.now()));
//
//        EntityManager e =  JpaManagerSingleton.INSTANCE.getEntityManager();
        // GenericRepository g = new GenericRepository(Language.class);
        // Language language = new Language("A",Date.from(Instant.now()));
        // g.create(language);
//        film.setLanguageId(e.find(Language.class, 1));
//        filmService.create(film);

        //System.out.println( actorService.deleteById(Short.valueOf("201")));
//        System.out.println( actorService.findActorFilms(Short.valueOf("2")));
        //FilmDtoCreate filmDtoCreate = new FilmDtoCreate( "title", "description",

//        Short.valueOf("15") , BigDecimal.valueOf(15) , Short.valueOf("5") , BigDecimal.valueOf(5),
//                 "G",  "Trailers",Short.valueOf("1") );
////        System.out.println( actorService.addFilmToActor(Short.valueOf("202"),Short.valueOf("2")));
//        System.out.println(filmService.createFilm(filmDtoCreate));
//        System.out.println(storeService.findNumberOfEachFilm((short) 1));
//        System.out.println(storeRepository.getRentalCount());
//        System.out.println(inventoryRepository.findFilmRentedInventory(Short.valueOf("2")).toString());
    }
}
