package gov.iti.sakila.presistence.repositories.repositoriesimpl;

import gov.iti.sakila.presistence.entities.FilmActor;
import gov.iti.sakila.presistence.entities.FilmActorPK;
import gov.iti.sakila.presistence.repositories.FilmActorRepository;

public class FilmActorRepositoryImpl extends GenericRepositoryImpl<FilmActor, FilmActorPK> implements FilmActorRepository {

    public FilmActorRepositoryImpl(){
        super(FilmActor.class);
    }


}
