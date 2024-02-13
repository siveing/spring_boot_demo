package com.example.jin.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.jin.models.UserModel;
import com.example.jin.repositories.UserRepository;

@Controller // This means that this class is a Controller
@RequestMapping(path = "/user") // This means URL's start with /demo (after Application path)
public class UserController {

    @Autowired // This means to get the bean called userRepository
    // Which is auto-generated by Spring, we will use it to handle the data
    private UserRepository userRepository;

    @PostMapping(path = "/add") // Map ONLY POST Requests
    public ResponseEntity<String> addNewUser(@RequestParam("name") String name,
            @RequestParam("email") String email) {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request

        UserModel user = new UserModel();
        user.setName(name);
        user.setEmail(email);
        userRepository.save(user);

        // Return a success response with a custom message
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("User created successfully with ID: " + user.getId());
    }

    @GetMapping(path = "/all")
    public ResponseEntity<Iterable<UserModel>> getAllUsers() {
        // This returns a JSON or XML with the users
        return ResponseEntity.ok(userRepository.findAll());
    }

    @GetMapping("/show/{id}")
    public ResponseEntity<Optional<UserModel>> showUserModel(@PathVariable("id") Integer id) {
        // @PathVariable means it is a parameter from the path URI request

        return ResponseEntity.ok(userRepository.findById(id));
    }

    @DeleteMapping("/delete/{id}")
    public @ResponseBody String deleteUserModel(@PathVariable("id") Integer id) {
        // Remove the object
        userRepository.deleteById(id);
        // Return the message that data has deleted
        return "Delete successfully.";
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateUserModel(
            @PathVariable("id") Integer id,
            @RequestParam("name") String name,
            @RequestParam("email") String email) {

        // Check if the user exists
        UserModel user = userRepository.findById(id).orElse(null);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("User with ID " + id + " not found");
        }

        // Update user information
        user.setName(name);
        user.setEmail(email);
        userRepository.save(user);

        // Return the message that data has deleted
        return ResponseEntity.ok("User with ID " + id + " updated successfully");
    }
}