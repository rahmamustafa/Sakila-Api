# Sakila-Api
Sakila is a sample database for MySQL that is commonly used for testing and learning purposes. It includes a fictional DVD rental store scenario with a database schema that contains various tables, such as customer, film, inventory, and payment, among others.

> Many to many relationships
> Multiple paths between entities (e.g. film-inventory-rental-payment vs film-inventory-store-customer-payment) to practice joins
> Primary keys are called [tablename]_[id]
> Foreign keys are called like their referenced primary key, if possible. This allows for using JOIN .. USING syntax where supported
> Relationship tables do not have any surrogate keys but use composite primary keys
