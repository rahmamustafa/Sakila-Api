package gov.iti.sakila.presistence.repositories.repositoriesimpl;

import gov.iti.sakila.presistence.entities.Language;
import gov.iti.sakila.presistence.repositories.LanguageRepository;

public class LanguageRepositoryImpl extends GenericRepositoryImpl<Language,Short > implements LanguageRepository {

//    static LanguageRepository instance = new LanguageRepository();
public LanguageRepositoryImpl(){
    super(Language.class);
}

    
}
