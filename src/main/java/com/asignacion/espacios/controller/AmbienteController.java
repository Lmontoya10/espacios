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
import com.asignacion.espacios.model.dto.AmbienteDTO;
import com.asignacion.espacios.model.entity.Ambiente;
import com.asignacion.espacios.service.IAmbienteService;

@Controller
@RequestMapping("/ambiente")
public class AmbienteController {
	
	@Autowired
	IAmbienteService serviceAmbiente;

	
	@GetMapping("/listarAmbientes")
	public String listarAmbiente(Model model) {
		List<AmbienteDTO> listaAmbientes = serviceAmbiente.listarTodos();
		model.addAttribute("listaAmbientes", listaAmbientes);
		return "ambiente/listarAmbiente";
	}
	
	@GetMapping("/crearAmbiente")
	public String crearAmbiente(Model model, AmbienteDTO ambiente) {
		model.addAttribute("Ambiente", ambiente);
		return "ambiente/crearAmbiente";
	}
	
	
//	@GetMapping("/editarAmbiente/{idAmbiente}")
//	public String editarAmbiente(Model model, Ambiente ambiente, @PathVariable("idAmbiente")int idAmbiente) {
//		ambiente = serviceAmbiente.buscarAmbienteId(idAmbiente);
//		model.addAttribute("Ambiente", ambiente);
//		return "ambiente/crearAmbiente";
//	}
	
	
	@PostMapping("/guardarAmbiente")
	public String guardarAmbiente (Model model, AmbienteDTO ambiente, RedirectAttributes attributes) {
		Mensaje mensaje = new Mensaje();
		mensaje = serviceAmbiente.guardarValidando(ambiente);
		if(mensaje.getCodigoMensaje()==0) {
			model.addAttribute("Ambiente", ambiente);
			model.addAttribute(mensaje.getTipoAlerta(), mensaje.getDescripcionMensaje());
			return "ambiente/crearAmbiente";
		}
		attributes.addFlashAttribute(mensaje.getTipoAlerta(), mensaje.getDescripcionMensaje());
		return "redirect:/ambiente/crearAmbiente";
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
