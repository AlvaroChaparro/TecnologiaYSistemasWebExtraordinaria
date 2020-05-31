package edu.uclm.esi.apalabreitor.apalabreitor.dao;

import org.springframework.data.repository.CrudRepository;

import edu.uclm.esi.apalabreitor.apalabreitor.model.User;

public interface UserRepository extends CrudRepository<User, String>{
	User findByEmail(String email);
}
