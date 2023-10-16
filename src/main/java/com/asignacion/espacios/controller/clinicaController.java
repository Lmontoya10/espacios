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
import com.asignacion.espacios.model.entity.Clinica;
import com.asignacion.espacios.model.entity.MaeGrupoLista;
import com.asignacion.espacios.service.IClinicaService;
import com.asignacion.espacios.service.IMaeGrupoListaService;


@Controller
@RequestMapping("/clinica")
public class clinicaController {
	
	@Autowired
	IClinicaService serviceClinica;
	
	@Autowired
	IMaeGrupoListaService serviceIMaeGrupoLista;

	@GetMapping("/listarClinicas")
	public String listarClinicas(Model model) {
		List<Clinica> listaClinicas = serviceClinica.listarTodos();
		model.addAttribute("listaClinicas", listaClinicas);
		return "clinica/listarClinicas";
	}
	
	
	@GetMapping("/crearClinica")
	public String crearClinica(Model model, Clinica clinica) {
		model.addAttribute("Clinica", clinica);
		return "clinica/crearClinica";
	}
	
	@GetMapping("/editarClinica/{idClinica}")
	public String editarClinica(Model model, Clinica clinica, @PathVariable("idClinica")int idClinica) {
		clinica = serviceClinica.buscarClincaId(idClinica);
		model.addAttribute("Clinica", clinica);
		return "clinica/crearClinica";
	}

	@PostMapping("/guardarClinica")
	public String guardarClinica (Model model, Clinica clinica, RedirectAttributes attributes) {
		Mensaje mensaje = new Mensaje();
		mensaje = serviceClinica.guardarValidando(clinica);
		if(mensaje.getCodigoMensaje()==0) {
			model.addAttribute("Clinica", clinica);
			model.addAttribute(mensaje.getTipoAlerta(), mensaje.getDescripcionMensaje());
			return "clinica/crearClinica";
		}
		attributes.addFlashAttribute(mensaje.getTipoAlerta(), mensaje.getDescripcionMensaje());
		return "redirect:/clinica/listarClinicas";
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
		List<MaeGrupoLista> listaNombreClinica = serviceIMaeGrupoLista.listaOpcionesHabilitadasPorIdGrupoOrdenadosOrden(7);
	    model.addAttribute("listaNombreClinica", listaNombreClinica);
		List<MaeGrupoLista> listaCAE = serviceIMaeGrupoLista.listaOpcionesHabilitadasPorIdGrupoOrdenadosOrden(2);
	    model.addAttribute("listaCAE", listaCAE);
	    List<MaeGrupoLista> listaDiaSemana = serviceIMaeGrupoLista.listaOpcionesHabilitadasPorIdGrupoOrdenadosOrden(5);
	    model.addAttribute("listaDiaSemana", listaDiaSemana);
	    List<MaeGrupoLista> listaHorario = serviceIMaeGrupoLista.listaOpcionesHabilitadasPorIdGrupoOrdenadosOrden(6);
	    model.addAttribute("listaHorario", listaHorario);

	}
	

}
