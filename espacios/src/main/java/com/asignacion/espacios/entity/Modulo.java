package com.asignacion.espacios.entity;



import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Modulo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idModulo;
	
	private String codigo;
	private int cantidadPuestos;
	private int consecutivoInicial;
	private boolean indHabilitado;
	private int idAmbiente;
	private Date fechaCreacion;
	
	public Modulo() {
		super();
		fechaCreacion = new Date();
	}
	
	
	public Integer getIdModulo() {
		return idModulo;
	}
	public void setIdModulo(Integer idModulo) {
		this.idModulo = idModulo;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public int getCantidadPuestos() {
		return cantidadPuestos;
	}
	public void setCantidadPuestos(int cantidadPuestos) {
		this.cantidadPuestos = cantidadPuestos;
	}
	public int getConsecutivoInicial() {
		return consecutivoInicial;
	}
	public void setConsecutivoInicial(int consecutivoInicial) {
		this.consecutivoInicial = consecutivoInicial;
	}
	public boolean isIndHabilitado() {
		return indHabilitado;
	}
	public void setIndHabilitado(boolean indHabilitado) {
		this.indHabilitado = indHabilitado;
	}
	public int getIdAmbiente() {
		return idAmbiente;
	}
	public void setIdAmbiente(int idAmbiente) {
		this.idAmbiente = idAmbiente;
	}
	
	public Date getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	public String toString() {
		return "Modulo [idModulo=" + idModulo + ", codigo=" + codigo + ", cantidadPuestos=" + cantidadPuestos
				+ ", consecutivoInicial=" + consecutivoInicial + ", indHabilitado=" + indHabilitado + ", idAmbiente="
				+ idAmbiente + ", fechaCreacion=" + fechaCreacion + "]";
	}

	
	
	
	

}
