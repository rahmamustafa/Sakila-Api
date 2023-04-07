package gov.iti.sakila.presistence.repositories;

import java.util.List;

import gov.iti.sakila.presistence.entities.Category;
import gov.iti.sakila.presistence.entities.Film;
import gov.iti.sakila.presistence.entities.FilmCategory;

public class CategoryRepository extends GenericRepository<Category,Short > {

    public CategoryRepository(){
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
