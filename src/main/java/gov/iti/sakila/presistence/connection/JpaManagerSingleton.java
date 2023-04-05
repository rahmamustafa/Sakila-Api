package gov.iti.sakila.presistence.connection;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public enum JpaManagerSingleton {
    
    INSTANCE;
    
    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

    public EntityManager getEntityManager(){
        return entityManager;
    }

    JpaManagerSingleton(){
        entityManagerFactory = Persistence.createEntityManagerFactory("sakilaPresis");
        entityManager = entityManagerFactory.createEntityManager();
    }

}
