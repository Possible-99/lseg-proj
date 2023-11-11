package com.lseg_proy.accountsystem.repository;

import com.lseg_proy.accountsystem.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account,Integer> {
}
