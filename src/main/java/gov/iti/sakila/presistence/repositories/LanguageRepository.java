package gov.iti.sakila.presistence.repositories;

import gov.iti.sakila.presistence.entities.Category;
import gov.iti.sakila.presistence.entities.Film;
import gov.iti.sakila.presistence.entities.FilmCategory;
import gov.iti.sakila.presistence.entities.Language;

import java.util.List;

public class LanguageRepository extends GenericRepository<Language,Short > {

//    static LanguageRepository instance = new LanguageRepository();
    public LanguageRepository(){
        super(Language.class);
    }

    
}
