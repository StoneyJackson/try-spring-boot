package com.in28minutes.rest.webservices.try_spring_boot.user;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
public class UserResource {

    private UserDaoService service;

    public UserResource(UserDaoService service) {
        this.service = service;
    }
    
    @GetMapping("/users")
    public List<User> retrieveAllUsers() {
        return service.findAll();
    }

    @GetMapping("/users/{id}")
    public User retrieveUser(@PathVariable int id) {
        User user = service.findOne(id);
        if (user == null)
            throw new UserNotFoundException("id:"+id);
        return user;
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id) {
        service.deleteById(id);
    }

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) throws URISyntaxException {
        User savedUser = service.save(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(savedUser.getId())
                        .toUri();
        return ResponseEntity.created(location).build();
    }
    
}
