package com.asignacion.espacios.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asignacion.espacios.clases.Mensaje;
import com.asignacion.espacios.model.entity.Clinica;
import com.asignacion.espacios.repository.ClinicaRepository;
import com.asignacion.espacios.service.IClinicaService;

@Service
public class ClinicaServiceImp implements IClinicaService {

	@Autowired
	ClinicaRepository repoClinica;

		
	
	//NATIVO=======================================================================================================================
	//buscar clinica por id
	public Clinica buscarClincaId(Integer idClinica) {
		Optional<Clinica> optional = repoClinica.findById(idClinica);
		if (optional.isPresent()) {
			return optional.get();
		}
		return null;
	}
	

	public List<Clinica> listarTodos() {
		return repoClinica.findAll();
	}


	//guardar o actualizar clinica
	public void guardarClinica(Clinica clinica) {
		repoClinica.save(clinica);
	}
	

	//REPOSITORY===================================================================================================================
	//Valida segun los ids que se envían que el registro no exista para no duplicar informaicón
	public Clinica validarNoExisteSegunIds(int idListaNombre, int idListaCae, int idListaDia, int idListaHorario, boolean indHabilitado) {
		// TODO Auto-generated method stub
		return repoClinica.findByMaeGrupoListaNombre_IdListaAndMaeGrupoListaCae_IdListaAndMaeGrupoListaDia_IdListaAndMaeGrupoListaHorario_IdListaAndIndHabilitado
				(idListaNombre, idListaCae, idListaDia, idListaHorario, indHabilitado);
	}

	
	//METODOS Y LOGICA=============================================================================================================
	
	//Guardar validando
	public Mensaje guardarValidando(Clinica clinica) {
		try {
			int idListaNombre = clinica.getMaeGrupoListaNombre().getIdLista();
			int idListaCae = clinica.getMaeGrupoListaCae().getIdLista();
			int idListaDia = clinica.getMaeGrupoListaDia().getIdLista();
			int idListaHorario = clinica.getMaeGrupoListaHorario().getIdLista();
			boolean indHabilitado = clinica.isIndHabilitado();
			Clinica clinicaExit = validarNoExisteSegunIds(idListaNombre, idListaCae, idListaDia, idListaHorario, indHabilitado);
			if (clinicaExit != null) {
				return Mensaje.retornarMensaje(0, "msgW", "La configuración de la clínica ya existe o no realizó ningún cambio");
			}
			guardarClinica(clinica);
		} catch (Exception e) {
			return Mensaje.retornarMensaje(0, "msgD", "Error al guardar los datos. Error " + e);
		}
		return Mensaje.retornarMensaje(1, "msgS", "Datos guardados correctamente");
	}


	
	
}
