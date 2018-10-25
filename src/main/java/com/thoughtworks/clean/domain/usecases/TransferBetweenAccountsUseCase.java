package com.thoughtworks.clean.domain.usecases;

import com.thoughtworks.clean.domain.entities.Account;
import com.thoughtworks.clean.domain.entities.TransferCheck;

import java.math.BigDecimal;

public interface TransferBetweenAccountsUseCase {
    TransferCheck transfer(Account fromAccount, Account toAccount, BigDecimal total);
}
