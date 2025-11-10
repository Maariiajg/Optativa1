package com.daw_pizza.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daw_pizza.persistence.entities.Pedido;
import com.daw_pizza.persistence.repositories.PedidoRepository;

@Service
public class PedidoService {
	@Autowired
	private PedidoRepository pedidoRepository;
	
	//findAll
	public List<Pedido> findAll(){
		return this.pedidoRepository.findAll();
	}
}
