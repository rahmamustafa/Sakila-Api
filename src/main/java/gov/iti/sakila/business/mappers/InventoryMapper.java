package gov.iti.sakila.business.mappers;

import gov.iti.sakila.business.services.dtos.InventoryDto;
import gov.iti.sakila.business.services.dtos.RentalDto;
import gov.iti.sakila.business.services.dtos.film.FilmDto;
import gov.iti.sakila.business.services.dtos.store.StoreDto;
import gov.iti.sakila.presistence.entities.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper
public interface InventoryMapper {
    InventoryMapper INSTANCE = Mappers.getMapper(InventoryMapper.class);

    Inventory toEntity(RentalDto rentalDto);

    @Mapping(source = "filmId", target = "film", qualifiedByName = "mapFilm")
    @Mapping(source = "storeId", target = "store", qualifiedByName = "mapStore")
    InventoryDto toDto(Inventory inventory);

    @Named("mapStore")
    default StoreDto mapStore(Store store) {
        return StoreMapper.INSTANCE.storetoStoreDto(store);
    }

    @Named("mapFilm")
    default FilmDto mapFilm(Film film) {
        return FilmMapper.INSTANCE.filmToFilmDto(film);
    }
}