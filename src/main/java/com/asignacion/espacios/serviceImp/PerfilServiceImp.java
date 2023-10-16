package com.asignacion.espacios.serviceImp;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asignacion.espacios.model.entity.Perfil;
import com.asignacion.espacios.repository.PerfilRepository;
import com.asignacion.espacios.service.IPerfilService;

@Service
public class PerfilServiceImp implements IPerfilService {

	
	@Autowired
	PerfilRepository repoPerfil;
	
	public Perfil buscarPorId(int idPerfil) {
		Optional<Perfil> optional = repoPerfil.findById(idPerfil);
		if(optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

}
