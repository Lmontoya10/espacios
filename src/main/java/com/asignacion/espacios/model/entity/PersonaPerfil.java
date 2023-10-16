package com.asignacion.espacios.model.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class PersonaPerfil {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idPersonaPerfil;
	
	@ManyToOne
	@JoinColumn(name ="idPersona")
	private Persona persona;
	
	@ManyToOne
	@JoinColumn(name = "idPerfil")
	private Perfil perfil;
	
	private boolean indHabilitado;

	public Integer getIdPersonaPerfil() {
		return idPersonaPerfil;
	}

	public void setIdPersonaPerfil(Integer idPersonaPerfil) {
		this.idPersonaPerfil = idPersonaPerfil;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

	public boolean isIndHabilitado() {
		return indHabilitado;
	}

	public void setIndHabilitado(boolean indHabilitado) {
		this.indHabilitado = indHabilitado;
	}

	public String toString() {
		return "PersonaPerfil [idPersonaPerfil=" + idPersonaPerfil + ", persona=" + persona + ", perfil=" + perfil
				+ ", indHabilitado=" + indHabilitado + "]";
	}
	
	
	
}
