package com.asignacion.espacios.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class DataBaseWebSecurity extends WebSecurityConfigurerAdapter {

	@Autowired
	private DataSource dataSource;

	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().dataSource(dataSource)
				.usersByUsernameQuery("select usuario, contrasena, indHabilitado from Persona where usuario=?")
				.authoritiesByUsernameQuery("select p.usuario, pe.descripcion from PersonaPerfil pp "
						+ "inner join Persona p on p.idPersona = pp.idPersona "
						+ "inner join Perfil pe on pe.idPerfil = pp.idPerfil " 
						+ "where p.usuario = ? And p.indHabilitado = 1 And pp.indHabilitado = 1");
	}

	//PROTEGER ACCESO A URL
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		
			//Los recursos estáticos no requieren autenticación
			.antMatchers("/complementos/**","/css/**","/js/**","/imagenesGenerales/**").permitAll()
			
			//Las vistas que no requieren autenticación
			.antMatchers("/login","/bcrypt/**","/logout").permitAll()
			
			//=============================CONFIGURACIIONES DE SUPERUSUARIOS ==============================================================
	        .antMatchers("/listaOpciones/EditarDatoGeneralEmpresa",
	        		"/listaOpciones/guardarDatoGeneralEmpresa",
	        		"/listaOpciones/CrearEditarDatoGeneralSede/**",
	        		"/listaOpciones/guardarDatoGeneralSede"
	        		)
	        .hasAnyAuthority("SUPERUSUARIO")
			
			//=============================ADMINISTRACIÓN DE USUARIOS, CREACIÓN DE USUARIOS ==============================================================
	        .antMatchers("/usuario/listaUsuarioSistema",
	        		"/usuario/crearUsuarioSistema",
	        		"/usuario/editarUsuarioSistema/**",
	        		"/usuario/guardarUsuarioSistema",
	        		"/usuario/AsignarPermisos/**",
	        		"/usuario/guardarAsignarPermisos",
	        		"/usuario/AsignarSedeUsuario/**",
	        		"/usuario/guardarAsignarSedeUsuario",
	        		"/usuario/GrupoOpcionesUsuario/**",
	        		"/usuario/guardarGrupoOpcionesUsuario",
	        		"/usuario/eliminarGrupoOpcionesUsuario/**"
	        		)
	        .hasAnyAuthority("SUPERUSUARIO","ADMINISTRADOR")
	        
	        
	        //=============================PROVEEDORES - FACTURACION ============================================
			
	        
	        
			
			//Todas las demás URLs de la Aplicación requieren autenticación
			.anyRequest().authenticated()
				
			
			//El formulario de Login no requiere autenticacion
			.and().formLogin().loginPage("/login")
			.permitAll()
			.defaultSuccessUrl("/");
		
		
	}
	
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(4);
	}
}
