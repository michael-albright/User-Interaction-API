package com.tts.usersapi.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tts.usersapi.models.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> 
{
	//adds the method to find Users with the property 'state'
	List<User> findByState(String state);
	
	@Override
	public List<User> findAll();
}
