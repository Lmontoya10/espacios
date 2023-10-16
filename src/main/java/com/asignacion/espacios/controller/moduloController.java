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
import com.asignacion.espacios.model.entity.Ambiente;
import com.asignacion.espacios.model.entity.MaeGrupoLista;
import com.asignacion.espacios.model.entity.Modulo;
import com.asignacion.espacios.service.IAmbienteService;
import com.asignacion.espacios.service.IMaeGrupoListaService;
import com.asignacion.espacios.service.IModuloService;


@Controller
@RequestMapping("/modulo")
public class moduloController {
	
	@Autowired
	IModuloService serviceModulo;
	
	@Autowired
	IMaeGrupoListaService serviceIMaeGrupoLista;
	
	@Autowired
	IAmbienteService serviceAmbiente;


	
	@GetMapping("/listarModulos")
	public String listarModulos(Model model) {
		List<Modulo> listaModulos = serviceModulo.listarTodos();
		model.addAttribute("listaModulos", listaModulos);
		System.out.println("moduloController.listarModulos()");
		return "modulo/listarModulos";
	}
	
	@GetMapping("/crearModulo")
	public String crearModulo(Model model, Modulo modulo) {
		// List<Ambiente>listaAmbiente = serviceAmbiente.listarTodos();
		// model.addAttribute("listaAmbiente", listaAmbiente);
		model.addAttribute("Modulo", modulo);
		return "modulo/crearModulo";
	}
	
	@GetMapping("/editarModulo/{idModulo}")
	public String editarModulo(Model model, Modulo modulo, @PathVariable("idModulo")int idModulo) {
		modulo = serviceModulo.buscarModuloId(idModulo);
		model.addAttribute("Modulo", modulo);
		// List<Ambiente>listaAmbiente = serviceAmbiente.listarTodos();
		// model.addAttribute("listaAmbientes", listaAmbiente);
		return "modulo/crearModulo";
	}
	
	
	@PostMapping("/guardarModulo")
	public String guardarModulo (Model model, Modulo modulo, RedirectAttributes attributes) {
		Mensaje mensaje = new Mensaje();
		mensaje = serviceModulo.guardarValidando(modulo);
		if(mensaje.getCodigoMensaje()==0) {
			model.addAttribute("Modulo", modulo);
			model.addAttribute(mensaje.getTipoAlerta(), mensaje.getDescripcionMensaje());
			return "modulo/crearModulo";
		}
		attributes.addFlashAttribute(mensaje.getTipoAlerta(), mensaje.getDescripcionMensaje());
		return "redirect:/modulo/listarModulos";
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
			  List<MaeGrupoLista> listaOrientacion = serviceIMaeGrupoLista.listaOpcionesHabilitadasPorIdGrupoOrdenadosOrden(3);
			    model.addAttribute("listaOrientacion", listaOrientacion);
			    List<MaeGrupoLista> listaDistribucion = serviceIMaeGrupoLista.listaOpcionesHabilitadasPorIdGrupoOrdenadosOrden(8);
			    model.addAttribute("listaDistribucion", listaDistribucion);
			    List<Ambiente> listaAmbientes = serviceAmbiente.listarTodos();
			    model.addAttribute("listaAmbientes", listaAmbientes);
	
		}
	
	
}
