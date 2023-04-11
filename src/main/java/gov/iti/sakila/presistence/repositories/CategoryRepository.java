package gov.iti.sakila.presistence.repositories;

import java.sql.Date;
import java.time.Instant;
import java.util.List;

import gov.iti.sakila.presistence.entities.Category;
import gov.iti.sakila.presistence.entities.Film;
import gov.iti.sakila.presistence.entities.FilmActor;
import gov.iti.sakila.presistence.entities.FilmCategory;
import jakarta.ws.rs.NotFoundException;

public class CategoryRepository extends GenericRepository<Category,Short > {

    FilmCategoryRepository filmCategoryRepository = new FilmCategoryRepository();
    FilmRepository filmRepository = new FilmRepository();
    public CategoryRepository(){
        super(Category.class);
    }
    public Category findByName(String name){
        return findListObjByNamedQuery("Category.findByName", "name", name).get(0);

    }
    public int addFilmToCategory(Short filmId,Short categoryId){
        if(findById(categoryId)==null)
            throw new NotFoundException("Category Not Found");;
        if(filmRepository.findById(filmId)==null)
            throw new NotFoundException("Film Not Found");
        FilmCategory filmCategory  = new FilmCategory(filmId, categoryId);
        if(filmCategoryRepository.findById(filmCategory.getFilmCategoryPK())!=null)
            throw new IllegalArgumentException("This category has this film");
        filmCategory.setLastUpdate(Date.from(Instant.now()));
        filmCategoryRepository.create(filmCategory);
        return 1;
    }
    
}
