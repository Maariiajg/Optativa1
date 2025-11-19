package com.daw_pizza.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daw_pizza.persistence.entities.Pizza;
import com.daw_pizza.persistence.repositories.PizzaRepository;
import com.daw_pizza.services.exceptions.PizzaNotFoundException;

@Service
public class PizzaService {
	
	@Autowired
	private PizzaRepository pizzaRepository;
	
	public List<Pizza> findAll(){
		return this.pizzaRepository.findAll();
	}
	
	public Pizza findById(int idPizza) {
		if(!this.pizzaRepository.existsById(idPizza)) {
			throw new PizzaNotFoundException("El ID indicado no existe. ");
		}
		
		return this.pizzaRepository.findById(idPizza).get();
	}
	
	public Pizza create(Pizza pizza) {
		pizza.setId(0);
		
		return this.pizzaRepository.save(pizza);
	}
	
	public Pizza update(int idPizza, Pizza pizza) {
		if(idPizza != pizza.getId()) {
			throw new PizzaNotFoundException("El ID del path y del body no coinciden. ");
		}
		
		Pizza pizzaBD = this.findById(idPizza);
		pizzaBD.setDescripcion(pizza.getDescripcion());
		pizzaBD.setDisponible(pizza.isDisponible());
		pizzaBD.setNombre(pizza.getNombre());
		pizzaBD.setPrecio(pizza.getPrecio());
		pizzaBD.setVegana(pizza.isVegana());
		pizzaBD.setVegetatiana(pizza.isVegetatiana());
		
		return this.pizzaRepository.save(pizza);
	}
	
	public void deleteById(int idPizza) {
		if(!this.pizzaRepository.existsById(idPizza)) {
			throw new PizzaNotFoundException("El ID indicado no existe. ");
		}
		
		this.pizzaRepository.deleteById(idPizza);
	}
	
	//actividad 4
	public List<Pizza> findDisponiblesOrdenadasPorPrecio() {
	    return this.pizzaRepository.findByDisponibleTrueOrderByPrecioAsc();
	}

	public List<Pizza> buscarPorNombre(String nombre) {
	    return this.pizzaRepository.findByNombreContainingIgnoreCaseAndDisponibleTrue(nombre);
	}

	public List<Pizza> buscarQueLlevenIngrediente(String ing) {
	    return this.pizzaRepository.findByDescripcionContainingIgnoreCase(ing);
	}

	public List<Pizza> buscarQueNoLlevenIngrediente(String ing) {
	    return this.pizzaRepository.findByDescripcionNotContainingIgnoreCase(ing);
	}

	// Actualizar PRECIO
	public Pizza actualizarPrecio(int idPizza, double nuevoPrecio) {
	    Pizza pizza = this.findById(idPizza);
	    pizza.setPrecio(nuevoPrecio);
	    return this.pizzaRepository.save(pizza);
	}

	// Marcar como disponible / no disponible
	public Pizza cambiarDisponibilidad(int idPizza, boolean disponible) {
	    Pizza pizza = this.findById(idPizza);
	    pizza.setDisponible(disponible);
	    return this.pizzaRepository.save(pizza);
	}


}