package com.asignacion.espacios.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomBooleanEditor;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.asignacion.espacios.clases.Mensaje;
import com.asignacion.espacios.entity.MaeGrupoLista;
import com.asignacion.espacios.entity.Persona;
import com.asignacion.espacios.entity.PersonaPerfil;
import com.asignacion.espacios.service.IMaeGrupoListaService;
import com.asignacion.espacios.service.IPersonaPerfilService;
import com.asignacion.espacios.service.IPersonaService;

@Controller
@RequestMapping("/persona")
public class personaController {

	@Autowired
	IPersonaService servicePersona;
	
	@Autowired
	IMaeGrupoListaService serviceIMaeGrupoLista;
	
	@Autowired
	IPersonaPerfilService servicePersonaPerfil;
	
	//*********************ESTUDIANTE*************************************//

	@GetMapping("/listarPersonaEstudiante")
	public String listarPersonaEstudiante(Model model) {
		List<PersonaPerfil> listaPersonaPerfilEstudiante = servicePersonaPerfil.listarPerfilHabilitadoPorIdPerfil(true, 1); //1: Estaduante
		model.addAttribute("listaPersonaPerfilEstudiante", listaPersonaPerfilEstudiante);
		return "estudiante/listarEstudiante";
	}
	
	@GetMapping("/crearPersonaEstudiante")
	public String crearPersonaEstudiante(Model model, Persona persona) {
		model.addAttribute("Persona", persona);
		return "estudiante/crearEstudiante";
	}
	
	@GetMapping("/editarPersonaEstudiante/{idPersona}")
	public String editarPersonaEstudiante(Model model, Persona persona, @PathVariable("idPersona")int idPersona) {
		persona = servicePersona.buscarPersonaId(idPersona);
		model.addAttribute("Persona", persona);
		return "estudiante/crearEstudiante";
	}
	
	@PostMapping("/guardarPersonaEstudiante")
	public String guardarPersonaEstudiante (Model model, Persona persona, RedirectAttributes attributes) {
		Mensaje mensaje = new Mensaje();
		mensaje = servicePersona.guardarValidando(persona);
		if(mensaje.getCodigoMensaje()==0) {
			model.addAttribute("Persona", persona);
			model.addAttribute(mensaje.getTipoAlerta(), mensaje.getDescripcionMensaje());
			return "estudiante/crearEstudiante";
		}
		attributes.addFlashAttribute(mensaje.getTipoAlerta(), mensaje.getDescripcionMensaje());
		return "redirect:/persona/listarPersonaEstudiante";
	}
		
	
	//*********************DOCENTE*************************************//
	
	@GetMapping("/listarPersonaDocente")
	public String listarPersonaDocente(Model model) {
		List<PersonaPerfil> listaPersonaPerfilDocente = servicePersonaPerfil.listarPerfilHabilitadoPorIdPerfil(true, 2); //2: Docente
		model.addAttribute("listaPersonaPerfilDocente", listaPersonaPerfilDocente);
		return "docente/listarDocente";
	}
	
	
	
	@GetMapping("/crearPersonaDocente")
	public String crearPersonaDocente(Model model, Persona persona) {
		model.addAttribute("Persona", persona);
		return "docente/crearDocente";
	}
	
	@GetMapping("/editarPersonaDocente/{idPersona}")
	public String editarPersonaDocente(Model model, Persona persona, @PathVariable("idPersona")int idPersona) {
		persona = servicePersona.buscarPersonaId(idPersona);
		model.addAttribute("Persona", persona);
		return "docente/crearDocente";
	}
	
	
	@PostMapping("/guardarPersonaDocente")
	public String guardarPersonaDocente (Model model, Persona persona, RedirectAttributes attributes) {
		Mensaje mensaje = new Mensaje();
		mensaje = servicePersona.guardarValidando(persona);
		if(mensaje.getCodigoMensaje()==0) {
			model.addAttribute("Persona", persona);
			model.addAttribute(mensaje.getTipoAlerta(), mensaje.getDescripcionMensaje());
			return "estudiante/crearDocente";
		}
		attributes.addFlashAttribute(mensaje.getTipoAlerta(), mensaje.getDescripcionMensaje());
		return "redirect:/persona/listarPersonaDocente";
	}
	
	//*********************AUXILIAR*************************************//
	
	@GetMapping("/listarPersonaAuxiliar")
	public String listarPersonaAuxiliar(Model model) {
		List<PersonaPerfil> listaPersonaPerfilAuxiliar = servicePersonaPerfil.listarPerfilHabilitadoPorIdPerfil(true, 3); //3: auxiliar
		model.addAttribute("listaPersonaPerfilAuxiliar", listaPersonaPerfilAuxiliar);
		return "auxiliar/listarAuxiliar";
	}

	
	@GetMapping("/crearPersonaAuxiliar")
	public String crearPersonaAuxiliar(Model model, Persona persona) {
		model.addAttribute("Persona", persona);
		return "auxiliar/crearAuxiliar";
	}
	
	@GetMapping("/editarPersonaAuxiliar/{idPersona}")
	public String editarPersonaAuxiliar(Model model, Persona persona, @PathVariable("idPersona")int idPersona) {
		persona = servicePersona.buscarPersonaId(idPersona);
		model.addAttribute("Persona", persona);
		return "auxiliar/crearAuxiliar";
	
	}
	
	@PostMapping("/guardarPersonaAuxiliar")
	public String guardarPersonaAuxiliar (Model model, Persona persona, RedirectAttributes attributes) {
		Mensaje mensaje = new Mensaje();
		mensaje = servicePersona.guardarValidando(persona);
		if(mensaje.getCodigoMensaje()==0) {
			model.addAttribute("Persona", persona);
			model.addAttribute(mensaje.getTipoAlerta(), mensaje.getDescripcionMensaje());
			return "estudiante/crearDocente";
		}
		attributes.addFlashAttribute(mensaje.getTipoAlerta(), mensaje.getDescripcionMensaje());
		return "redirect:/persona/listarPersonaDocente";
	}
	
	
	
	
	// ************ NECESARIOS PARA TRABAJAR DE FORMA GENERAL DENTRO DE TODO EL
	// CONTROLADOR**************
	// InitBinder para String si los detecta vacios el data Binding los settea a
	// Null
	@InitBinder
	public void InitBinder(WebDataBinder binder) {
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
	}

	// InitBinder para boleanos null los settea a falso
	@InitBinder
	public void IniBinderBoolean(WebDataBinder dataBinder) {
		dataBinder.registerCustomEditor(Boolean.class, new CustomBooleanEditor(false));
	}

	/* Darle formato a la fecha */
	@InitBinder
	public void initBinder(WebDataBinder webDataBinder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}

	// Listar maestras
	@ModelAttribute
	public void setGenericos(Model model, Authentication auth) {
		List<MaeGrupoLista> listaCAE = serviceIMaeGrupoLista.listaOpcionesHabilitadasPorIdGrupoOrdenadosOrden(2);
	    model.addAttribute("listaCAE", listaCAE);

	}

}
