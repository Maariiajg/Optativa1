package com.daw_pizza.web.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.daw_pizza.persistence.entities.PizzaPedido;
import com.daw_pizza.persistence.repositories.PizzaPedidoRepository;

@RestController
@RequestMapping("/pizzapedidos")
public class PizzaPedidoController {
	@Autowired
    private PizzaPedidoRepository pizzaPedidoRepository;
    
    public List<PizzaPedido> findAll() {
        return pizzaPedidoRepository.findAll();
    }
}
