package com.thoughtworks.clean.delivery.data.oracle;

import com.thoughtworks.clean.domain.entities.Account;
import com.thoughtworks.clean.domain.entities.TransferCheck;
import com.thoughtworks.clean.domain.usecases.AccountRepository;

import java.util.Optional;

public class OracleAccountRepository implements AccountRepository {
    @Override
    public Optional<Account> findAccountById(Integer accountId) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void register(TransferCheck transferCheck) {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
