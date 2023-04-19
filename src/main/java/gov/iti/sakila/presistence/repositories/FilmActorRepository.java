package gov.iti.sakila.presistence.repositories;

import gov.iti.sakila.presistence.entities.Actor;
import gov.iti.sakila.presistence.entities.Film;
import gov.iti.sakila.presistence.entities.FilmActor;
import gov.iti.sakila.presistence.entities.FilmActorPK;

import java.util.List;

public interface FilmActorRepository extends GenericRepository<FilmActor, FilmActorPK> {



}
