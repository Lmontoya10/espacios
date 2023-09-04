package com.asignacion.espacios.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class webConfig implements WebMvcConfigurer{
	
	@Value("${espacios.ruta.imagenes}")
	private String rutaImagenes;
	
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		
		//Configuración de los recursos estáticos (imagenes generales del proyecto)
		registry.addResourceHandler("/imagenesGenerales/**").addResourceLocations("file:"+rutaImagenes); //windows
		
	}

}
