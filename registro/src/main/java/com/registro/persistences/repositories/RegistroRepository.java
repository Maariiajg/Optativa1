package com.registro.persistences.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

import com.registro.persistences.entities.Registro;



public interface RegistroRepository extends ListCrudRepository<Registro, Integer> {
	
	@Query("SELECT r FROM Registro r WHERE r.ubicacion = :ubicacion AND r.fechaLectura BETWEEN :fechaInicio AND :fechaFin")
	List<Registro> findByUbicacionAndFechaLecturaBetween(
	        @Param("ubicacion") String ubicacion,
	        @Param("fechaInicio") LocalDate fechaInicio,
	        @Param("fechaFin") LocalDate fechaFin);

}
