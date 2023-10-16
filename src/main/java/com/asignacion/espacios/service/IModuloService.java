package com.asignacion.espacios.service;

import java.util.List;

import com.asignacion.espacios.clases.Mensaje;
import com.asignacion.espacios.model.entity.Modulo;


public interface IModuloService {
	
	//NATIVO=======================================================================================================================
	Modulo buscarModuloId (Integer idModulo);
	List<Modulo> listarTodos ();
	void guardarModulo (Modulo modulo);
	Modulo guardarModuloReturn (Modulo modulo);
	
	//REPOSITORY===================================================================================================================
	Modulo buscarPorCodigo (String codigo);
	
	//METODOS Y LOGICA=============================================================================================================
		Mensaje guardarValidando (Modulo modulo);

}
