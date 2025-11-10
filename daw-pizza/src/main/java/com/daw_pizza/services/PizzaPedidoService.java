package com.daw_pizza.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daw_pizza.persistence.entities.Pizza;
import com.daw_pizza.persistence.repositories.PizzaRepository;

@Service
public class PizzaPedidoService {
	@Autowired
	private PizzaRepository pizzaRepository;
	
	//findAll
	public List<Pizza> findAll(){
		return this.pizzaRepository.findAll();
	}
}
