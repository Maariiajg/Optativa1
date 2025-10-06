package com.daw.web.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.daw.persistence.entities.Tarea;
import com.daw.services.TareaServices;
import com.daw.services.exceptions.TareaNotFoundException;

@RestController
@RequestMapping("/tareas")
public class TareaController {
	
	@Autowired
	private TareaServices tareaServices;
	
	@GetMapping
	public List<Tarea> list(){
		return this.tareaServices.findAll();
		
	}
	
	@GetMapping("/{idTarea}")
	public ResponseEntity<?> findById(@PathVariable int idTarea){
		try {
			return ResponseEntity.ok(this.tareaServices.findById(idTarea));
		} catch(TareaNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage()); //para poner excepciones personalizadas y saber de donde viene el error
		}
	}
	
	
	@PostMapping
	public ResponseEntity<?> create(@RequestBody Tarea tarea){
		try {
			return
		}catch() {
			
		}
	}
	
}
