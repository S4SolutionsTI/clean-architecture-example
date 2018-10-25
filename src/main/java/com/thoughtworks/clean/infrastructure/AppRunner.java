package com.thoughtworks.clean.infrastructure;

import com.thoughtworks.clean.delivery.data.InMemoryAccountRepository;
import com.thoughtworks.clean.delivery.data.oracle.OracleAccountRepository;
import com.thoughtworks.clean.delivery.web.controller.AccountController;
import com.thoughtworks.clean.domain.usecases.AccountRepository;
import com.thoughtworks.clean.domain.usecases.AccountService;
import com.thoughtworks.clean.domain.usecases.TransferBetweenAccountsUseCase;
import com.thoughtworks.clean.infrastructure.web.WebApp;

import java.util.Arrays;
import java.util.Map;

import static java.util.stream.Collectors.toMap;

public class AppRunner {

    private final Configuration configuration;
    private WebApp webApp;

    public AppRunner(String[] args) {
        this.configuration = new Configuration(convertToMap(args));
    }

    public void run() {
        // adapter - DB
        final AccountRepository accountRepository = initAccountRepository();
        // use cases
        final TransferBetweenAccountsUseCase transferBetweenAccountsUseCase = new AccountService(accountRepository);
        // adapter - WEB
        final AccountController accountController = new AccountController(accountRepository, transferBetweenAccountsUseCase);
        // frameworks
        this.webApp = new WebApp(configuration, accountController);
        webApp.start();
    }

    private AccountRepository initAccountRepository() {
        return (configuration.isProdEnvironment()) ?
                new OracleAccountRepository() :
                new InMemoryAccountRepository();
    }

    private Map<String, String> convertToMap(String[] args) {
        return Arrays
                .stream(args)
                .map(arg -> arg.split("="))
                .collect(toMap(arg -> arg[0], arg -> arg[1]));
    }
}
