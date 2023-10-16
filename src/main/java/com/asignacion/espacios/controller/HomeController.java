package com.asignacion.espacios.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomBooleanEditor;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@GetMapping("/")
	public String home()
	{
		return "home";
	}
	
	@GetMapping("/login")
	public String login()
	{
		return "login/login";
	}
	
	@GetMapping("/logout") 
	public String logout(HttpServletRequest request){
		SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();
		logoutHandler.logout(request, null, null);
		return "redirect:/";
	}
	
	
	//Utilidad para generar encryptacion de contraseñas por medio de la URL
	@GetMapping("/bcrypt/{texto}")
	@ResponseBody
	public String encriptar(@PathVariable("texto") String texto) {
		return texto + " encriptado en Bcrypt: " + passwordEncoder.encode(texto);
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
