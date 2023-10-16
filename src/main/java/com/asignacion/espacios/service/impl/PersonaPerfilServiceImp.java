package com.asignacion.espacios.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asignacion.espacios.model.entity.PersonaPerfil;
import com.asignacion.espacios.repository.PersonaPerfilRepository;
import com.asignacion.espacios.service.IPersonaPerfilService;

@Service
public class PersonaPerfilServiceImp implements IPersonaPerfilService {

	@Autowired
	PersonaPerfilRepository repoPersoPerfil;

	//Nativo
	public void guardarLista(List<PersonaPerfil> listaPersonaPerfil) {
		repoPersoPerfil.saveAll(listaPersonaPerfil);
	}
	
	
	//Repository
	public List<PersonaPerfil> listarPerfilHabilitadoPorIdPerfil(boolean indHabilitado, int idPerfil) {
		return repoPersoPerfil.findByIndHabilitadoAndPerfil_IdPerfil(indHabilitado, idPerfil);
	}

	
	public List<PersonaPerfil> listaPerfilesPersonaPorIdPersona(int idPersona) {
		return repoPersoPerfil.listaPerfilesPersonaPorIdPersona(idPersona);
	}

	

	
}
