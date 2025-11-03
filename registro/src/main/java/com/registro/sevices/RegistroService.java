package com.registro.sevices;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.registro.persistences.entities.Registro;
import com.registro.persistences.repositories.RegistroRepository;
import com.registro.sevices.exceptions.RegistroException;
import com.registro.sevices.exceptions.RegistroNotFoundException;

@Service
public class RegistroService {
	
	@Autowired
	private RegistroRepository registroRepository;
	
	// Ejercicio 1. Obtener todos funciona
	public List<Registro> findAll(){
		return this.registroRepository.findAll();
	}
	
	//Ejercicio 2. modificar un registro mediante su ID. Funciona
	public Registro update(Registro registro, int idRegistro) {
		
		if(!this.registroRepository.existsById(idRegistro)) {
			throw new RegistroNotFoundException("El ID del pokemon " + idRegistro + " no existe");
		}
	  	if (registro.getId() != idRegistro) {
			throw new RegistroException(
					String.format("El id del body (%d) y el id del path (%d) no coinciden", registro.getId(), idRegistro));
		}
	  	
	  	
        // No se permite cambiar fecha ni captura
	  	if (registro.getFechaLectura() != null || registro.getPrecipitacion() != 0.0) {
	  	    throw new RegistroException("No se puede modificar la fecha o la precipitacion");
	  	}
        
        Registro registroBD = this.findById(idRegistro);
        registroBD.setUbicacion(registro.getUbicacion());
        registroBD.setTemperatura(registro.getTemperatura());
        registroBD.setUnidad(registro.getUnidad());

        return this.registroRepository.save(registroBD);
    }
	
	//findById Hace falta para el Ejercicio 2
		public Registro findById(int idRegistro){
			if(!this.registroRepository.existsById(idRegistro)) {
				throw new RegistroNotFoundException("El ID del pokemon " + idRegistro + " no existe");
			}
			return this.registroRepository.findById(idRegistro).get();
		}
		
		
		
		
		
		//Ejercicio 3. modificar una precipitación asociada a un registro. Funciona
		public Registro updatePrecipitacion(int idRegistro, double anterior, double nueva) {
		    // Verificar que el ID existe
		    if (!this.registroRepository.existsById(idRegistro)) {
		        throw new RegistroNotFoundException("El ID del registro " + idRegistro + " no existe");
		    }

		    Registro registroBD = this.registroRepository.findById(idRegistro).get();

		    // Caso 1: las precipitaciones son idénticas
		    if (anterior == nueva) {
		        throw new RegistroException("La precipitación anterior y la nueva son idénticas");
		    }

		    // Caso 2: la precipitación anterior no coincide con la de base de datos
		    if (registroBD.getPrecipitacion() != anterior) {
		        throw new RegistroException("La precipitación anterior no coincide con la registrada en la base de datos");
		    }

		    // Actualizar valor
		    registroBD.setPrecipitacion(nueva);
		    return this.registroRepository.save(registroBD);
		}

		
		
		
		//Ejercicio 4. buscar los registros por ubicación y un rango de fechas. Funciona
		public List<Registro> findByUbicacionAndFechaLecturaBetween(String ubicacion, LocalDate fechaInicio, LocalDate fechaFin){
			return this.registroRepository.findByUbicacionAndFechaLecturaBetween(ubicacion, fechaInicio, fechaFin);
		}
		
}
