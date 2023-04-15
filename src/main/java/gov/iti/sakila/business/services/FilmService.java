package gov.iti.sakila.business.services;

import java.time.Instant;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import gov.iti.sakila.business.mappers.ActorMapper;
import gov.iti.sakila.business.mappers.CustomerMapper;
import gov.iti.sakila.business.mappers.FilmMapper;
import gov.iti.sakila.business.mappers.StoreMapper;
import gov.iti.sakila.business.util.ValidatorHandler;
import gov.iti.sakila.presistence.dtos.customer.CustomerDto;
import gov.iti.sakila.presistence.dtos.film.FilmDtoWithCountForStore;
import gov.iti.sakila.presistence.dtos.film.StoreFilmInventoryDto;
import gov.iti.sakila.presistence.dtos.store.StoreDto;
import gov.iti.sakila.presistence.dtos.actor.ActorDto;
import gov.iti.sakila.presistence.dtos.film.FilmDto;
import gov.iti.sakila.presistence.dtos.film.FilmDtoCreate;
import gov.iti.sakila.presistence.dtos.store.StoreInventoryDto;
import gov.iti.sakila.presistence.entities.*;
import gov.iti.sakila.presistence.repositories.ActorRepository;
import gov.iti.sakila.presistence.repositories.CategoryRepository;
import gov.iti.sakila.presistence.repositories.FilmRepository;
import gov.iti.sakila.presistence.repositories.LanguageRepository;
import jakarta.ws.rs.NotFoundException;
import lombok.NonNull;

public class FilmService {

    private final FilmRepository filmRepository = new FilmRepository();
    private final LanguageRepository languageRepository = new LanguageRepository();
    private final ActorRepository actorRepository = new ActorRepository();
    private final CategoryRepository categoryRepository = new CategoryRepository();
    private final ValidatorHandler validatorHandler = ValidatorHandler.getInstance();
    private final String ERROR_MESSAGE = "Please Enter Valid Data";


    public FilmDto createFilm(FilmDtoCreate filmDtoCreate){
        validatorHandler.validate(filmDtoCreate);
        if(filmDtoCreate.getReplacementCost()==null)
            throw new IllegalArgumentException("Please enter replacement cost");
        Film film = FilmMapper.INSTANCE.filmDtoCreateToFilm(filmDtoCreate);
        film.setLastUpdate(Date.from(Instant.now()));
        film.setLanguageId(languageRepository.findById(filmDtoCreate.getLanguageId()));
        if(filmDtoCreate.getOriginalLanguageId()!=null)
            film.setOriginalLanguageId(languageRepository.findById(filmDtoCreate.getOriginalLanguageId()));
        film = filmRepository.create(film);
        return FilmMapper.INSTANCE.filmToFilmDto(film);
    }
    public FilmDto findFilmById(@NonNull Short filmId){
        if(filmId<=0)
            throw new IllegalArgumentException(ERROR_MESSAGE);
        Film film = filmRepository.findById(filmId);
        if(film==null)
            throw new NotFoundException("Film Not Found");
        return FilmMapper.INSTANCE.filmToFilmDto(film);
    }
    public int deleteFilmById(@NonNull Short filmId){
        if(filmId<=0)
            throw new IllegalArgumentException(ERROR_MESSAGE);
        return filmRepository.deleteById(filmId);
    }
//    public Film update(Film film){
//        if(findFilmById(film.getFilmId()) == null)
//            return null;
//        return filmRepository.update(film);
//    }
    public List<FilmDto> findAllFilms(){
        List<Film> films = Collections.unmodifiableList(filmRepository.findAll());
        if(films.size()==0)
            throw new NotFoundException("Not Found");
        return films.stream().map(FilmMapper.INSTANCE::filmToFilmDto).toList();
    }
    public List<ActorDto> findFilmActors(@NonNull Short filmId){
        if(filmId<=0)
            throw new IllegalArgumentException(ERROR_MESSAGE);
        List<Actor> filmActors = filmRepository.findFilmActors(filmId);
        if(filmActors.size()==0)
            throw new NotFoundException("Not Found");
        return filmActors.stream().map(ActorMapper.INSTANCE::actorToActorDto).toList();
    }
    public String findFilmLanguage(@NonNull Short filmId){
        return findFilmById(filmId).getLanguage();
    }
    public String findFilmOriginalLanguage(@NonNull Short filmId){
        return findFilmById(filmId).getOriginalLanguage();
    }
    public List<FilmDto> findFilmByReleaseYear(@NonNull Date releaseYear){
        List<Film> films = filmRepository.findByReleaseYear(releaseYear);
        if(films.size()==0)
            throw new NotFoundException("Not Found");
        return films.stream().map(FilmMapper.INSTANCE::filmToFilmDto).toList();
    }
    public List<String> findFilmCategories(@NonNull Short filmId){
        if(filmId<=0)
            throw new IllegalArgumentException(ERROR_MESSAGE);
        Film film = filmRepository.findById(filmId);
        if(film==null)
            throw new NotFoundException("Film Not Found");
        List<Category> categories = film.getFilmCategoryList().stream().map(FilmCategory::getCategory).toList();
        if(categories.size()==0)
            throw new NotFoundException("Not Found");
        return categories.stream().map(Category::getName).toList();
    }
    public List<FilmDto> findByRating(@NonNull String rating){
        if(rating.isBlank())
            throw new IllegalArgumentException("Please enter rating");
        List<Film> films = filmRepository.findByRating(rating);
        if(films.size()==0)
            throw new NotFoundException("Not Found");
        return films.stream().map(FilmMapper.INSTANCE::filmToFilmDto).toList();
    }

