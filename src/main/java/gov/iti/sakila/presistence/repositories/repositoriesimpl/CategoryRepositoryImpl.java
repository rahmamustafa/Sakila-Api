package gov.iti.sakila.presistence.repositories.repositoriesimpl;

import gov.iti.sakila.presistence.entities.Category;
import gov.iti.sakila.presistence.entities.FilmCategory;
import gov.iti.sakila.presistence.repositories.CategoryRepository;
import jakarta.ws.rs.NotFoundException;

import java.sql.Date;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

public class CategoryRepositoryImpl extends GenericRepositoryImpl<Category,Short > implements CategoryRepository {

    FilmCategoryRepositoryImpl filmCategoryRepository = new FilmCategoryRepositoryImpl();
    FilmRepositoryImpl filmRepository = new FilmRepositoryImpl();
    public CategoryRepositoryImpl(){
        super(Category.class);
    }
    @Override
    public Category findByName(String name){

        List<Category> categories = findListObjByNamedQuery("Category.findByName", "name", name);
        Optional<Category> category = categories.stream().findFirst();
        if(category.isEmpty())
            throw new NotFoundException("Category Not Found");
        return category.get();
    }
    @Override
    public int addFilmToCategory(Short filmId,Short categoryId){
        findById(categoryId);
        filmRepository.findById(filmId);
        FilmCategory filmCategory  = new FilmCategory(filmId, categoryId);
        if(filmCategoryRepository.checkExist(filmCategory.getFilmCategoryPK()))
            throw new IllegalArgumentException("This category has this film");
        filmCategory.setLastUpdate(Date.from(Instant.now()));
        filmCategoryRepository.create(filmCategory);
        return 1;
    }
    
}
