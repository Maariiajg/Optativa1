package com.daw_pizza.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.ListCrudRepository;

import com.daw_pizza.persistence.entities.PizzaPedido;

public interface PizzaPedidoRepository extends JpaRepository<PizzaPedido, Integer>{

}
