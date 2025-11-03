package com.registro.persistences.entities;

import java.time.LocalDate;

import com.registro.persistences.entities.enums.Unidad;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Registro")
@Getter
@Setter
public class Registro {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "fecha_lectura")
	private LocalDate fechaLectura;
	
	private String ubicacion;
	
	private double temperatura;
	
	@Enumerated(value = EnumType.STRING)
	private Unidad unidad;
	
	private double precipitacion;

}
