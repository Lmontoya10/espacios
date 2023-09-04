package com.asignacion.espacios.entity;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;


@Entity
public class PersonaEstudiante {

	@Id
	private Integer idPersona;
	@OneToOne @JoinColumn(name="idPersona")
	private Persona persona;
	
	@ManyToOne @JoinColumn(name="idNivel")
	private MaeGrupoLista maeGrupoListaNivel;

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

	public MaeGrupoLista getMaeGrupoListaNivel() {
		return maeGrupoListaNivel;
	}

	public void setMaeGrupoListaNivel(MaeGrupoLista maeGrupoListaNivel) {
		this.maeGrupoListaNivel = maeGrupoListaNivel;
	}
	
	public String toString() {
		return "PersonaEstudiante [idPersona=" + idPersona + ", persona=" + persona + ", maeGrupoListaNivel="
				+ maeGrupoListaNivel + "]";
	}
	
	
	
	
	
}
