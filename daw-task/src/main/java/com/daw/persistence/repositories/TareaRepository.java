package com.daw.persistence.repositories;

import java.awt.List;

import org.springframework.data.repository.ListCrudRepository;

import com.daw.persistence.entities.Tarea;

public interface TareaRepository extends ListCrudRepository<Tarea, Integer>{
	

}