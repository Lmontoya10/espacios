package com.asignacion.espacios.serviceImp;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asignacion.espacios.clases.Mensaje;
import com.asignacion.espacios.entity.PersonaDocente;
import com.asignacion.espacios.repository.PersonaDocenteRepository;
import com.asignacion.espacios.service.IPersonaDocenteService;

@Service
public class PersonaDocenteServiceImp implements IPersonaDocenteService {

	@Autowired
	PersonaDocenteRepository repoPersonaDocente;

	//NATIVO
	//Buscar por id
	public PersonaDocente buscarPorId(int idPersona) {
		Optional<PersonaDocente> optional = repoPersonaDocente.findById(idPersona);
		if(optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	//Guardar
	public void guardar(PersonaDocente personaDocente) {
		repoPersonaDocente.save(personaDocente);
	}

	public void eliminarPorId(int idPersona) {
		repoPersonaDocente.deleteById(idPersona);
	}

	
	//Guardar validando
	public Mensaje guardarEliminarValidando(PersonaDocente personaDocente, Boolean perfilDocente) {
		try {
			if(perfilDocente == false) {
				PersonaDocente personaDocenteExist = buscarPorId(personaDocente.getIdPersona());
				if (personaDocenteExist != null) {
					eliminarPorId(personaDocente.getIdPersona());
				}
			}else {
				guardar(personaDocente);
			}
			return Mensaje.retornarMensaje(1, "msgS", "Se gudardo o eliminó persona docente correctamente según el perfil");
		} catch (Exception e) {
			return Mensaje.retornarMensaje(0, "msgD", "Ocurrió un error al guardar los datos del perfil persona docente");
		}
	}
	
	
	
}
