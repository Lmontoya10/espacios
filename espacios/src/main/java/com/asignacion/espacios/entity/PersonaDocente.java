package com.asignacion.espacios.entity;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;


@Entity
public class PersonaDocente {

	@Id
	private Integer idPersona;
	@OneToOne @JoinColumn(name="idPersona")
	private Persona persona;
	
	@ManyToOne @JoinColumn(name="idEspecialidad")
	private MaeGrupoLista maeGrupoListaEspecialidad;

	public Integer getIdPersona() {
		return idPersona;
	}

	public void setIdPersona(Integer idPersona) {
		this.idPersona = idPersona;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public MaeGrupoLista getMaeGrupoListaEspecialidad() {
		return maeGrupoListaEspecialidad;
	}

	public void setMaeGrupoListaEspecialidad(MaeGrupoLista maeGrupoListaEspecialidad) {
		this.maeGrupoListaEspecialidad = maeGrupoListaEspecialidad;
	}

	public String toString() {
		return "PersonaDocente [idPersona=" + idPersona + ", persona=" + persona + ", maeGrupoListaEspecialidad="
				+ maeGrupoListaEspecialidad + "]";
	}
	
	
}
