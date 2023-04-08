package gov.iti.sakila.presistence.dbconnection;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public enum JpaManagerSingleton {
    
    INSTANCE;
    private EntityManagerFactory entityManagerFactory;


    public EntityManager getEntityManager(){
        return entityManagerFactory.createEntityManager();
    }

    private JpaManagerSingleton(){
        entityManagerFactory = Persistence.createEntityManagerFactory("sakilaPresis");
    }

}
