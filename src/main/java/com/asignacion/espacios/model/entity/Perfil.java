package com.asignacion.espacios.model.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Perfil {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idPerfil; 
	
	    private String descripcion;
	    private boolean indHabilitado;
	    private Date fechaCreacion;
		public Integer getIdPerfil() {
			return idPerfil;
		}
		public void setIdPerfil(Integer idPerfil) {
			this.idPerfil = idPerfil;
		}
		public String getDescripcion() {
			return descripcion;
		}
		public void setDescripcion(String descripcion) {
			this.descripcion = descripcion;
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
		
		public String toString() {
			return "Perfil [idPerfil=" + idPerfil + ", descripcion=" + descripcion + ", indHabilitado=" + indHabilitado
					+ ", fechaCreacion=" + fechaCreacion + "]";
		}
	    
	    
	    
}
