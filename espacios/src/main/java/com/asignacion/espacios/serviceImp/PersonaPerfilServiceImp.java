package com.asignacion.espacios.serviceImp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asignacion.espacios.entity.PersonaPerfil;
import com.asignacion.espacios.repository.PersonaPerfilRepository;
import com.asignacion.espacios.service.IPersonaPerfilService;

@Service
public class PersonaPerfilServiceImp implements IPersonaPerfilService {

	@Autowired
	PersonaPerfilRepository repoPersoPerfil;

	public List<PersonaPerfil> listarPerfilHabilitadoPorIdPerfil(boolean indHabilitado, int idPerfil) {
		return repoPersoPerfil.findByIndHabilitadoAndPerfil_IdPerfil(indHabilitado, idPerfil);
	}

	
}
