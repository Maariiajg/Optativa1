package com.daw.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daw.persistence.entities.Tarea;
import com.daw.persistence.entities.enums.Estado;
import com.daw.persistence.repositories.TareaRepository;
import com.daw.services.exceptions.TareaException;
import com.daw.services.exceptions.TareaNotFoundException;

@Service
public class TareaServices {

    private final TareaRepository tareaRepository;

    @Autowired
    public TareaServices(TareaRepository tareaRepository) {
        this.tareaRepository = tareaRepository;
    }

    public List<Tarea> findAll() {
        return this.tareaRepository.findAll();
    }

    public Tarea findById(int idTarea) {
        if(!this.tareaRepository.existsById(idTarea)) {
        	throw new TareaNotFoundException("El ID no existe");
        }
        return this.tareaRepository.findById(idTarea).get(); 
    }

    // create
    public Tarea create(Tarea tarea) {
    	if(tarea.getFechaVencimiento().isBefore(LocalDate.now())) {
    		throw new TareaException("La fecha de vencimiento debe ser posterior.");
    	}
    	
    	tarea.setId(0);
        tarea.setFechaCreacion(LocalDate.now());
        tarea.setEstado(Estado.PENDIENTE);
        
       return this.tareaRepository.save(tarea);
    }

    // update
    public Tarea update(int idTarea, Tarea tarea) {
        if (idTarea != tarea.getId()) {
            throw new TareaException("El ID del path (" + idTarea + ") y el id del body (" + tarea.getId() + ") no coinciden");
        }

        Tarea tareaBD = this.tareaRepository.findById(idTarea)
                .orElseThrow(() -> new TareaNotFoundException("No existe la tarea con ID: " + idTarea));

        if (tarea.getFechaCreacion() != null) {
            throw new TareaException("No se permite modificar la fecha de creación.");
        }
        if (tarea.getEstado() != null) {
            throw new TareaException("No se permite modificar el estado directamente.");
        }

        tareaBD.setTitulo(tarea.getTitulo());
        tareaBD.setDescripcion(tarea.getDescripcion());
        tareaBD.setFechaVencimiento(tarea.getFechaVencimiento());

        return this.tareaRepository.save(tareaBD);
    }
}
