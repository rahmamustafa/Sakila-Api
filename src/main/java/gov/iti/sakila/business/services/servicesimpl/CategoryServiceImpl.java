package gov.iti.sakila.business.services.servicesimpl;

import java.time.Instant;
import java.util.Date;
import java.util.List;

import gov.iti.sakila.business.mappers.CategoryMapper;
import gov.iti.sakila.business.services.dtos.CategoryDto;
import gov.iti.sakila.business.services.dtos.film.FilmDto;
import gov.iti.sakila.presistence.entities.Category;
import gov.iti.sakila.presistence.repositories.CategoryRepository;
import gov.iti.sakila.presistence.repositories.repositoriesimpl.CategoryRepositoryImpl;
import jakarta.ws.rs.NotFoundException;
import lombok.NonNull;

public class CategoryServiceImpl implements gov.iti.sakila.business.services.CategoryService {
    private CategoryRepository categoryRepository = new CategoryRepositoryImpl();
    private final String ERROR_MESSAGE = "Please Enter Valid Data";

    @Override
    public CategoryDto createCategory(@NonNull String categoryName){
        if(categoryName.isBlank())
            throw new IllegalArgumentException(ERROR_MESSAGE);
        Category category = new Category();
        category.setName(categoryName);
        category.setLastUpdate(Date.from(Instant.now()));
        categoryRepository.create(category);
        return CategoryMapper.INSTANCE.categoryToCategoryDto(category);
    }
    @Override
    public CategoryDto findCategoryById(@NonNull Short categoryId){
        if(categoryId<=0)
            throw new IllegalArgumentException(ERROR_MESSAGE);
        Category category = categoryRepository.findById(categoryId);
        return CategoryMapper.INSTANCE.categoryToCategoryDto(category);
    }
    @Override
    public int deleteCategoryById(@NonNull Short categoryId){
        if(categoryId<=0)
            throw new IllegalArgumentException(ERROR_MESSAGE);
        return categoryRepository.deleteById(categoryId);
    }
//    public Category update(Category category){
//        if(findById(category.getCategoryId()) == null)
//            return null;
//        return categoryRepository.update(category);
//    }
    @Override
    public List<CategoryDto> findAllCategories(){
         List<Category> categories =  categoryRepository.findAll();
         if(categories.size()==0)
             throw new NotFoundException("Not Found");
        return categories.stream().map(CategoryMapper.INSTANCE::categoryToCategoryDto).toList();
    }
    @Override
    public CategoryDto findCategoryByName(@NonNull String name){
        if(name.isBlank())
            throw new IllegalArgumentException(ERROR_MESSAGE);
        Category category = categoryRepository.findByName(name);
        return CategoryMapper.INSTANCE.categoryToCategoryDto(category);

    }
    @Override
    public List<FilmDto> findCategoryFilms(@NonNull Short categoryId){
        if(categoryId<=0)
            throw new IllegalArgumentException(ERROR_MESSAGE);
        Category category = categoryRepository.findById(categoryId);
        return CategoryMapper.INSTANCE.categoryToCategoryDto(category).getFilmList();
    }
    @Override
    public int addFilmToCategory(@NonNull Short filmId, @NonNull Short categoryId){
        if(categoryId<=0  || filmId<=0)
            throw new IllegalArgumentException(ERROR_MESSAGE);
        return categoryRepository.addFilmToCategory(filmId,categoryId);
    }
    


}