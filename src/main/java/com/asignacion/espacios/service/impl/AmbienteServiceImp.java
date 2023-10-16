package com.asignacion.espacios.service.impl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asignacion.espacios.clases.Mensaje;
import com.asignacion.espacios.model.dto.AmbienteDTO;
import com.asignacion.espacios.model.entity.Ambiente;
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
	public List<AmbienteDTO> listarTodos() {
		List<Ambiente> listaAmbiente = repoAmbiente.findAll();
		return listaAmbiente.stream().map(ambiente -> new AmbienteDTO(ambiente.getDescripcion(), ambiente.getFechaCreacion(), ambiente.isIndHabilitado())).collect(Collectors.toList());
	}

	@Override
	public void guardarAmbiente(AmbienteDTO ambienteDto) {
		repoAmbiente.save(convert2Entity(ambienteDto));
	}

	@Override
	public Mensaje guardarValidando(AmbienteDTO dto) {
		Ambiente ambienteExist = buscarAmbienteId(dto.getIdAmbiente());
		if(!Objects.isNull(ambienteExist)) {
			return Mensaje.retornarMensaje(0, "msgD", "El ambiente ya está registrado");
		}
		repoAmbiente.save(convert2Entity(dto));
		return Mensaje.retornarMensaje(0, "msgD", "Registro guardado correctamente");
	}
	
	private Ambiente convert2Entity(AmbienteDTO dto) {
		return new Ambiente(dto.getDescripcion(), dto.getFechaCreacion(), dto.isIndHabilitado());
	}

}
