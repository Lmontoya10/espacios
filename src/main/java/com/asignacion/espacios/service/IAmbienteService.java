package com.asignacion.espacios.service;

import java.util.List;

import com.asignacion.espacios.clases.Mensaje;
import com.asignacion.espacios.model.entity.Ambiente;


public interface IAmbienteService {
	
	// NATIVO=======================================================================================================================
	Ambiente buscarAmbienteId(Integer idAmbiente);
	List<Ambiente> listarTodos();
	void guardarAmbiente(Ambiente ambiente);

	// REPOSITORY===================================================================================================================
	
	// METODOS Y LOGICA=============================================================================================================
	Mensaje guardarValidando(Ambiente ambiente);


}
