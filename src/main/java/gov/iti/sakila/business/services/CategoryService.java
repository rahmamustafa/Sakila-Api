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
import jakarta.ws.rs.NotFoundException;
import lombok.NonNull;

public class CategoryService {
    private CategoryRepository categoryRepository = new CategoryRepository();

    public CategoryDto createCategory(@NonNull String categoryName){
        if(categoryName.isBlank())
            throw new IllegalArgumentException("please enter valid data");
        Category category = new Category();
        category.setName(categoryName);
        category.setLastUpdate(Date.from(Instant.now()));
        categoryRepository.create(category);
        return CategoryMapper.INSTANCE.categoryToCategoryDto(category);
    }
    public CategoryDto findCategoryById(@NonNull Short categoryId){
        if(categoryId<=0)
            throw new IllegalArgumentException("please enter valid data");
        return CategoryMapper.INSTANCE.categoryToCategoryDto(categoryRepository.findById(categoryId));
    }
    public int deleteCategoryById(@NonNull Short categoryId){
        if(categoryId<=0)
            throw new IllegalArgumentException("please enter valid data");
        return categoryRepository.deleteById(categoryId);
    }
//    public Category update(Category category){
//        if(findById(category.getCategoryId()) == null)
//            return null;
//        return categoryRepository.update(category);
//    }
    public List<CategoryDto> findAllCategories(){
         List<Category> categories =  categoryRepository.findAll();
         if(categories.size()==0)
             throw new NotFoundException("Not Found");
        return categories.stream().map(CategoryMapper.INSTANCE::categoryToCategoryDto).toList();
    }
    public CategoryDto findCategoryByName(@NonNull String name){
        if(name.isBlank())
            throw new IllegalArgumentException("please enter valid data");
        return CategoryMapper.INSTANCE.categoryToCategoryDto(categoryRepository.findByName(name));

    }
    public List<FilmDto> findCategoryFilms(@NonNull Short categoryId){
        if(categoryId<=0)
            throw new IllegalArgumentException("please enter valid data");
        return CategoryMapper.INSTANCE.categoryToCategoryDto(categoryRepository.findById(categoryId)).getFilmList();
    }
    public int addFilmToCategory(@NonNull Short filmId ,@NonNull Short categoryId){
        if(categoryId<=0  || filmId<=0)
            throw new IllegalArgumentException("please enter valid data");
        return categoryRepository.addFilmToCategory(filmId,categoryId);
    }
    


}