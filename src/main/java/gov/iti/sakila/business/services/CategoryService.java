package gov.iti.sakila.business.services;

import java.util.Collections;
import java.util.List;

import gov.iti.sakila.presistence.entities.Category;
import gov.iti.sakila.presistence.entities.Film;
import gov.iti.sakila.presistence.repositories.CategoryRepository;
import jakarta.jws.WebService;
@WebService
public class CategoryService {
    private CategoryRepository categoryRepository = new CategoryRepository();

    public Category create(Category category){
        return categoryRepository.create(category);
    }
    public Category findById(Short categoryId){
        return categoryRepository.findById(categoryId);
    }
    public boolean deleteById(Short categoryId){
        return categoryRepository.deleteById(categoryId);
    }
    public Category update(Category category){
        if(findById(category.getCategoryId()) == null)
            return null;
        return categoryRepository.update(category);
    }
    public List<Category> findAll(){
        return Collections.unmodifiableList(categoryRepository.findAll());
    }
    public List<Category> findByName(String name){
        return categoryRepository.findByName(name);

    }
    public List<Film> findCategoryFilms(Short CategoryId){
        return categoryRepository.findCategeoryFilms(CategoryId);
    }
    


}