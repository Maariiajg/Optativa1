package com.daw_pizza.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daw_pizza.persistence.entities.PizzaPedido;
import com.daw_pizza.persistence.repositories.PizzaPedidoRepository;
import com.daw_pizza.services.dto.PizzaPedidoOutputDTO;
import com.daw_pizza.services.exceptions.PizzaNotFoundException;
import com.daw_pizza.services.exceptions.PizzaPedidoNotFoundException;
import com.daw_pizza.services.mappers.PizzaPedidoMapper;

@Service
public class PizzaPedidoService {

	@Autowired
	private PizzaPedidoRepository pizzaPedidoRepository;
	
	public List<PizzaPedido> findAll(){
		return this.pizzaPedidoRepository.findAll();
	}
	
	public PizzaPedido findById(int idPizzaPedido) {
		if(!this.pizzaPedidoRepository.existsById(idPizzaPedido)) {
			throw new PizzaPedidoNotFoundException("El ID indicado no existe. ");
		}
		
		return this.pizzaPedidoRepository.findById(idPizzaPedido).get();
	}
	
	public PizzaPedido create(PizzaPedido pizzaPedido) {
		pizzaPedido.setId(0);
		
		return this.pizzaPedidoRepository.save(pizzaPedido);
	}
	
	public PizzaPedido update(int idPizzaPedido, PizzaPedido pizzaPedido) {
		if(idPizzaPedido != pizzaPedido.getId()) {
			throw new PizzaPedidoNotFoundException("El ID del path y del body no coinciden. ");
		}
		
		PizzaPedido pizzaPedidoBD = this.findById(idPizzaPedido);
		pizzaPedidoBD.setIdPedido(pizzaPedido.getIdPedido());
		pizzaPedidoBD.setIdPizza(pizzaPedido.getIdPizza());
		pizzaPedidoBD.setPrecio(pizzaPedido.getPrecio());
		pizzaPedidoBD.setCantidad(pizzaPedido.getCantidad());
		
		return this.pizzaPedidoRepository.save(pizzaPedidoBD);
	}
	
	public void deleteById(int idPizzaPedido) {
		if(!this.pizzaPedidoRepository.existsById(idPizzaPedido)) {
			throw new PizzaNotFoundException("El ID indicado no existe. ");
		}
		
		this.pizzaPedidoRepository.deleteById(idPizzaPedido);
	}
	
	//CRUDs Controller Pedido
	//findAll de PizzaPedido
	public List<PizzaPedidoOutputDTO> findByIdPedido(int idPedido){
		return PizzaPedidoMapper.toDtos(this.pizzaPedidoRepository.findByIdPedido(idPedido));
	}
	
	public PizzaPedidoOutputDTO findDTOById(int idPizzaPedido) {
		if(!this.pizzaPedidoRepository.existsById(idPizzaPedido)) {
			throw new PizzaPedidoNotFoundException("El ID indicado no existe. ");
		}
		
		return PizzaPedidoMapper.toDTO(this.pizzaPedidoRepository.findById(idPizzaPedido).get());
	}
	
	public PizzaPedidoOutputDTO createDTO(PizzaPedido pizzaPedido) {
		pizzaPedido.setId(0);
		
		return PizzaPedidoMapper.toDTO(this.pizzaPedidoRepository.save(pizzaPedido));
	}
	
	public PizzaPedidoOutputDTO updateDTO(int idPizzaPedido, PizzaPedido pizzaPedido) {
		if(idPizzaPedido != pizzaPedido.getId()) {
			throw new PizzaPedidoNotFoundException("El ID del path y del body no coinciden. ");
		}
		
		PizzaPedido pizzaPedidoBD = this.findById(idPizzaPedido);
		pizzaPedidoBD.setIdPedido(pizzaPedido.getIdPedido());
		pizzaPedidoBD.setIdPizza(pizzaPedido.getIdPizza());
		pizzaPedidoBD.setPrecio(pizzaPedido.getPrecio());
		pizzaPedidoBD.setCantidad(pizzaPedido.getCantidad());
		
		return PizzaPedidoMapper.toDTO(this.pizzaPedidoRepository.save(pizzaPedidoBD));
	}
	
}