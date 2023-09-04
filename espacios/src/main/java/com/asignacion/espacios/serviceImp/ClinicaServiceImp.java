package com.asignacion.espacios.serviceImp;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asignacion.espacios.clases.Mensaje;
import com.asignacion.espacios.entity.Clinica;
import com.asignacion.espacios.repository.ClinicaRepository;
import com.asignacion.espacios.service.IClinicaService;

@Service
public class ClinicaServiceImp implements IClinicaService {

	@Autowired
	ClinicaRepository repoClinica;

		
	
	//NATIVO=======================================================================================================================
	//buscar clinica por id
	public Clinica buscarClincaId(Integer idClinica) {
		Optional<Clinica> optional = repoClinica.findById(idClinica);
		if (optional.isPresent()) {
			return optional.get();
		}
		return null;
	}
	

	public List<Clinica> listarTodos() {
		return repoClinica.findAll();
	}


	//guardar o actualizar clinica
	public void guardarClinica(Clinica clinica) {
		repoClinica.save(clinica);

	}
	

	//guardar clinica retornando datos

	public Clinica guardarClinicaReturn(Clinica clinica) {
		clinica = repoClinica.save(clinica);
		return null;
	}


	

	
	//REPOSITORY===================================================================================================================

	public Clinica buscarPorNombre(String nombre) {
		Optional<Clinica> optional = Optional.ofNullable(repoClinica.findByNombre(nombre));
		if(optional.isPresent()) {
			return optional.get();
		}
		return null;
	}


	
	//METODOS Y LOGICA=============================================================================================================
	
	@Override
	public Mensaje guardarValidando(Clinica clinica) {
		if(clinica.getIdClinica()== null) {
			//Registro nuevo
			Clinica clinicaExist = buscarPorNombre(clinica.getNombre());
			if(clinicaExist!=null) {
				return Mensaje.retornarMensaje(0, "msgD", "La clinica ya está registrada");
			}
			clinica.setFechaCreacion(fecha());
			clinica = guardarClinicaReturn(clinica);
			return Mensaje.retornarMensaje(1, "msgS", "Registro guardado correctamente");
		}else {
			//Actualizacion registro
			clinica.setFechaCreacion(clinica.getFechaCreacion());
			clinica = guardarClinicaReturn(clinica);
			return Mensaje.retornarMensaje(1, "msgS", "Registro actualizado correctamente");
		}
		
		
	}
	
	public String fecha()
	{
	    SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd hh:mm aa");
	    long miliseconds = System.currentTimeMillis();
	    java.sql.Date date = new Date(miliseconds);
	    String dateFormateada = formato.format(date);
	    return dateFormateada;
	}
}
