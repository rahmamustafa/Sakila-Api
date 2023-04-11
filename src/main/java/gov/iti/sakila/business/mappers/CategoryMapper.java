package gov.iti.sakila.business.mappers;

import gov.iti.sakila.presistence.dtos.AddressDto;
import gov.iti.sakila.presistence.dtos.CategoryDto;
import gov.iti.sakila.presistence.dtos.StaffDto;
import gov.iti.sakila.presistence.dtos.film.FilmDto;
import gov.iti.sakila.presistence.dtos.store.StoreDto;
import gov.iti.sakila.presistence.entities.*;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CategoryMapper {
    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);


    @Mapping(source = "filmCategoryList", target = "filmList", qualifiedByName = "mapFilm")
    CategoryDto categoryToCategoryDto(Category category);

    @Named("mapFilm")
    default List<FilmDto> mapLanguageName(List<FilmCategory> filmCategoryList) {
        List<Film> films = filmCategoryList.stream().map(FilmCategory::getFilm).toList();
        return films.stream().map(FilmMapper.INSTANCE::filmToFilmDto).toList();
    }


}