package com.asignacion.espacios.model.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Ambiente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idAmbiente;
	
	private String descripcion;
	private Date fechaCreacion;
	private boolean indHabilitado;
	
	public Ambiente() {
		super();
		fechaCreacion = new Date();
	}

	public Ambiente(String descripcion, Date fechaCreacion, boolean indHabilitado) {
		super();
		this.descripcion = descripcion;
		this.fechaCreacion = fechaCreacion;
		this.indHabilitado = indHabilitado;
	}

	public Integer getIdAmbiente() {
		return idAmbiente;
	}

	public void setIdAmbiente(Integer idAmbiente) {
		this.idAmbiente = idAmbiente;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public boolean isIndHabilitado() {
		return indHabilitado;
	}

	public void setIndHabilitado(boolean indHabilitado) {
		this.indHabilitado = indHabilitado;
	}

	@Override
	public String toString() {
		return "Ambiente [idAmbiente=" + idAmbiente + ", descripcion=" + descripcion + ", fechaCreacion="
				+ fechaCreacion + ", indHabilitado=" + indHabilitado + "]";
	}
	
	
}
