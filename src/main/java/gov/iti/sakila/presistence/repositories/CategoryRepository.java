package gov.iti.sakila.presistence.repositories;

import java.sql.Date;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

import gov.iti.sakila.presistence.entities.Category;
import gov.iti.sakila.presistence.entities.Film;
import gov.iti.sakila.presistence.entities.FilmActor;
import gov.iti.sakila.presistence.entities.FilmCategory;
import jakarta.ws.rs.NotFoundException;

public interface CategoryRepository extends GenericRepository<Category,Short > {

    public Category findByName(String name);
    public int addFilmToCategory(Short filmId,Short categoryId);
    
}
