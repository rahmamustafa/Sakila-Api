package gov.iti.sakila.presistence.repositories;

import gov.iti.sakila.presistence.entities.Category;
import gov.iti.sakila.presistence.entities.Film;
import gov.iti.sakila.presistence.entities.FilmCategory;

import java.util.List;

public class LanguageRepository extends GenericRepository<Category,Short > {

    public LanguageRepository(){
        super(Category.class);
    }
    public List<Category> findByName(String name){
        return findListObjByNamedQuery("Category.findByName", "name", name);

    }
    public List<Film> findCategeoryFilms(Short categoryId){
        Category category = findById(categoryId);
        return category.getFilmCategoryList().stream().map(FilmCategory::getFilm).toList();
    }
    
}
