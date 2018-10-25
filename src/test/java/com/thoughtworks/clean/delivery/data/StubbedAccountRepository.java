package com.thoughtworks.clean.delivery.data;

import com.thoughtworks.clean.domain.entities.Account;
import com.thoughtworks.clean.domain.entities.TransferCheck;
import com.thoughtworks.clean.domain.usecases.AccountRepository;

import java.util.Optional;

public class StubbedAccountRepository implements AccountRepository {

    private boolean transferExecuted = false;
    private TransferCheck transferCheckPassedToRepository = null;

    @Override
    public Optional<Account> findAccountById(Integer accountId) {
        return Optional.empty();
    }

    @Override
    public void register(TransferCheck transferCheck) {
        transferExecuted = true;
        transferCheckPassedToRepository = transferCheck;
    }

    public boolean isTransferSaved() {
        return transferExecuted;
    }

    public TransferCheck getTransferCheckPassedToRepository() {
        return transferCheckPassedToRepository;
    }
}
