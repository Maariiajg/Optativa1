package com.daw_pizza.services.mappers;

import java.util.ArrayList;
import java.util.List;

import com.daw_pizza.persistence.entities.Pedido;
import com.daw_pizza.persistence.entities.PizzaPedido;
import com.daw_pizza.services.dto.PedidoDTO;
import com.daw_pizza.services.dto.PizzaPedidoOutputDTO;

public class PedidoMapper {
	public static PedidoDTO toDto(Pedido pedido) {
		PedidoDTO dto = new PedidoDTO();
		
		//transformo las entidades en dto
		dto.setId(pedido.getId());
		dto.setFecha(pedido.getFecha());
		dto.setTotal(pedido.getTotal());
		dto.setMetodo(pedido.getMetodo());
		dto.setNotas(pedido.getNotas());
		
		//viene del cliente asociado al pedido
		dto.setCliente(pedido.getCliente().getNombre());
		dto.setTelefono(pedido.getCliente().getTelefono());
		dto.setDireccion(pedido.getCliente().getDireccion());
		
		List<PizzaPedidoOutputDTO> pizzas = new ArrayList<PizzaPedidoOutputDTO>();
		for(PizzaPedido pp: pedido.getPizzaPedidos()) {
			PizzaPedidoOutputDTO ppDTO = PizzaPedidoMapper.toDTO(pp);
			
			pizzas.add(ppDTO);
		}
		
		dto.setPizzas(pizzas);
		return dto;
	}
}
