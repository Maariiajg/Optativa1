package com.registro.web.controllers;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.registro.persistences.entities.Registro;
import com.registro.sevices.RegistroService;
import com.registro.sevices.exceptions.RegistroException;
import com.registro.sevices.exceptions.RegistroNotFoundException;

@RestController
@RequestMapping("/registros")
public class RegistroController {
	@Autowired
	private RegistroService registroService;
	
	
	//Ejercicio 1
	@GetMapping //funciona
	public ResponseEntity<List<Registro>> findAll(){
		return ResponseEntity.ok(this.registroService.findAll());
	}
	
	
	//Ejercicio 2 Funciona
	@PutMapping("/{idRegistro}")
	public ResponseEntity<?> update(@PathVariable int idRegistro, @RequestBody Registro registro) {
		try {
			return ResponseEntity.ok(this.registroService.update(registro, idRegistro));
		} catch (RegistroNotFoundException ex) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
		} catch (RegistroException ex) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
		}
	}
	
	
	
	//Ejercicio 3. Funciona
	@PutMapping("/{idRegistro}/precipitacion")
	public ResponseEntity<?> updatePrecipitacion(
	        @PathVariable int idRegistro,
	        @RequestParam double anterior,
	        @RequestParam double nueva) {
	    try {
	        Registro actualizado = this.registroService.updatePrecipitacion(idRegistro, anterior, nueva);
	        return ResponseEntity.ok(actualizado);
	    } catch (RegistroNotFoundException ex) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
	    } catch (RegistroException ex) {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
	    }
	}

	
	
	
	//Ejercicio 4. Funciona
	@GetMapping("/buscar")
	public ResponseEntity<List<Registro>> findByUbicacionAndFechaLecturaBetween(
	    @RequestParam String ubicacion,
	    @RequestParam LocalDate fechaInicio,
	    @RequestParam LocalDate fechaFin) {
	    
	    return ResponseEntity.ok(
	        this.registroService.findByUbicacionAndFechaLecturaBetween(ubicacion, fechaInicio, fechaFin)
	    );
	}
	
	
}