    public List<FilmDto> findByRentalDuration(@NonNull String rentalDuration){
        if(rentalDuration.isBlank())
            throw new IllegalArgumentException("Please enter rental duration");
        List<Film> films = filmRepository.findByRentalDuration(rentalDuration);
        if(films.size()==0)
            throw new NotFoundException("Not Found");
        return films.stream().map(FilmMapper.INSTANCE::filmToFilmDto).toList();

    }
    public List<FilmDto> findFilmByReplacementCost(@NonNull String replacementCost){
        if(replacementCost.isBlank())
            throw new IllegalArgumentException("Please enter replacement cost");
        List<Film> films = filmRepository.findByReplacementCost(replacementCost);
        if(films.size()==0)
            throw new NotFoundException("Not Found");
        return films.stream().map(FilmMapper.INSTANCE::filmToFilmDto).toList();

    }
    public List<FilmDto> findFilmByLength(@NonNull Short length){
        List<Film> films = filmRepository.findByLength(length);
        if(films.size()==0)
            throw new NotFoundException("Not Found");
        return films.stream().map(FilmMapper.INSTANCE::filmToFilmDto).toList();
    }
    // return customers bought film
    public List<CustomerDto> findFilmCustomers(@NonNull Short filmId){
        if(filmId<=0)
            throw new IllegalArgumentException(ERROR_MESSAGE);
        Film film = filmRepository.findById(filmId);
        if(film==null)
            throw new NotFoundException("Film Not Found");
        List<Customer> customers = film.getInventoryList().stream().flatMap(inv -> inv.getStoreId().getCustomerList().stream()).toList();
        if(customers.size()==0)
            throw new NotFoundException("Not Found");
        return customers.stream().map(CustomerMapper.INSTANCE::customertoCustomerDto).toList();
    }
//    // return stores
    public List<StoreDto> findFilmStores(@NonNull Short filmId){
        if(filmId<=0)
            throw new IllegalArgumentException(ERROR_MESSAGE);
        Film film = filmRepository.findById(filmId);
        if(film==null)
            throw new NotFoundException("Film Not Found");
        List<Store> stores = film.getInventoryList().stream().map(inv -> inv.getStoreId()).toList();
        if(stores.size()==0)
            throw new NotFoundException("Not Found");
        return stores.stream().map(StoreMapper.INSTANCE::storetoStoreDto).toList();
    }

    public int addActorToFilm(@NonNull Short filmId, @NonNull Short actorId ){
        if(filmId<=0 || actorId<0)
            throw new IllegalArgumentException(ERROR_MESSAGE);
        return filmRepository.addActorToFilm(filmId,actorId);
    }

    public int addCategoryToFilm(@NonNull Short filmId,@NonNull Short categoryId){
        if(filmId<=0 || categoryId<0)
            throw new IllegalArgumentException(ERROR_MESSAGE);
        return categoryRepository.addFilmToCategory(filmId,categoryId);
    }
    public List<FilmDto> findFilmByTitle(@NonNull String title){
        if(title.isBlank())
            throw new IllegalArgumentException("Please enter title");
        List<Film> films = filmRepository.findByTitle(title).stream().toList();
        if(films.size()==0)
            throw new NotFoundException("Not Found");
        return films.stream().map(FilmMapper.INSTANCE::filmToFilmDto).toList();
    }
    public FilmDtoWithCountForStore findNumberOfActors(@NonNull Short filmId){
        if(filmId<=0)
            throw new IllegalArgumentException(ERROR_MESSAGE);
        return filmRepository.findNumberOfActors(filmId);
    }

    public long findFilmCountInStock(@NonNull Short filmId){
        if(filmId<=0)
            throw new IllegalArgumentException(ERROR_MESSAGE);
        return filmRepository.findFilmCountInStock(filmId);
    }

    public long findCountFilmRentals(@NonNull Short filmId){
        if(filmId<=0)
            throw new IllegalArgumentException(ERROR_MESSAGE);
        return filmRepository.findCountFilmRentals(filmId);
    }
    public List<StoreInventoryDto> findWhereFilmAvailable(@NonNull Short filmId){
        if(filmId<=0)
            throw new IllegalArgumentException(ERROR_MESSAGE);
        return filmRepository.findWhereFilmAvailable(filmId);
    }
    public List<StoreDto> findFilmAvailableInWhichStore(@NonNull Short filmId){
        if(filmId<=0)
            throw new IllegalArgumentException(ERROR_MESSAGE);
        List<Store> stores = filmRepository.findFilmAvailableInWhichStore(filmId);
        return stores.stream().map(StoreMapper.INSTANCE::storetoStoreDto).toList();
    }
}
