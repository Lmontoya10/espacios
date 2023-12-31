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
import com.asignacion.espacios.model.entity.Modulo;
import com.asignacion.espacios.model.entity.Puesto;
import com.asignacion.espacios.service.IModuloService;
import com.asignacion.espacios.service.IPuestoService;

@Controller
@RequestMapping("/puesto")
public class PuestoController {

	@Autowired
	IPuestoService servicePuesto;
	

	@Autowired
	IModuloService serviceModulo;

	
	@GetMapping("/listarPuesto")
	public String listarPuesto(Model model) {
		List<Puesto> listaPuestos = servicePuesto.listarTodos();
		model.addAttribute("listaPuestos", listaPuestos);
		return "puesto/listarPuesto";
	}
	
	@GetMapping("/crearPuesto")
	public String crearPuesto(Model model, Puesto puesto) {
		List<Modulo> listaModulos = serviceModulo.listarTodos();
		model.addAttribute("listaModulos",listaModulos);
		model.addAttribute("Puesto", puesto);
		return "puesto/crearPuesto";
	}
	
	
	@GetMapping("/editarPuesto/{idPuesto}")
	public String editarPuesto(Model model, Puesto puesto, @PathVariable("idPuesto")int idPuesto) {
		puesto = servicePuesto.buscarPuestoId(idPuesto);
		model.addAttribute("Puesto", puesto);
		List<Modulo> listaModulos = serviceModulo.listarTodos();
	        model.addAttribute("listaModulos", listaModulos);
		return "puesto/crearPuesto";
	}
	
	
	@PostMapping("/guardarPuesto")
	public String guardarPuesto (Model model, Puesto puesto, RedirectAttributes attributes) {
		Mensaje mensaje = new Mensaje();
		mensaje = servicePuesto.guardarValidando(puesto);
		if(mensaje.getCodigoMensaje()==0) {
			model.addAttribute("Puesto", puesto);
			model.addAttribute(mensaje.getTipoAlerta(), mensaje.getDescripcionMensaje());
			return "puesto/crearPuesto";
		}
		attributes.addFlashAttribute(mensaje.getTipoAlerta(), mensaje.getDescripcionMensaje());
		return "redirect:/puesto/listarPuesto";
	}

	
	//************ NECESARIOS PARA TRABAJAR DE FORMA GENERAL DENTRO DE TODO EL CONTROLADOR**************
		//InitBinder para String si los detecta vacios el data Binding los settea a Null	 
		@InitBinder
		public void InitBinder(WebDataBinder binder) {
			binder.registerCustomEditor(String.class, new StringTrimmerEditor(true) );
		}
		
		//InitBinder para boleanos null los settea a falso	 
		@InitBinder
		public void IniBinderBoolean(WebDataBinder dataBinder) {
			dataBinder.registerCustomEditor(Boolean.class, new CustomBooleanEditor(false));
		}
		
		/*Darle formato a la fecha*/
		@InitBinder
		public void initBinder(WebDataBinder webDataBinder) {
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
		}
		
		//Listar maestras
		@ModelAttribute
		public void setGenericos(Model model, Authentication auth) {
			
		}

}
