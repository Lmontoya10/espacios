package com.asignacion.espacios.service;

import java.util.List;

import com.asignacion.espacios.model.entity.MaeGrupoLista;

public interface IMaeGrupoListaService {

	//NATIVO=======================================================================================================================
	
	
	//REPOSITORY===================================================================================================================
	List<MaeGrupoLista> listaOpcionesHabilitadasPorIdGrupoOrdenadosOrden (int idGrupo);
	
	
	//METODOS Y LOGICA=============================================================================================================
	
}
