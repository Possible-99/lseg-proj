package com.lseg_proy.accountsystem.service;

import com.lseg_proy.accountsystem.model.Account;
import com.lseg_proy.accountsystem.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service class for handling business logic related to accounts.
 * This class interacts with the AccountRepository to perform CRUD operations.
 */
@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    /**
     * Creates and saves a new account in the repository.
     *
     * @param account The Account object to be saved.
     * @return The saved Account object, now containing a generated ID.
     */
    public Account createAccount(Account account) {
        return accountRepository.save(account);
    }

    /**
     * Retrieves an account by its ID.
     *
     * @param id The ID of the account to retrieve.
     * @return An Optional containing the Account if found, or an empty Optional if not found.
     */
    public Optional<Account> getAccountById(int id) {
        return accountRepository.findById(id);
    }

    /**
     * Retrieves a list of all accounts in the repository.
     *
     * @return A List of all accounts.
     */
    public List<Account> getAccounts() {
        return accountRepository.findAll();
    }
}
