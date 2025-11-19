package com.daw_pizza.persistence.repositories;

import java.util.List;

import org.springframework.data.repository.ListCrudRepository;

import com.daw_pizza.persistence.entities.Pizza;

public interface PizzaRepository extends ListCrudRepository<Pizza, Integer>{
	// Pizzas disponibles ordenadas por precio
	List<Pizza> findByDisponibleTrueOrderByPrecioAsc();

	// Buscar pizzas por nombre (solo disponibles)
	List<Pizza> findByNombreContainingIgnoreCaseAndDisponibleTrue(String nombre);

	// Buscar pizzas que lleven un ingrediente (busca en la descripción)
	List<Pizza> findByDescripcionContainingIgnoreCase(String ingrediente);

	// Buscar pizzas que NO lleven un ingrediente
	List<Pizza> findByDescripcionNotContainingIgnoreCase(String ingrediente);

}
