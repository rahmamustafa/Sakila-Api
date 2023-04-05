package gov.iti.sakila.presistence.repositories;

import java.util.Date;
import java.util.List;

import gov.iti.sakila.presistence.entities.Film;

public class FilmRepository extends GenericRepository<Film , Short> {

    public FilmRepository(){
        super(Film.class);
    }
    public List<Film> findByReleaseYear(Date releaseYear){
        return findListObjByNamedQuery("Film.findByReleaseYear", "releaseYear", releaseYear);

    }
    public List<Film> findByRating(String rating){
        return findListObjByNamedQuery("Film.findByRating", "rating", rating);
    }
    public List<Film> findByRentalDuration(String rentalDuration){
        return findListObjByNamedQuery("Film.findByRentalDuration", "rentalDuration", rentalDuration);

    }
    public List<Film> findByReplacementCost(String replacementCost){
        return findListObjByNamedQuery("Film.findByReplacementCost", "replacementCost", replacementCost);

    }
    public List<Film> findByLength(Short length){
        return findListObjByNamedQuery("Film.findByLength", "length", length);

    }
    

}
