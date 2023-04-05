package gov.iti.sakila.presistence.repositories;

import gov.iti.sakila.presistence.entities.Category;

public class CategoryRepository extends GenericRepository<Category,Short > {

    public CategoryRepository(){
        super(Category.class);
    }

}
