package com.asignacion.espacios.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.asignacion.espacios.clases.Mensaje;
import com.asignacion.espacios.entity.Clinica;
import com.asignacion.espacios.entity.MaeGrupoLista;
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
		List<MaeGrupoLista> listaCAE = serviceIMaeGrupoLista.listaOpcionesHabilitadasPorIdGrupoOrdenadosOrden(2);
	    model.addAttribute("listaCAE", listaCAE);
		model.addAttribute("Clinica", clinica);
		return "clinica/crearClinica";
	}
	
	@GetMapping("/editarClinica/{idClinica}")
	public String editarClinica(Model model, Clinica clinica, @PathVariable("idClinica")int idClinica) {
		clinica = serviceClinica.buscarClincaId(idClinica);
		model.addAttribute("Clinica", clinica);
		List<MaeGrupoLista> listaCAE = serviceIMaeGrupoLista.listaOpcionesHabilitadasPorIdGrupoOrdenadosOrden(2);
	    model.addAttribute("listaCAE", listaCAE);
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
	

}
