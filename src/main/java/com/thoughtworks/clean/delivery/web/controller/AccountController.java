package com.thoughtworks.clean.delivery.web.controller;

import com.thoughtworks.clean.delivery.web.dto.TransferRequestDto;
import com.thoughtworks.clean.delivery.web.dto.TransferResponseDto;
import com.thoughtworks.clean.domain.entities.Account;
import com.thoughtworks.clean.domain.entities.TransferCheck;
import com.thoughtworks.clean.domain.usecases.AccountRepository;
import com.thoughtworks.clean.domain.usecases.TransferBetweenAccountsUseCase;
import spark.Request;
import spark.Response;

import java.io.IOException;
import java.math.BigDecimal;

public class AccountController {

    private final AccountRepository accountRepository;
    private final TransferBetweenAccountsUseCase transferBetweenAccountsUseCase;

    public AccountController(AccountRepository accountRepository,
                             TransferBetweenAccountsUseCase transferBetweenAccountsUseCase) {
        this.accountRepository = accountRepository;
        this.transferBetweenAccountsUseCase = transferBetweenAccountsUseCase;
    }

    // POST /accounts/transfer
    public TransferResponseDto transfer(Request request, Response response) throws IOException {
        final TransferRequestDto requestDto = TransferRequestDto.from(request);
        Account fromAccount = retrieveAccountById(requestDto.getFromAccountId());
        Account toAccount = retrieveAccountById(requestDto.getToAccountId());
        BigDecimal amount = requestDto.getAmount();
        final TransferCheck transferCheck = transferBetweenAccountsUseCase.transfer(fromAccount, toAccount, amount);
        if (transferCheck.equals(TransferCheck.NO_FUNDS)) {
            response.status(500);
        } else {
            response.status(201);
        }
        return TransferResponseDto.from(transferCheck);
    }

    private Account retrieveAccountById(Integer accountId) {
        return accountRepository.findAccountById(accountId).orElse(null);
    }


}
