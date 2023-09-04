package com.asignacion.espacios.serviceImp;


import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.attoparser.config.ParseConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asignacion.espacios.clases.Mensaje;
import com.asignacion.espacios.entity.Modulo;
import com.asignacion.espacios.entity.Puesto;
import com.asignacion.espacios.repository.ModuloRepository;
import com.asignacion.espacios.repository.PuestoRepository;
import com.asignacion.espacios.service.IModuloService;
import com.asignacion.espacios.service.IPuestoService;

@Service
public class ModuloServiceImp implements IModuloService {

	@Autowired
	ModuloRepository repoModulo;
	
	@Autowired
	IPuestoService servicePuesto;
	
	//NATIVO=======================================================================================================================
	//buscar modulo por id

	public Modulo buscarModuloId(Integer idModulo) {
		Optional<Modulo> optional = repoModulo.findById(idModulo);
		if (optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	@Override
	public List<Modulo> listarTodos() {
		return repoModulo.findAll();
	}

	@Override
	public void guardarModulo(Modulo modulo) {
		repoModulo.save(modulo);

	}

	@Override
	public Modulo guardarModuloReturn(Modulo modulo) {
		
		modulo=repoModulo.save(modulo);
		return modulo;
	}
	
	
	// REPOSITORY===================================================================================================================
	@Override
	public Modulo buscarPorCodigo(String codigo) {
		Optional<Modulo> optional = Optional.ofNullable(repoModulo.findByCodigo(codigo));
		if (optional.isPresent()) {
			return optional.get();
		}
		return null;
	}


	//METODOS Y LOGICA=============================================================================================================
	public Mensaje guardarValidando(Modulo modulo) {
			Modulo moduloExist = buscarPorCodigo(modulo.getCodigo());
			if(moduloExist!=null) {
				return Mensaje.retornarMensaje(0, "msgD", "El modulo ya está registrado");
			}
			modulo = guardarModuloReturn(modulo);
			List<Puesto> listaPuesto = new ArrayList<>();
			Puesto puesto;
			int consecutivoFinal = modulo.getConsecutivoInicial() + modulo.getCantidadPuestos();
			
			for (int i = modulo.getConsecutivoInicial(); i < consecutivoFinal ;i++) {
				puesto = new Puesto();
				puesto.setCodigo(String.valueOf(i));
				puesto.setModulo(modulo);
				puesto.setIndHabilitado(true);
				puesto.setFechaCreacion(modulo.getFechaCreacion());
				listaPuesto.add(puesto);
			}
			servicePuesto.guardarPuestoLista(listaPuesto);
			return Mensaje.retornarMensaje(1, "msgS", "Registro guardado correctamente");
	}

	
	

}
