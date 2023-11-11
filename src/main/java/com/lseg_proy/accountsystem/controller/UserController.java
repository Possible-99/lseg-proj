package com.lseg_proy.accountsystem.controller;

import com.lseg_proy.accountsystem.model.User;
import com.lseg_proy.accountsystem.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for managing users.
 * This class handles HTTP requests related to user operations,
 * delegating business logic to the UserService class.
 */
@RestController
public class UserController {

    private final UserService userService;

    /**
     * Constructor for UserController.
     *
     * @param userService The UserService to be used for user-related operations.
     */
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Endpoint for creating a new user.
     *
     * @param user The User object to be created.
     * @return ResponseEntity containing the created User.
     */
    @PostMapping("/users")
    public ResponseEntity<User> addUser(@Valid @RequestBody User user) {
        User createdUser = userService.createUser(user);
        return ResponseEntity.ok(createdUser);
    }

    /**
     * Endpoint for retrieving a user by their ID.
     *
     * @param id The ID of the user to be retrieved.
     * @return ResponseEntity containing the requested User.
     */
    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable int id) {
        User user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    /**
     * Endpoint for retrieving all users.
     *
     * @return ResponseEntity containing a list of all Users.
     */
    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getUsers();
        return ResponseEntity.ok(users);
    }

    /**
     * Endpoint for deleting an account from a user by their respective IDs.
     *
     * @param userId The ID of the user.
     * @param accountId The ID of the account to be deleted.
     * @return ResponseEntity with HTTP status OK.
     */
    @DeleteMapping("/users/{userId}/accounts/{accountId}")
    public ResponseEntity<?> deleteAccountFromUserById(@PathVariable int userId, @PathVariable int accountId) {
        userService.deleteAccountFromUserById(userId, accountId);
        return ResponseEntity.ok().build();
    }
}
