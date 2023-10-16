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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.asignacion.espacios.clases.Mensaje;
import com.asignacion.espacios.model.entity.MaeGrupoLista;
import com.asignacion.espacios.model.entity.Persona;
import com.asignacion.espacios.model.entity.PersonaPerfil;
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
	
	@GetMapping("/listarTodasPersonas")
	public String listarPersonaEstudiante(Model model) {
		List<Persona> listarTodasPersonas = servicePersona.listarTodos();
		model.addAttribute("listarTodasPersonas", listarTodasPersonas);
		return "persona/listarPersona";
	}
	
	@GetMapping("/crearPersona")
	public String crearPersonaEstudiante(Model model, Persona persona) {
		model.addAttribute("Persona", persona);
		return "persona/crearPersona";
	}
	
	@GetMapping("/editarPersona/{idPersona}")
	public String editarPersonaEstudiante(Model model, Persona persona, @PathVariable("idPersona")int idPersona) {
		//Creo objetos y vairables
		Boolean perfilEstudiante = false;
		Boolean perfilDocente = false;
		Boolean perfilAuxiliar = false;
		Boolean perfilAdminsitrador = false;
		//Consulto la persona
		persona = servicePersona.buscarPersonaId(idPersona);
		model.addAttribute("Persona", persona);
		//Consulto los perfiles
		List<PersonaPerfil> listaPersonaPerfil = servicePersonaPerfil.listaPerfilesPersonaPorIdPersona(idPersona);
		for (PersonaPerfil lista :listaPersonaPerfil) {
			if(lista.getPerfil().getIdPerfil()==1) {
				perfilEstudiante = lista.isIndHabilitado();
			}else if(lista.getPerfil().getIdPerfil()==2) {
				perfilDocente = lista.isIndHabilitado();
			}else if(lista.getPerfil().getIdPerfil()==3) {
				perfilAuxiliar = lista.isIndHabilitado();
			}else if(lista.getPerfil().getIdPerfil()==4) {
				perfilAdminsitrador = lista.isIndHabilitado();
			}
		}
		model.addAttribute("perfilEstudiante", perfilEstudiante);
		model.addAttribute("perfilDocente", perfilDocente);
		model.addAttribute("perfilAuxiliar", perfilAuxiliar);
		model.addAttribute("perfilAdminsitrador", perfilAdminsitrador);
		return "persona/crearPersona";
	}
	
	@PostMapping("/guardarPersonaEstudiante")
	public String guardarPersonaEstudiante (Model model, Persona persona, RedirectAttributes attributes, 
			@RequestParam(value="perfilEstudiante", defaultValue =  "false") Boolean perfilEstudiante,
			@RequestParam(value="perfilDocente", defaultValue =  "false") Boolean perfilDocente,
			@RequestParam(value="perfilAuxiliar", defaultValue =  "false") Boolean perfilAuxiliar,
			@RequestParam(value="perfilAdminsitrador", defaultValue =  "false") Boolean perfilAdminsitrador) {
		//Declaro variables y objetos
		Mensaje mensaje = new Mensaje();
		//Guardo el objeto persona con los otros objetos matepados
		mensaje = servicePersona.guardarValidando(persona, perfilEstudiante, perfilDocente, perfilAuxiliar, perfilAdminsitrador);
		if(mensaje.getCodigoMensaje()==0) {
			model.addAttribute("Persona", persona);
			model.addAttribute(mensaje.getTipoAlerta(), mensaje.getDescripcionMensaje());
			return "persona/crearPersona";
		}
		attributes.addFlashAttribute(mensaje.getTipoAlerta(), mensaje.getDescripcionMensaje());
		model.addAttribute("Persona", persona);
		//return "persona/crearPersona";
		return "redirect:/persona/listarTodasPersonas";
	}
	
	
	
	
	
	
		
	@GetMapping("/listarPersonaDocente")
	public String listarPersonaDocente(Model model) {
		List<PersonaPerfil> listaPersonaPerfilDocente = servicePersonaPerfil.listarPerfilHabilitadoPorIdPerfil(true, 2); //2: Docente
		model.addAttribute("listaPersonaPerfilDocente", listaPersonaPerfilDocente);
		return "docente/listarDocente";
	}
	
	@GetMapping("/listarPersonaAuxiliar")
	public String listarPersonaAuxiliar(Model model) {
		List<PersonaPerfil> listaPersonaPerfilAuxiliar = servicePersonaPerfil.listarPerfilHabilitadoPorIdPerfil(true, 3); //3: auxiliar
		model.addAttribute("listaPersonaPerfilAuxiliar", listaPersonaPerfilAuxiliar);
		return "auxiliar/listarAuxiliar";
	}
	
	
	// ************ NECESARIOS PARA TRABAJAR DE FORMA GENERAL DENTRO DE TODO EL CONTROLADOR**************
	// InitBinder para String si los detecta vacios el data Binding los settea a Null
	@InitBinder
	public void InitBinder(WebDataBinder binder) {
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
	}

	// InitBinder para booleanos null los settea a falso
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
	    List<MaeGrupoLista> listaEspecialidadDocente = serviceIMaeGrupoLista.listaOpcionesHabilitadasPorIdGrupoOrdenadosOrden(4);
	    model.addAttribute("listaEspecialidadDocente", listaEspecialidadDocente);

	}

}
