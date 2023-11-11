package com.lseg_proy.accountsystem.controller;

import com.lseg_proy.accountsystem.exception.UserNotFoundException;
import com.lseg_proy.accountsystem.model.Account;
import com.lseg_proy.accountsystem.model.User;
import com.lseg_proy.accountsystem.service.AccountService;
import com.lseg_proy.accountsystem.service.UserService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The AccountController class handles incoming HTTP requests related to account operations.
 * It interacts with AccountService and UserService to perform business logic.
 */
@RestController
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private UserService userService;

    /**
     * Retrieves an account by its ID.
     *
     * @param id the ID of the account to retrieve
     * @return ResponseEntity containing the account or a not-found status
     */
    @GetMapping("/account/{id}")
    public ResponseEntity<Account> getAccountById(@PathVariable int id) {
        return accountService.getAccountById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Retrieves a list of all accounts.
     *
     * @return a list of all accounts
     */
    @GetMapping("/accounts")
    public List<Account> getAllAccounts() {
        return accountService.getAccounts();
    }

    /**
     * Adds a new account based on the provided AccountDto.
     *
     * @param accountDto the data transfer object containing account details
     * @return the newly created account
     * @throws UserNotFoundException if the user associated with the account is not found
     */
    @PostMapping("/addAccount")
    public Account addAccount(@RequestBody AccountDto accountDto) throws UserNotFoundException {
        int userId = accountDto.getUserId();
        User user = userService.getUserById(userId);

        if (user == null) {
            throw new UserNotFoundException("User not found with id: " + userId);
        }

        Account account = new Account();
        account.setAccountName(accountDto.getAccountName());
        account.setAccountCurrency(accountDto.getAccountCurrency());
        account.setUser(user);

        return accountService.createAccount(account);
    }

    /**
     * AccountDto is an inner class used as a Data Transfer Object (DTO) for account information.
     */
    @Data
    public static class AccountDto {
        private int userId;
        private String accountName;
        private String accountCurrency;
    }
}
