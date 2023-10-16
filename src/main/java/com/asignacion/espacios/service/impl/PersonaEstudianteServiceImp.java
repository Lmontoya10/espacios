package com.asignacion.espacios.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asignacion.espacios.clases.Mensaje;
import com.asignacion.espacios.model.entity.PersonaEstudiante;
import com.asignacion.espacios.repository.PersonaEstudianteRepository;
import com.asignacion.espacios.service.IPersonaEstudianteService;

@Service
public class PersonaEstudianteServiceImp implements IPersonaEstudianteService {


	@Autowired
	PersonaEstudianteRepository repoPersonaEstudiante;

	//NATIVO
	//Guardar
	public void guardar(PersonaEstudiante personaEstudiante) {
		repoPersonaEstudiante.save(personaEstudiante);
	}

	//Buscar por id
	public PersonaEstudiante buscarPorId(int idPersona) {
		Optional<PersonaEstudiante> optional = repoPersonaEstudiante.findById(idPersona);
		if(optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	//Eliminar por id
	public void eliminarPorId(int idPersona) {
		repoPersonaEstudiante.deleteById(idPersona);
	}

	//REPOSITORY
	
	
	//METODOS Y LÓGICA
	//Guarda o elimina validando según el perfilEstudiante seleccionado
	public Mensaje guardarEliminarValidando(PersonaEstudiante personaEstudiante, Boolean perfilEstudiante) {
		try {
			if(perfilEstudiante == false) {
				PersonaEstudiante personaEstudianteExist = buscarPorId(personaEstudiante.getIdPersona());
				if(personaEstudianteExist != null) {
					eliminarPorId(personaEstudiante.getIdPersona());
				}
			}else {
				guardar(personaEstudiante);
			}
			return Mensaje.retornarMensaje(1, "msgS", "Se gudardo o eliminó correctamente según el perfil");
		} catch (Exception e) {
			return Mensaje.retornarMensaje(0, "msgD", "Ocurrió un error al guardar los datos del perfil persona estudiante");
		}
	}
}
