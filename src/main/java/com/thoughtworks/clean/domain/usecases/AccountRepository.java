package com.thoughtworks.clean.domain.usecases;

import com.thoughtworks.clean.domain.entities.Account;
import com.thoughtworks.clean.domain.entities.TransferCheck;

import java.util.Optional;

public interface AccountRepository {
    Optional<Account> findAccountById(Integer accountId);

    void register(TransferCheck transferCheck);
}
