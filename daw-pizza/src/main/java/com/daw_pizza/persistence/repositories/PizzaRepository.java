package com.daw_pizza.persistence.repositories;

import org.springframework.data.repository.ListCrudRepository;

import com.daw_pizza.persistence.entities.Pizza;

public interface PizzaRepository extends ListCrudRepository<Pizza, Integer>{

}
