package gov.iti.sakila.presistence.repositories;

import gov.iti.sakila.presistence.entities.FilmActor;
import gov.iti.sakila.presistence.entities.FilmActorPK;
import gov.iti.sakila.presistence.entities.FilmCategory;
import gov.iti.sakila.presistence.entities.FilmCategoryPK;

public class FilmCategoryRepository extends GenericRepository<FilmCategory, FilmCategoryPK> {

    public FilmCategoryRepository(){
        super(FilmCategory.class);
    }


}
