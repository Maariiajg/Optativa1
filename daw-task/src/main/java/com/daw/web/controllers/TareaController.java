package com.daw.web.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.daw.persistence.entities.Tarea;
import com.daw.services.TareaServices;

@RestController
@RequestMapping("/Tarea")
public class TareaController {
	
	@Autowired
	private TareaServices tareaServices;
	
	public List<Tarea> list(){
		return this.tareaServices.findAll();
		
	}
	
	public Tarea findById(int idTarea) {
		return this.tareaServices.findById(idTarea);
	}
}
