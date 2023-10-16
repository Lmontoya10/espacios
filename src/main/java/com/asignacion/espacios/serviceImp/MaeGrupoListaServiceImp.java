package com.asignacion.espacios.serviceImp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asignacion.espacios.model.entity.MaeGrupoLista;
import com.asignacion.espacios.repository.MaeGrupoListaRepository;
import com.asignacion.espacios.service.IMaeGrupoListaService;


@Service
public class MaeGrupoListaServiceImp implements IMaeGrupoListaService {

	@Autowired
	MaeGrupoListaRepository repoMaeGrupoLista;
	
	
	//NATIVO=======================================================================================================================
	
	
	//REPOSITORY===================================================================================================================
	//Lista todas las opciones según el idGrupo y que la opción esté habilitada
	public List<MaeGrupoLista> listaOpcionesHabilitadasPorIdGrupoOrdenadosOrden(int idGrupo) {
		return repoMaeGrupoLista.findByMaegrupo_IdGrupoAndIndHabilitadoOrderByOrdenListaAsc(idGrupo, true);
	}

	
	
	//METODOS Y LOGICA=============================================================================================================
	
}
