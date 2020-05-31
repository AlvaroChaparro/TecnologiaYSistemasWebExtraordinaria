package edu.uclm.esi.apalabreitor.apalabreitor.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import edu.uclm.esi.apalabreitor.apalabreitor.model.Palabra;


public interface PalabraRepository extends CrudRepository<Palabra, Integer> {

	List<Palabra> findByTexto(String texto);
}
