package gov.iti.sakila.business.mappers;

import gov.iti.sakila.business.services.dtos.RentalDto;
import gov.iti.sakila.business.services.dtos.customer.CustomerDto;
import gov.iti.sakila.business.services.dtos.film.FilmDto;
import gov.iti.sakila.presistence.entities.*;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RentalMapper {
    RentalMapper INSTANCE = Mappers.getMapper(RentalMapper.class);

    Rental rentalDtoToRental(RentalDto rentalDto);

    @Mapping(source = "customerId", target = "customerId", qualifiedByName = "mapCustomer")
    @Mapping(source = "inventoryId", target = "filmId", qualifiedByName = "mapFilm")
    RentalDto rentalToRentalDto(Rental rental);

    @Named("mapCustomer")
    default CustomerDto mapCustomer(Customer customer) {
        return CustomerMapper.INSTANCE.customertoCustomerDto(customer);
    }

    @Named("mapFilm")
    default FilmDto mapFilm(Inventory inventory) {
        return FilmMapper.INSTANCE.filmToFilmDto(inventory.getFilmId());
    }
}