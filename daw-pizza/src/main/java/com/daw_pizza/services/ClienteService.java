package com.daw_pizza.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daw_pizza.persistence.entities.Cliente;
import com.daw_pizza.persistence.repositories.ClienteRepository;

@Service
public class ClienteService {
	@Autowired
	private ClienteRepository clienteRepository;
	
	//findAll
	public List<Cliente> findAll(){
		return this.clienteRepository.findAll();
	}
}
