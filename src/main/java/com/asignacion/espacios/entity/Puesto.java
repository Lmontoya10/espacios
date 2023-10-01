package com.asignacion.espacios.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Puesto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idPuesto;
	
	@ManyToOne
	@JoinColumn(name ="idModulo")
	private Modulo modulo;
	
	private String codigo;
	private boolean indHabilitado;
	private Date fechaCreacion;
	
	public Puesto() {
		super();
		fechaCreacion = new Date();
	}
	
	public Integer getIdPuesto() {
		return idPuesto;
	}
	public void setIdPuesto(Integer idPuesto) {
		this.idPuesto = idPuesto;
	}
	
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public boolean isIndHabilitado() {
		return indHabilitado;
	}
	public void setIndHabilitado(boolean indHabilitado) {
		this.indHabilitado = indHabilitado;
	}

	
	public Date getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	public Modulo getModulo() {
		return modulo;
	}
	public void setModulo(Modulo modulo) {
		this.modulo = modulo;
	}

	public String toString() {
		return "Puesto [idPuesto=" + idPuesto + ", modulo=" + modulo + ", codigo=" + codigo + ", indHabilitado="
				+ indHabilitado + ", fechaCreacion=" + fechaCreacion + "]";
	}
	
}
