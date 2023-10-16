package com.asignacion.espacios.model.dto;

import java.io.Serializable;
import java.util.Date;

public class AmbienteDTO implements Serializable {

	private static final long serialVersionUID = -1726079802252266181L;
	private Integer idAmbiente;
	private String descripcion;
	private Date fechaCreacion;
	private boolean indHabilitado;

	public AmbienteDTO(String descripcion, Date fechaCreacion, boolean indHabilitado) {
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

}
