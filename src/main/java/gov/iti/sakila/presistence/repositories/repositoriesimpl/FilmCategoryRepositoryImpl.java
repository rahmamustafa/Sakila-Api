package gov.iti.sakila.presistence.repositories.repositoriesimpl;

import gov.iti.sakila.presistence.entities.FilmCategory;
import gov.iti.sakila.presistence.entities.FilmCategoryPK;
import gov.iti.sakila.presistence.repositories.FilmCategoryRepository;

public class FilmCategoryRepositoryImpl extends GenericRepositoryImpl<FilmCategory, FilmCategoryPK> implements FilmCategoryRepository {

    public FilmCategoryRepositoryImpl(){
        super(FilmCategory.class);
    }


}
