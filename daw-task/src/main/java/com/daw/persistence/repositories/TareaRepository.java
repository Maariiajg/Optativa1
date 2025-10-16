package com.daw.persistence.repositories;

import java.util.List;

import org.springframework.data.repository.ListCrudRepository;

import com.daw.persistence.entities.Tarea;
import com.daw.persistence.entities.enums.Estado;
import java.time.LocalDate;


public interface TareaRepository extends ListCrudRepository<Tarea, Integer> {
	
	List<Tarea> findByEstado(Estado estado);
	
//	Obtener las tareas vencidas (fecha de vencimiento menor que la de hoy).
	List<Tarea> findByFechaVencimientoBefore(LocalDate fechaVencimiento);
//	Obtener las tareas no vencidas (fecha de vencimiento mayor que la de hoy).
	List<Tarea> findByFechaVencimientoGreaterThanEqual(LocalDate fechaVencimiento);
//	Obtener tareas mediante su título (que contenga el String que se pasa como título).
	List<Tarea> findByTitulo(String titulo);


}