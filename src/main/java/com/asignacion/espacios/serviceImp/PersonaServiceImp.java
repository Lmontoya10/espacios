package com.asignacion.espacios.serviceImp;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.asignacion.espacios.clases.Mensaje;
import com.asignacion.espacios.model.entity.Persona;
import com.asignacion.espacios.model.entity.PersonaDocente;
import com.asignacion.espacios.model.entity.PersonaEstudiante;
import com.asignacion.espacios.model.entity.PersonaPerfil;
import com.asignacion.espacios.repository.PersonaRepository;
import com.asignacion.espacios.service.IPersonaDocenteService;
import com.asignacion.espacios.service.IPersonaEstudianteService;
import com.asignacion.espacios.service.IPersonaPerfilService;
import com.asignacion.espacios.service.IPersonaService;

@Service
public class PersonaServiceImp implements IPersonaService {

	@Autowired
	PersonaRepository repoPersona;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	IPersonaEstudianteService servicePersonaEstudiante;
	
	@Autowired 
	IPersonaDocenteService servicePersonaDocente;
	
	@Autowired
	IPersonaPerfilService servicePersonaPerfil;
	
	//NATIVO=======================================================================================================================
	//buscar persona por id
	public Persona buscarPersonaId(Integer idPersona) {
		Optional<Persona> optional = repoPersona.findById(idPersona);
		if (optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	//listado de todas las personas
	public List<Persona> listarTodos() {
		return repoPersona.findAll();
	}

	//guardar o actualizar persona
	public void guardarPersona(Persona persona) {
		repoPersona.save(persona);
	}

	//guardar persona retornando datos
	public Persona guardarPersonaReturn(Persona persona) {
		persona = repoPersona.save(persona);
		return persona;
	}
	
	//REPOSITORY===================================================================================================================
	public Persona buscarPorDocumento(String documento) {
		Optional<Persona> optional = Optional.ofNullable(repoPersona.findByIdentificacion(documento));
		if(optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	public Persona buscarPorEmail(String email) {
		Optional<Persona> optional = Optional.ofNullable(repoPersona.findByEmail(email));
		if (optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	
	//METODOS Y LOGICA=============================================================================================================
	//Guardo validando el documento y el correo no estén repetidos
	public Mensaje guardarValidando(Persona persona, Boolean perfilEstudiante, Boolean perfilDocente, Boolean perfilAuxiliar, Boolean perfilAdministrador) {
		Mensaje mensaje = new Mensaje();
		//Asignos los valores recibidos a los objetos de manera independiente "persona" "personaEstudiante" "PersonaDocente"
		Persona personaRec = personaMapeoToPersona(persona);
		PersonaEstudiante personaEstudiante = persona.getPersonaEstudiante();
		PersonaDocente personaDocente = persona.getPersonaDocente();
		try {
			//GUARDO LA PERSONA
			if(personaRec.getIdPersona()== null) {
				//Registro nuevo
				//valido que el número de documento no exista
				Persona personaExist = buscarPorDocumento(personaRec.getIdentificacion());
				if(personaExist!=null) {
					return Mensaje.retornarMensaje(0, "msgD", "El documento ya está registrado");
				}
				//valido que el correo no exista
				personaExist = buscarPorEmail(personaRec.getEmail());
				if(personaExist!=null) {
					return Mensaje.retornarMensaje(0, "msgD", "El Email ya está registrado");
				}
				//Guardo la persona retornando el objeto persona recien guardado
				personaRec.setContrasena(passwordEncoder.encode(personaRec.getIdentificacion()));
				personaRec = guardarPersonaReturn(personaRec);
				//Agrego el idPersona a los objetos que se relacionan "personaEstudiante" y "personaDocente"
				personaEstudiante.setIdPersona(personaRec.getIdPersona());
				personaDocente.setIdPersona(personaRec.getIdPersona());
			}else {
				//Actualizacion registro
				Persona personaExist = buscarPersonaId(personaRec.getIdPersona());
				personaRec.setContrasena(personaExist.getContrasena());
				personaRec.setFechaCreacion(personaExist.getFechaCreacion());
				personaRec = guardarPersonaReturn(personaRec);
			}
			
			//GUARDO O ELIMINO EL "personaEstudiante" Según el perfilEstudiante recibido "true o false"
			mensaje = servicePersonaEstudiante.guardarEliminarValidando(personaEstudiante, perfilEstudiante);
			if(mensaje.getCodigoMensaje()==0) {
				return Mensaje.retornarMensaje(mensaje.getCodigoMensaje(), mensaje.getTipoAlerta(), mensaje.getDescripcionMensaje());
			}
			//GUARDO O ELIMINO EL "personaDocente" Según el perfilDocente recibido "true o false"
			mensaje = servicePersonaDocente.guardarEliminarValidando(personaDocente, perfilDocente);
			if(mensaje.getCodigoMensaje()==0) {
				return Mensaje.retornarMensaje(mensaje.getCodigoMensaje(), mensaje.getTipoAlerta(), mensaje.getDescripcionMensaje());
			}
			
			//GUARDO LOS PERFILES SEGÚN LOS SELECCIONADO. Para eso primero los organizo segun el metodo privado ListaPersonaPerfilOrganizado
			List<PersonaPerfil> listaPersonaPerfil = listaPersonaPerfilOrganizada(perfilEstudiante,perfilDocente,perfilAuxiliar,perfilAdministrador,personaRec.getIdPersona());
			servicePersonaPerfil.guardarLista(listaPersonaPerfil);
			
			return Mensaje.retornarMensaje(1, "msgS", "Registro guardado correctamente");
		} catch (Exception e) {
			return Mensaje.retornarMensaje(0, "msgD", "Error al guardar: " + e);
		}
	}
	
	
	
	//============================METODOS PRIVADOS =============================================================
	//Metodo para recibir el objeto persona con sus objetos relacionados y retornar solo el objeto perosna
	private Persona personaMapeoToPersona(Persona personaMapeoEntidades) {
		Persona personaRec = new Persona();
		personaRec.setIdPersona(personaMapeoEntidades.getIdPersona());
		personaRec.setIdentificacion(personaMapeoEntidades.getIdentificacion());
		personaRec.setPrimerNombre(personaMapeoEntidades.getPrimerNombre());
		personaRec.setSegundoNombre(personaMapeoEntidades.getSegundoNombre());
		personaRec.setPrimerApellido(personaMapeoEntidades.getPrimerApellido());
		personaRec.setSegundoApellido(personaMapeoEntidades.getSegundoApellido());
		personaRec.setEmail(personaMapeoEntidades.getEmail());
		personaRec.setFechaCreacion(personaMapeoEntidades.getFechaCreacion());
		personaRec.setUsuario(personaMapeoEntidades.getEmail());
		personaRec.setIndHabilitado(personaMapeoEntidades.isIndHabilitado());
		return personaRec;
	}
	
	private List<PersonaPerfil> listaPersonaPerfilOrganizada (Boolean perfilEstudiante, Boolean perfilDocente, Boolean perfilAuxiliar, Boolean perfilAdministrador, int idPersona){
		List<PersonaPerfil> listaPersonaPerfilCons = servicePersonaPerfil.listaPerfilesPersonaPorIdPersona(idPersona);
		List<PersonaPerfil> listaPersonaPerfilGuardar = new ArrayList<>();
		PersonaPerfil personaPerfil;
		//Ciclo para asignar el valor del perfil seleccionado
		for (PersonaPerfil lista :listaPersonaPerfilCons) {
			personaPerfil = new PersonaPerfil();
			if(lista.getPerfil().getIdPerfil()==1) {
				personaPerfil.setIndHabilitado(perfilEstudiante);
			}else if(lista.getPerfil().getIdPerfil()==2) {
				personaPerfil.setIndHabilitado(perfilDocente);
			}else if(lista.getPerfil().getIdPerfil()==3) {
				personaPerfil.setIndHabilitado(perfilAuxiliar);
			}else if(lista.getPerfil().getIdPerfil()==4) {
				personaPerfil.setIndHabilitado(perfilAdministrador);
			}
			
			//Valido si el idPersonaPerfil es mayor a 7000000 para iniciarlo y guardarlo como nuevo
			if(lista.getIdPersonaPerfil()<7000000) {
				personaPerfil.setIdPersonaPerfil(lista.getIdPersonaPerfil());
			}
			personaPerfil.setPersona(lista.getPersona());
			personaPerfil.setPerfil(lista.getPerfil());
			//Agrego los datos a guardar en la nueva lista de PersonaPerfil
			listaPersonaPerfilGuardar.add(personaPerfil);
		}
		return listaPersonaPerfilGuardar;
	}
	
	
}
