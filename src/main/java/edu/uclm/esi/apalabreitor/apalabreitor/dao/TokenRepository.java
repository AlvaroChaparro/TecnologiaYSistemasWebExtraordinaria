package edu.uclm.esi.apalabreitor.apalabreitor.dao;

import org.springframework.data.repository.CrudRepository;

import edu.uclm.esi.apalabreitor.apalabreitor.model.Token;
import edu.uclm.esi.apalabreitor.apalabreitor.model.User;


public interface TokenRepository extends CrudRepository<Token, String>{
	Token findByEmail(String email);
}
