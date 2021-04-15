package com.tts.usersapi.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import com.tts.usersapi.models.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long>
{
    public List<User> findByState(String state);
    
    @Override
    public List<User> findAll();
    
    public List<User> findByFirstNameAndLastName(String firstName, String lastName);
}
