package com.asignacion.espacios.serviceImp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asignacion.espacios.clases.Mensaje;
import com.asignacion.espacios.entity.Ambiente;
import com.asignacion.espacios.repository.AmbienteRepository;
import com.asignacion.espacios.service.IAmbienteService;

@Service
public class AmbienteServiceImp implements IAmbienteService {
	
	@Autowired
	AmbienteRepository repoAmbiente;
	

	@Override
	public Ambiente buscarAmbienteId(Integer idAmbiente) {
		Optional<Ambiente> optional = repoAmbiente.findById(idAmbiente);
		if (optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	@Override
	public List<Ambiente> listarTodos() {
		return repoAmbiente.findAll();
	}

	@Override
	public void guardarAmbiente(Ambiente ambiente) {
		repoAmbiente.save(ambiente);

	}

	@Override
	public Mensaje guardarValidando(Ambiente ambiente) {
		Ambiente ambienteExist = buscarAmbienteId(ambiente.getIdAmbiente());
		if(ambienteExist!=null) {
			return Mensaje.retornarMensaje(0, "msgD", "El ambiente ya está registrado");
		}
		repoAmbiente.save(ambiente);
		return Mensaje.retornarMensaje(0, "msgD", "Registro guardado correctamente");
	}

}
