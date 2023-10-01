package com.asignacion.espacios.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class MaeGrupo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idGrupo;
	private String descripcion;
	private boolean indHabilitado;
	private boolean indEditable;
	
	public Integer getIdGrupo() {
		return idGrupo;
	}
	public void setIdGrupo(Integer idGrupo) {
		this.idGrupo = idGrupo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public boolean getIndHabilitado() {
		return indHabilitado;
	}
	public void setIndHabilitado(boolean indHabilitado) {
		this.indHabilitado = indHabilitado;
	}
	
	public boolean isIndEditable() {
		return indEditable;
	}
	public void setIndEditable(boolean indEditable) {
		this.indEditable = indEditable;
	}
	
	public String toString() {
		return "MaeGrupo [idGrupo=" + idGrupo + ", descripcion=" + descripcion + ", indHabilitado=" + indHabilitado
				+ ", indEditable=" + indEditable + "]";
	}
	
}
