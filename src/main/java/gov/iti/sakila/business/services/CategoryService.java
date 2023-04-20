package gov.iti.sakila.business.services;

import gov.iti.sakila.business.services.dtos.CategoryDto;
import gov.iti.sakila.business.services.dtos.film.FilmDto;
import lombok.NonNull;

import java.util.List;

public interface CategoryService {
    CategoryDto createCategory(@NonNull String categoryName);

    CategoryDto findCategoryById(@NonNull Short categoryId);

    int deleteCategoryById(@NonNull Short categoryId);

    //    public Category update(Category category){
    //        if(findById(category.getCategoryId()) == null)
    //            return null;
    //        return categoryRepository.update(category);
    //    }
    List<CategoryDto> findAllCategories();

    CategoryDto findCategoryByName(@NonNull String name);

    List<FilmDto> findCategoryFilms(@NonNull Short categoryId);

    int addFilmToCategory(@NonNull Short filmId, @NonNull Short categoryId);
}
