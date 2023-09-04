package com.asignacion.espacios.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity
public class Clinica {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idClinica;
	
	@ManyToOne
	@JoinColumn(name ="idListaCae")
	private MaeGrupoLista maeGrupoListaCae;
	
	private String nombre;
	private boolean indHabilitado;
	private String fechaCreacion;
	
	
	public Integer getIdClinica() {
		return idClinica;
	}
	public void setIdClinica(Integer idClinica) {
		this.idClinica = idClinica;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public boolean isIndHabilitado() {
		return indHabilitado;
	}
	public void setIndHabilitado(boolean indHabilitado) {
		this.indHabilitado = indHabilitado;
	}
	
	public String getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(String fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	public MaeGrupoLista getMaeGrupoListaCae() {
		return maeGrupoListaCae;
	}
	public void setMaeGrupoListaCae(MaeGrupoLista maeGrupoListaCae) {
		this.maeGrupoListaCae = maeGrupoListaCae;
	}

	
	public String toString() {
		return "Clinica [idClinica=" + idClinica + ", maeGrupoListaCae=" + maeGrupoListaCae + ", nombre=" + nombre
				+ ", indHabilitado=" + indHabilitado + ", fechaCreacion=" + fechaCreacion + "]";
	}

	
	
	
	

	

}
