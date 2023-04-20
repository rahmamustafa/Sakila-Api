package gov.iti.sakila.business.services.servicesimpl;

import java.time.Instant;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import gov.iti.sakila.business.mappers.ActorMapper;
import gov.iti.sakila.business.mappers.CustomerMapper;
import gov.iti.sakila.business.mappers.FilmMapper;
import gov.iti.sakila.business.mappers.StoreMapper;
import gov.iti.sakila.business.util.ValidatorHandler;
import gov.iti.sakila.business.services.dtos.customer.CustomerDto;
import gov.iti.sakila.business.services.dtos.film.FilmDtoWithCountForStore;
import gov.iti.sakila.business.services.dtos.store.StoreDto;
import gov.iti.sakila.business.services.dtos.actor.ActorDto;
import gov.iti.sakila.business.services.dtos.film.FilmDto;
import gov.iti.sakila.business.services.dtos.film.FilmDtoCreate;
import gov.iti.sakila.business.services.dtos.store.StoreInventoryDto;
import gov.iti.sakila.presistence.entities.*;
import gov.iti.sakila.presistence.repositories.ActorRepository;
import gov.iti.sakila.presistence.repositories.CategoryRepository;
import gov.iti.sakila.presistence.repositories.FilmRepository;
import gov.iti.sakila.presistence.repositories.LanguageRepository;
import gov.iti.sakila.presistence.repositories.repositoriesimpl.ActorRepositoryImpl;
import gov.iti.sakila.presistence.repositories.repositoriesimpl.CategoryRepositoryImpl;
import gov.iti.sakila.presistence.repositories.repositoriesimpl.FilmRepositoryImpl;
import gov.iti.sakila.presistence.repositories.repositoriesimpl.LanguageRepositoryImpl;
import jakarta.ws.rs.NotFoundException;
import lombok.NonNull;

public class FilmServiceImpl implements gov.iti.sakila.business.services.FilmService {

    private final FilmRepository filmRepository = new FilmRepositoryImpl();
    private final LanguageRepository languageRepository = new LanguageRepositoryImpl();
    private final ActorRepository actorRepository = new ActorRepositoryImpl();
    private final CategoryRepository categoryRepository = new CategoryRepositoryImpl();
    private final ValidatorHandler validatorHandler = ValidatorHandler.getInstance();
    private final String ERROR_MESSAGE = "Please Enter Valid Data";

    @Override
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
    @Override
    public FilmDto findFilmById(@NonNull Short filmId){
        if(filmId<=0)
            throw new IllegalArgumentException(ERROR_MESSAGE);
        Film film = filmRepository.findById(filmId);
        return FilmMapper.INSTANCE.filmToFilmDto(film);
    }
    @Override
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
    @Override
    public List<FilmDto> findAllFilms(){
        List<Film> films = Collections.unmodifiableList(filmRepository.findAll());
        if(films.size()==0)
            throw new NotFoundException("Not Found");
        return films.stream().map(FilmMapper.INSTANCE::filmToFilmDto).toList();
    }
    @Override
    public List<ActorDto> findFilmActors(@NonNull Short filmId){
        if(filmId<=0)
            throw new IllegalArgumentException(ERROR_MESSAGE);
        List<Actor> filmActors = filmRepository.findFilmActors(filmId);
        if(filmActors.size()==0)
            throw new NotFoundException("Not Found");
        return filmActors.stream().map(ActorMapper.INSTANCE::actorToActorDto).toList();
    }
    @Override
    public String findFilmLanguage(@NonNull Short filmId){
        return findFilmById(filmId).getLanguage();
    }
    @Override
    public String findFilmOriginalLanguage(@NonNull Short filmId){
        return findFilmById(filmId).getOriginalLanguage();
    }
    @Override
    public List<FilmDto> findFilmByReleaseYear(@NonNull Date releaseYear){
        List<Film> films = filmRepository.findByReleaseYear(releaseYear);
        return films.stream().map(FilmMapper.INSTANCE::filmToFilmDto).toList();
    }
    @Override
    public List<String> findFilmCategories(@NonNull Short filmId){
        if(filmId<=0)
            throw new IllegalArgumentException(ERROR_MESSAGE);
        Film film = filmRepository.findById(filmId);
        List<Category> categories = film.getFilmCategoryList().stream().map(FilmCategory::getCategory).toList();
        if(categories.size()==0)
            throw new NotFoundException("Not Found");
        return categories.stream().map(Category::getName).toList();
    }
    @Override
    public List<FilmDto> findByRating(@NonNull String rating){
        if(rating.isBlank())
            throw new IllegalArgumentException("Please enter rating");
        List<Film> films = filmRepository.findByRating(rating);
        return films.stream().map(FilmMapper.INSTANCE::filmToFilmDto).toList();
    }

