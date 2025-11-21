package com.daw_pizza.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daw_pizza.persistence.entities.Pedido;
import com.daw_pizza.persistence.repositories.PedidoRepository;
import com.daw_pizza.services.dto.PedidoDTO;
import com.daw_pizza.services.exceptions.ClienteNotFoundException;
import com.daw_pizza.services.mappers.PedidoMapper;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository pedidoRepository;	
	
	public List<Pedido> findAll(){
		return this.pedidoRepository.findAll();
	}
	
	public PedidoDTO findById(int idPedido) {
		if(!this.pedidoRepository.existsById(idPedido)) {
			throw new ClienteNotFoundException("El ID indicado no existe. ");
		}
		
		return PedidoMapper.toDTO(this.pedidoRepository.findById(idPedido).get());
	}
	
	public Pedido create(Pedido pedido) {
		pedido.setId(0);
		
		return this.pedidoRepository.save(pedido);
	}
	
//		public Pedido update(int idPedido, Pedido pedido) {
//			Pedido pedidoBD = this.findById(idPedido);
//			pedidoBD.setIdCliente(pedido.getIdCliente());
//			pedidoBD.setFecha(pedido.getFecha());
//			pedidoBD.setTotal(pedido.getTotal());
//			pedidoBD.setMetodo(pedido.getMetodo());
//			pedidoBD.setNotas(pedido.getNotas());
//			
//			return this.pedidoRepository.save(pedido);
//		}
	
	public void deleteById(int idPedido) {
		if(!this.pedidoRepository.existsById(idPedido)) {
			throw new ClienteNotFoundException("El ID indicado no existe. ");
		}
		
		this.pedidoRepository.deleteById(idPedido);
	}

	//ejercicio 4
//	public Pedido añadirNotas(int idPedido, String notas) {
//	    Pedido pedido = this.findById(idPedido);
//	    pedido.setNotas(notas);
//	    return this.pedidoRepository.save(pedido);
//	}
//	
}