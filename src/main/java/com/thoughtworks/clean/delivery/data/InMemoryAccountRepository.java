package com.thoughtworks.clean.delivery.data;

import com.thoughtworks.clean.domain.entities.Account;
import com.thoughtworks.clean.domain.entities.TransferCheck;
import com.thoughtworks.clean.domain.usecases.AccountRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// Thread-safe
public class InMemoryAccountRepository implements AccountRepository {

    private final List<Account> accounts = new ArrayList<>();
    private final List<TransferCheck> transferChecks = new ArrayList<>();

    public InMemoryAccountRepository() {
        initExampleAccounts();
    }

    @Override
    public Optional<Account> findAccountById(Integer accountId) {
        synchronized (accounts) {
            return accounts
                    .stream()
                    .filter(account -> accountId.equals(account.getNumber()))
                    .findFirst();
        }
    }

    @Override
    public void register(TransferCheck transferCheck) {
        synchronized (accounts) {
            update(transferCheck.getFromAccount());
            update(transferCheck.getToAccount());
            transferChecks.add(transferCheck);
        }
    }

    private void update(Account account) {
        accounts.removeIf(oldVersionAccount -> account.getNumber().equals(oldVersionAccount.getNumber()));
        accounts.add(account);
    }

    private void initExampleAccounts() {
        accounts.add(new Account(1, BigDecimal.TEN));
        accounts.add(new Account(2, BigDecimal.ZERO));
    }
}
