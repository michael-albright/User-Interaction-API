package com.tts.usersapi.controllers;

import java.util.List;
import java.util.Optional;

/* TO USE THIS PROGRAM YOU MUST CHANGE THE URL TO 
 * 			http://localhost:8080/users
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tts.usersapi.models.User;
import com.tts.usersapi.repositories.UserRepository;

@RestController
public class UserController 
{
	//add an autowired dependency for the repository
	@Autowired
	UserRepository repository;
	
	//add an endpoint to get all users,
	//but we changed it to add List<User>.findAll to
	//our Repository
	
	/* THIS GOT REMOVED AND CHANGED TO BELOW */
	
//				@GetMapping("/users")
//				public List<User> getUsers()
//				{
//					return repository.findAll();
//				}
	
	
			/*BIG CHANGE, NUMBER 20*/
	// Finally, modify our endpoint for getting all users to allow filtering by state. Note we are not creating a new endpoint, just updating the first one we created. If a
	//user passes in a query parameter for state, we call the repository method to find by state. If the user passes in no value, we simply call the repository method
	//to get all users.
	@GetMapping("/users")
	public List<User> getUsers(@RequestParam(value="state", required=false) String state)
	{
	if (state != null) {
	return (List<User>) repository.findByState(state);
	}
	return (List<User>) repository.findAll();
	}
	
	
	//add an endpoint to get a singlye user by id. Notice the emdpooint uses 
	// a path variable. Also since it is possible that a user witha given id
	//might not exist the method has to return Optional<User>, meaning the result
	//might be null.
	@GetMapping("/users/{id}")
	public Optional<User> getUserById(@PathVariable(value="id") Long id)
	{
		return repository.findById(id);
	}
	
	//add endpoint to create a new user. This endpoint 
	//requires that user data be sent in the request body,
	//so we use the @RequestBody annotation on the parameter.
	@PostMapping("/users")
	public void createUser(@RequestBody User user)
	{
		repository.save(user);
	}
	
	//Add an endpoint to update an existing user. We again use the @RequestBody 
	//annotation on the user data parameter
	@PutMapping("/users/{id}")
	public void createUser(@PathVariable(value="id") Long id, @RequestBody User user)
	{
	repository.save(user);
	}
	
	//Add an endpoint to delete an existing user.
	@DeleteMapping("/users/{id}")
	public void createUser(@PathVariable(value="id") Long id)
	{
	repository.deleteById(id);
	}
	
	
}

