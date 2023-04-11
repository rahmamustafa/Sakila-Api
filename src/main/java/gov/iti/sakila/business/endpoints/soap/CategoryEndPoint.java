package gov.iti.sakila.business.endpoints.soap;

import gov.iti.sakila.business.mappers.CategoryMapper;
import gov.iti.sakila.business.services.CategoryService;
import gov.iti.sakila.presistence.dtos.CategoryDto;
import gov.iti.sakila.presistence.dtos.film.FilmDto;
import gov.iti.sakila.presistence.entities.Category;
import gov.iti.sakila.presistence.repositories.CategoryRepository;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;

import java.time.Instant;
import java.util.Date;
import java.util.List;

@WebService
public class CategoryEndPoint {
    private final CategoryService categoryService = new CategoryService();

    public CategoryDto createCategory(@WebParam(name = "name") String categoryName){
        return categoryService.createCategory(categoryName);
    }
    public CategoryDto findCategoryById(@WebParam(name = "id")Short categoryId){
        return categoryService.findCategoryById(categoryId);
    }
    public int deleteCategoryById(@WebParam(name = "id")Short categoryId){
        return categoryService.deleteCategoryById(categoryId);
    }
//    public Category update(Category category){
//        return categoryService.update(category);
//    }
    public List<CategoryDto> findAllCategories(){
        return categoryService.findAllCategories();
    }
    public CategoryDto findCategoryByName(@WebParam(name = "name")String name){
        return categoryService.findCategoryByName(name);

    }
    public List<FilmDto> findCategoryFilms(@WebParam(name = "id")Short CategoryId){
        return categoryService.findCategoryFilms(CategoryId);
    }
    public int addFilmToCategory(@WebParam(name = "filmId")Short filmId ,@WebParam(name = "categoryId") Short categoryId){
        return categoryService.addFilmToCategory(filmId,categoryId);
    }
    


}