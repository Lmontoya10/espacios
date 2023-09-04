package com.asignacion.espacios.entity;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class MaeGrupoLista {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idLista;
	
	@ManyToOne
	@JoinColumn(name = "idGrupo")
	private MaeGrupo maegrupo;
	
	private String nombreLista;
	private String codigoLista;
	private Integer ordenLista;
	private Boolean indHabilitado;
	private Date fechaCreacion;
	
	
	public Integer getIdLista() {
		return idLista;
	}
	public void setIdLista(Integer idLista) {
		this.idLista = idLista;
	}
	public MaeGrupo getMaegrupo() {
		return maegrupo;
	}
	public void setMaegrupo(MaeGrupo maegrupo) {
		this.maegrupo = maegrupo;
	}
	public String getNombreLista() {
		return nombreLista;
	}
	public void setNombreLista(String nombreLista) {
		this.nombreLista = nombreLista;
	}
	public String getCodigoLista() {
		return codigoLista;
	}
	public void setCodigoLista(String codigoLista) {
		this.codigoLista = codigoLista;
	}
	public Integer getOrdenLista() {
		return ordenLista;
	}
	public void setOrdenLista(Integer ordenLista) {
		this.ordenLista = ordenLista;
	}
	
	public Date getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	public Boolean getIndHabilitado() {
		return indHabilitado;
	}
	public void setIndHabilitado(Boolean indHabilitado) {
		this.indHabilitado = indHabilitado;
	}
	public String toString() {
		return "MaeGrupoLista [idLista=" + idLista + ", maegrupo=" + maegrupo + ", nombreLista=" + nombreLista
				+ ", codigoLista=" + codigoLista + ", ordenLista=" + ordenLista + ", indHabilitado=" + indHabilitado
				+ ", fechaCreacion=" + fechaCreacion + "]";
	}

	

}
