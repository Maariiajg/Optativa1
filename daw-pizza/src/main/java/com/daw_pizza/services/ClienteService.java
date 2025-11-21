package com.daw_pizza.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daw_pizza.persistence.entities.Cliente;
import com.daw_pizza.persistence.repositories.ClienteRepository;
import com.daw_pizza.services.exceptions.ClienteNotFoundException;
import com.daw_pizza.services.exceptions.PizzaNotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	public List<Cliente> findAll(){
		return this.clienteRepository.findAll();
	}
	
	public Cliente findById(int idCliente) {
		if(!this.clienteRepository.existsById(idCliente)) {
			throw new ClienteNotFoundException("El ID indicado no existe. ");
		}
		
		return this.clienteRepository.findById(idCliente).get();
	}
	
	public Cliente create(Cliente cliente) {
		cliente.setId(0);
		
		return this.clienteRepository.save(cliente);
	}
	
	public Cliente update(int idCliente, Cliente cliente) {
		Cliente clienteBD = this.findById(idCliente);
		clienteBD.setNombre(cliente.getNombre());
		clienteBD.setDireccion(cliente.getDireccion());
		clienteBD.setEmail(cliente.getEmail());
		clienteBD.setTelefono(cliente.getTelefono());		
		
		return this.clienteRepository.save(cliente);
	}
	
	public void deleteById(int idCliente) {
		if(!this.clienteRepository.existsById(idCliente)) {
			throw new PizzaNotFoundException("El ID indicado no existe. ");
		}
		
		this.clienteRepository.deleteById(idCliente);
	}
	
	//ejercicio 4
	public Cliente findByTelefono(String telefono) {
	    Cliente c = this.clienteRepository.findByTelefono(telefono);
	    if (c == null)
	        throw new ClienteNotFoundException("No existe cliente con ese teléfono");
	    return c;
	}

	// Modificar dirección
	public Cliente modificarDireccion(int idCliente, String nuevaDireccion) {
	    Cliente cliente = this.findById(idCliente);
	    cliente.setDireccion(nuevaDireccion);
	    return this.clienteRepository.save(cliente);
	}
	
}