    @Override
    public List<FilmDto> findByRentalDuration(@NonNull String rentalDuration){
        if(rentalDuration.isBlank())
            throw new IllegalArgumentException("Please enter rental duration");
        List<Film> films = filmRepository.findByRentalDuration(rentalDuration);
        return films.stream().map(FilmMapper.INSTANCE::filmToFilmDto).toList();

    }
    @Override
    public List<FilmDto> findFilmByReplacementCost(@NonNull String replacementCost){
        if(replacementCost.isBlank())
            throw new IllegalArgumentException("Please enter replacement cost");
        List<Film> films = filmRepository.findByReplacementCost(replacementCost);
        return films.stream().map(FilmMapper.INSTANCE::filmToFilmDto).toList();

    }
    @Override
    public List<FilmDto> findFilmByLength(@NonNull Short length){
        List<Film> films = filmRepository.findByLength(length);
        return films.stream().map(FilmMapper.INSTANCE::filmToFilmDto).toList();
    }
    // return customers bought film
    @Override
    public List<CustomerDto> findFilmCustomers(@NonNull Short filmId){
        if(filmId<=0)
            throw new IllegalArgumentException(ERROR_MESSAGE);
        Film film = filmRepository.findById(filmId);
        List<Customer> customers = film.getInventoryList().stream().flatMap(inv -> inv.getStoreId().getCustomerList().stream()).toList();
        if(customers.size()==0)
            throw new NotFoundException("Not Found");
        return customers.stream().map(CustomerMapper.INSTANCE::customertoCustomerDto).toList();
    }
//    // return stores
    @Override
    public List<StoreDto> findFilmStores(@NonNull Short filmId){
        if(filmId<=0)
            throw new IllegalArgumentException(ERROR_MESSAGE);
        Film film = filmRepository.findById(filmId);
        List<Store> stores = film.getInventoryList().stream().map(inv -> inv.getStoreId()).toList();
        if(stores.size()==0)
            throw new NotFoundException("Not Found");
        return stores.stream().map(StoreMapper.INSTANCE::storetoStoreDto).toList();
    }

    @Override
    public int addActorToFilm(@NonNull Short filmId, @NonNull Short actorId){
        if(filmId<=0 || actorId<0)
            throw new IllegalArgumentException(ERROR_MESSAGE);
        return filmRepository.addActorToFilm(filmId,actorId);
    }

    @Override
    public int addCategoryToFilm(@NonNull Short filmId, @NonNull Short categoryId){
        if(filmId<=0 || categoryId<0)
            throw new IllegalArgumentException(ERROR_MESSAGE);
        return categoryRepository.addFilmToCategory(filmId,categoryId);
    }
    @Override
    public List<FilmDto> findFilmByTitle(@NonNull String title){
        if(title.isBlank())
            throw new IllegalArgumentException("Please enter title");
        List<Film> films = filmRepository.findByTitle(title).stream().toList();
        return films.stream().map(FilmMapper.INSTANCE::filmToFilmDto).toList();
    }
    @Override
    public FilmDtoWithCountForStore findNumberOfActors(@NonNull Short filmId){
        if(filmId<=0)
            throw new IllegalArgumentException(ERROR_MESSAGE);
        return filmRepository.findNumberOfActors(filmId);
    }

    @Override
    public long findFilmCountInStock(@NonNull Short filmId){
        if(filmId<=0)
            throw new IllegalArgumentException(ERROR_MESSAGE);
        return filmRepository.findFilmCountInStock(filmId);
    }

    @Override
    public long findFilmRentalCount(@NonNull Short filmId){
        if(filmId<=0)
            throw new IllegalArgumentException(ERROR_MESSAGE);
        return filmRepository.findFilmRentalCount(filmId);
    }
    @Override
    public List<StoreInventoryDto> findWhereFilmAvailable(@NonNull Short filmId){
        if(filmId<=0)
            throw new IllegalArgumentException(ERROR_MESSAGE);
        return filmRepository.findWhereFilmAvailable(filmId);
    }
    @Override
    public List<StoreDto> findFilmAvailableInWhichStore(@NonNull Short filmId){
        if(filmId<=0)
            throw new IllegalArgumentException(ERROR_MESSAGE);
        List<Store> stores = filmRepository.findFilmAvailableInWhichStore(filmId);
        return stores.stream().map(StoreMapper.INSTANCE::storetoStoreDto).toList();
    }
}
