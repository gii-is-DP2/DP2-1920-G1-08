package org.springframework.inmocasa.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.URL;
import org.springframework.format.annotation.DateTimeFormat;


@Entity
public class Vivienda extends BaseEntity{

	@NotNull
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private LocalDate fechaPublicacion;
	
	@NotBlank
	private String direccion;
	
	@NotBlank
	private String zona;
	
	@NotNull
	private Integer precio;
		
	private Integer dimensiones;
	
	private Boolean amueblado;
	
	private String planta;

	@URL
	private String foto;
	
	private String caracteristicas;
	
	private String equipamiento;
	
	private Boolean denunciado;

	public LocalDate getFechaPublicacion() {
		return fechaPublicacion;
	}

	public void setFechaPublicacion(LocalDate fechaPublicacion) {
		this.fechaPublicacion = fechaPublicacion;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getZona() {
		return zona;
	}

	public void setZona(String zona) {
		this.zona = zona;
	}

	public Integer getPrecio() {
		return precio;
	}

	public void setPrecio(Integer precio) {
		this.precio = precio;
	}

	public Integer getDimensiones() {
		return dimensiones;
	}

	public void setDimensiones(Integer dimensiones) {
		this.dimensiones = dimensiones;
	}

	public Boolean getAmueblado() {
		return amueblado;
	}

	public void setAmueblado(Boolean amueblado) {
		this.amueblado = amueblado;
	}

	public String getPlanta() {
		return planta;
	}

	public void setPlanta(String planta) {
		this.planta = planta;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public String getCaracteristicas() {
		return caracteristicas;
	}

	public void setCaracteristicas(String caracteristicas) {
		this.caracteristicas = caracteristicas;
	}

	public String getEquipamiento() {
		return equipamiento;
	}

	public void setEquipamiento(String equipamiento) {
		this.equipamiento = equipamiento;
	}

	public Boolean getDenunciado() {
		return denunciado;
	}

	public void setDenunciado(Boolean denunciado) {
		this.denunciado = denunciado;
	}
}
