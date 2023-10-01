package com.asignacion.espacios.entity;



import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Modulo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idModulo;
	
	@ManyToOne
	@JoinColumn(name ="idOrientacion")
	private MaeGrupoLista maeGrupoListaOrientacion;
	
	@ManyToOne
	@JoinColumn(name ="idDistribucion")
	private MaeGrupoLista maeGrupoListaDistribucion;
	
	@ManyToOne
	@JoinColumn(name ="idAmbiente")
	private Ambiente ambiente;


	private String codigo;
	private int cantidadPuestos;
	private int consecutivoInicial;
	private boolean indHabilitado;
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

	public Date getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}


	public MaeGrupoLista getMaeGrupoListaOrientacion() {
		return maeGrupoListaOrientacion;
	}


	public void setMaeGrupoListaOrientacion(MaeGrupoLista maeGrupoListaOrientacion) {
		this.maeGrupoListaOrientacion = maeGrupoListaOrientacion;
	}


	public MaeGrupoLista getMaeGrupoListaDistribucion() {
		return maeGrupoListaDistribucion;
	}


	public void setMaeGrupoListaDistribucion(MaeGrupoLista maeGrupoListaDistribucion) {
		this.maeGrupoListaDistribucion = maeGrupoListaDistribucion;
	}


	public Ambiente getAmbiente() {
		return ambiente;
	}


	public void setAmbiente(Ambiente ambiente) {
		this.ambiente = ambiente;
	}


	@Override
	public String toString() {
		return "Modulo [idModulo=" + idModulo + ", maeGrupoListaOrientacion=" + maeGrupoListaOrientacion
				+ ", maeGrupoListaDistribucion=" + maeGrupoListaDistribucion + ", ambiente=" + ambiente + ", codigo="
				+ codigo + ", cantidadPuestos=" + cantidadPuestos + ", consecutivoInicial=" + consecutivoInicial
				+ ", indHabilitado=" + indHabilitado + ", fechaCreacion=" + fechaCreacion + "]";
	}

}
