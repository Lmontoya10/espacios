package com.asignacion.espacios.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.asignacion.espacios.model.entity.PersonaPerfil;

public interface PersonaPerfilRepository extends JpaRepository<PersonaPerfil, Integer> {
	
	List<PersonaPerfil> findByIndHabilitadoAndPerfil_IdPerfil(boolean indHabilitado, int idPerfil);
	
	@Query(value = "SELECT ISNULL(B.idPersonaPerfil, ROW_NUMBER() OVER(ORDER BY B.idPersonaPerfil ASC) + 7000000) AS idPersonaPerfil, C.idPersona, A.idPerfil, ISNULL(B.indHabilitado,0) AS indHabilitado "
			+ "FROM Perfil A "
			+ "LEFT OUTER JOIN PersonaPerfil B ON B.idPerfil = A.idPerfil AND B.idPersona = ?1 "
			+ "LEFT OUTER JOIN Persona C ON C.idPersona = ?1 ", nativeQuery = true)
	List<PersonaPerfil> listaPerfilesPersonaPorIdPersona(@Param("idPersona")int idPersona);
	
}
