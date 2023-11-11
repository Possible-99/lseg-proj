package com.lseg_proy.accountsystem.service;

import com.lseg_proy.accountsystem.exception.AccountNotFoundException;
import com.lseg_proy.accountsystem.exception.UserNotFoundException;
import com.lseg_proy.accountsystem.model.Account;
import com.lseg_proy.accountsystem.model.User;
import com.lseg_proy.accountsystem.repository.AccountRepository;
import com.lseg_proy.accountsystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class for managing user-related operations.
 * It interacts with the UserRepository and AccountRepository to perform CRUD operations.
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AccountRepository accountRepository;

    /**
     * Creates and saves a new user entity in the database.
     *
     * @param user The User entity to be saved.
     * @return The saved User entity.
     */
    public User createUser(User user){
        return userRepository.save(user);
    }

    /**
     * Retrieves a user by their ID.
     *
     * @param id The ID of the user to be retrieved.
     * @return The found User entity.
     * @throws UserNotFoundException if the user with the specified ID is not found.
     */
    public User getUserById(int id){
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
    }

    /**
     * Retrieves all users from the database.
     *
     * @return A list of all User entities.
     */
    public List<User> getUsers(){
        return userRepository.findAll();
    }

    /**
     * Deletes an account associated with a user by their respective IDs.
     *
     * @param userId The ID of the user.
     * @param accountId The ID of the account to be deleted.
     * @throws UserNotFoundException if the user with the specified ID is not found.
     * @throws AccountNotFoundException if the account with the specified ID is not found or not associated with the user.
     */
    public void deleteAccountFromUserById(int userId, int accountId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + userId));
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new AccountNotFoundException("Account not found with id: " + accountId));

        if(user.getAccounts().contains(account)) {
            user.getAccounts().remove(account);
            accountRepository.deleteById(accountId);
        } else {
            throw new AccountNotFoundException("Account not associated with the user with id: " + userId);
        }
    }
}
