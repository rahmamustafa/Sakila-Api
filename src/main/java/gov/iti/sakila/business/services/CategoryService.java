package gov.iti.sakila.business.services;

import java.time.Instant;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import gov.iti.sakila.business.mappers.CategoryMapper;
import gov.iti.sakila.presistence.dtos.CategoryDto;
import gov.iti.sakila.presistence.dtos.film.FilmDto;
import gov.iti.sakila.presistence.entities.Category;
import gov.iti.sakila.presistence.entities.Film;
import gov.iti.sakila.presistence.repositories.CategoryRepository;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
@WebService
public class CategoryService {
    private CategoryRepository categoryRepository = new CategoryRepository();

    public CategoryDto createCategory(@WebParam(name = "name") String categoryName){
        Category category = new Category();
        category.setName(categoryName);
        category.setLastUpdate(Date.from(Instant.now()));
        categoryRepository.create(category);
        return CategoryMapper.INSTANCE.categoryToCategoryDto(category);
    }
    public CategoryDto findCategoryById(@WebParam(name = "id")Short categoryId){
        return CategoryMapper.INSTANCE.categoryToCategoryDto(categoryRepository.findById(categoryId));
    }
    public boolean deleteCategoryById(@WebParam(name = "id")Short categoryId){
        return categoryRepository.deleteById(categoryId);
    }
//    public Category update(Category category){
//        if(findById(category.getCategoryId()) == null)
//            return null;
//        return categoryRepository.update(category);
//    }
    public List<CategoryDto> findAllCategories(){
         List<Category> categories =  categoryRepository.findAll();
        return categories.stream().map(CategoryMapper.INSTANCE::categoryToCategoryDto).toList();
    }
    public CategoryDto findCategoryByName(@WebParam(name = "name")String name){
        return CategoryMapper.INSTANCE.categoryToCategoryDto(categoryRepository.findByName(name));

    }
    public List<FilmDto> findCategoryFilms(@WebParam(name = "id")Short CategoryId){
        return CategoryMapper.INSTANCE.categoryToCategoryDto(categoryRepository.findById(CategoryId)).getFilmList();
    }
    public int addFilmToCategory(@WebParam(name = "filmId")Short filmId ,@WebParam(name = "categoryId") Short categoryId){
        return categoryRepository.addFilmToCategory(filmId,categoryId);
    }
    


}