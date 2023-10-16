package com.asignacion.espacios.model.entity;

import java.util.Date;

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
	@JoinColumn(name ="idListaNombre")
	private MaeGrupoLista maeGrupoListaNombre;
	
	@ManyToOne
	@JoinColumn(name ="idListaCae")
	private MaeGrupoLista maeGrupoListaCae;
	
	@ManyToOne
	@JoinColumn(name ="idListaDia")
	private MaeGrupoLista maeGrupoListaDia;
	
	@ManyToOne
	@JoinColumn(name ="idListaHorario")
	private MaeGrupoLista maeGrupoListaHorario;
	
	
	private boolean indHabilitado;
	private Date fechaCreacion;
	
	public Clinica() {
		super();
		indHabilitado = true;
		fechaCreacion = new Date();
	}
	
	
	public Integer getIdClinica() {
		return idClinica;
	}
	public void setIdClinica(Integer idClinica) {
		this.idClinica = idClinica;
	}
	public MaeGrupoLista getMaeGrupoListaNombre() {
		return maeGrupoListaNombre;
	}
	public void setMaeGrupoListaNombre(MaeGrupoLista maeGrupoListaNombre) {
		this.maeGrupoListaNombre = maeGrupoListaNombre;
	}
	public MaeGrupoLista getMaeGrupoListaCae() {
		return maeGrupoListaCae;
	}
	public void setMaeGrupoListaCae(MaeGrupoLista maeGrupoListaCae) {
		this.maeGrupoListaCae = maeGrupoListaCae;
	}
	public MaeGrupoLista getMaeGrupoListaDia() {
		return maeGrupoListaDia;
	}
	public void setMaeGrupoListaDia(MaeGrupoLista maeGrupoListaDia) {
		this.maeGrupoListaDia = maeGrupoListaDia;
	}
	public MaeGrupoLista getMaeGrupoListaHorario() {
		return maeGrupoListaHorario;
	}
	public void setMaeGrupoListaHorario(MaeGrupoLista maeGrupoListaHorario) {
		this.maeGrupoListaHorario = maeGrupoListaHorario;
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

	
	@Override
	public String toString() {
		return "Clinica [idClinica=" + idClinica + ", maeGrupoListaNombre=" + maeGrupoListaNombre
				+ ", maeGrupoListaCae=" + maeGrupoListaCae + ", maeGrupoListaDia=" + maeGrupoListaDia
				+ ", maeGrupoListaHorario=" + maeGrupoListaHorario + ", indHabilitado=" + indHabilitado
				+ ", fechaCreacion=" + fechaCreacion + "]";
	}
	
	

	

}
