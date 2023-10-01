package com.asignacion.espacios.clases;

public class Mensaje {

	private Integer codigoMensaje; //0: Error, 1: Ok
	private String tipoAlerta; //msgS, msgD, msgW
	private String descripcionMensaje;
	
	
	public Integer getCodigoMensaje() {
		return codigoMensaje;
	}
	public void setCodigoMensaje(Integer codigoMensaje) {
		this.codigoMensaje = codigoMensaje;
	}
	public String getTipoAlerta() {
		return tipoAlerta;
	}
	public void setTipoAlerta(String tipoAlerta) {
		this.tipoAlerta = tipoAlerta;
	}
	public String getDescripcionMensaje() {
		return descripcionMensaje;
	}
	public void setDescripcionMensaje(String descripcionMensaje) {
		this.descripcionMensaje = descripcionMensaje;
	}
	public String toString() {
		return "Mensaje [codigoMensaje=" + codigoMensaje + ", tipoAlerta=" + tipoAlerta + ", descripcionMensaje="
				+ descripcionMensaje + "]";
	}
	
	public static Mensaje retornarMensaje(int codigoMensaje, String tipoAlerta, String descripcionMensaje) {
		Mensaje mensaje = new Mensaje();
		mensaje.setCodigoMensaje(codigoMensaje);
		mensaje.setTipoAlerta(tipoAlerta);
		mensaje.setDescripcionMensaje(descripcionMensaje);
		return mensaje;
	}
	
	
	
}
