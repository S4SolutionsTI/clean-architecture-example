package com.thoughtworks.clean.domain.usecases;

import com.thoughtworks.clean.domain.entities.Account;
import com.thoughtworks.clean.domain.entities.TransferCheck;

import java.math.BigDecimal;

public class AccountService implements TransferBetweenAccountsUseCase {

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public TransferCheck transfer(Account fromAccount, Account toAccount, BigDecimal total) {
        if (hasNoFunds(fromAccount, total)) {
            return TransferCheck.NO_FUNDS;
        }

        final Account debitedAccount = fromAccount.debit(total);
        final Account creditedAccount = toAccount.credit(total);

        final TransferCheck transferCheck = new TransferCheck(debitedAccount, creditedAccount);
        accountRepository.register(transferCheck);
        return transferCheck;
    }

    private boolean hasNoFunds(Account account, BigDecimal total) {
        return total.compareTo(account.getAmount()) > 0;
    }
}
