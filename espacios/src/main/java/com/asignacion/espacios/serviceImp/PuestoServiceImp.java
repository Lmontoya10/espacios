package com.asignacion.espacios.serviceImp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asignacion.espacios.clases.Mensaje;
import com.asignacion.espacios.entity.Puesto;
import com.asignacion.espacios.repository.PuestoRepository;
import com.asignacion.espacios.service.IPuestoService;


@Service
public class PuestoServiceImp implements IPuestoService {

	@Autowired
	PuestoRepository repoPuesto;
	
	// REPOSITORY===================================================================================================================
	@Override
	public Puesto buscarPuestoId(Integer idPuesto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Puesto> listarTodos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void guardarPuesto(Puesto puesto) {
		// TODO Auto-generated method stub

	}

	@Override
	public void guardarPuestoLista(List<Puesto> listaPuesto) {
		repoPuesto.saveAll(listaPuesto);

	}

	@Override
	public Puesto guardarPuestoReturn(Puesto puesto) {
		// TODO Auto-generated method stub
		return null;
	}

	// REPOSITORY===================================================================================================================
	@Override
	public Puesto buscarPorCodigoAndModulo(String codigo, int idModulo) {
		// TODO Auto-generated method stub
		return null;
	}

	
	// METODOS Y LOGICA=============================================================================================================
	@Override
	public Mensaje guardarValidando(Puesto puesto) {
		// TODO Auto-generated method stub
		return null;
	}

}
