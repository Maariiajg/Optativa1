package com.daw_pizza.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daw_pizza.persistence.entities.Pizza;
import com.daw_pizza.persistence.repositories.PizzaRepository;
import com.daw_pizza.services.exceptions.PizzaException;
import com.daw_pizza.services.exceptions.PizzaNotFoundException;

@Service
public class PizzaService {

	@Autowired
	private PizzaRepository pizzaRepository;

	// findAll
	public List<Pizza> findAll() {
		return this.pizzaRepository.findAll();
	}

	// findById
	public Pizza findById(int idPizza) {
		if (!this.pizzaRepository.existsById(idPizza)) {
			throw new PizzaNotFoundException("La pizza con id " + idPizza + " no existe.");
		}
		return this.pizzaRepository.findById(idPizza).get();
	}

	// create
	public Pizza create(Pizza pizza) {
		pizza.setId(0);
		return this.pizzaRepository.save(pizza);
	}

	// update
	public Pizza update(Pizza pizza, int idPizza) {
		if (pizza.getId() != idPizza) {
			throw new PizzaException(String.format("El id del body (%d) y el id del path (%d) no coinciden", pizza.getId(), idPizza));
		}
		if (!this.pizzaRepository.existsById(idPizza)) {
			throw new PizzaNotFoundException("La pizza con id " + idPizza + " no existe.");
		}

		Pizza pizzaBD = this.findById(idPizza);
		pizzaBD.setNombre(pizza.getNombre());
		pizzaBD.setDescripcion(pizza.getDescripcion());
		pizzaBD.setPrecio(pizza.getPrecio());
		pizzaBD.setDisponible(pizza.isDisponible());
		pizzaBD.setVegana(pizza.isVegana());
		pizzaBD.setVegetariana(pizza.isVegetariana());

		return this.pizzaRepository.save(pizzaBD);
	}

	// delete
	public void delete(int idPizza) {
		if (!this.pizzaRepository.existsById(idPizza)) {
			throw new PizzaNotFoundException("La pizza con id " + idPizza + " no existe.");
		}
		this.pizzaRepository.deleteById(idPizza);
	}
}
