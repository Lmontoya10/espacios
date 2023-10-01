package com.asignacion.espacios.service;

import java.util.List;

import com.asignacion.espacios.clases.Mensaje;
import com.asignacion.espacios.entity.Clinica;

public interface IClinicaService {

	// NATIVO=======================================================================================================================
	Clinica buscarClincaId(Integer idClinica);
	List<Clinica> listarTodos();
	void guardarClinica(Clinica clinica);

	// REPOSITORY===================================================================================================================
	Clinica validarNoExisteSegunIds (int idListaNombre, int idListaCae, int idListaDia, int idListaHorario, boolean indHabilitado);

	// METODOS Y LOGICA=============================================================================================================
	Mensaje guardarValidando(Clinica clinica);

}
