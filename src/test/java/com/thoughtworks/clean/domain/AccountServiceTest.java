package com.thoughtworks.clean.domain;

import com.thoughtworks.clean.delivery.data.StubbedAccountRepository;
import com.thoughtworks.clean.domain.entities.Account;
import com.thoughtworks.clean.domain.entities.TransferCheck;
import com.thoughtworks.clean.domain.usecases.AccountService;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static com.thoughtworks.clean.domain.entities.AccountMatchers.deniedDueToNoFunds;
import static com.thoughtworks.clean.domain.entities.AccountMatchers.fromAccountHasTotal;
import static com.thoughtworks.clean.domain.entities.AccountMatchers.toAccountHasTotal;
import static com.thoughtworks.clean.domain.entities.AccountTestBuilder.anAccount;
import static org.hamcrest.core.AllOf.allOf;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class AccountServiceTest {

    private AccountService accountService;
    private StubbedAccountRepository repository;

    @Before
    public void setUp() {
        repository = new StubbedAccountRepository();
        accountService = new AccountService(repository);
    }

    @Test
    public void shouldNotTransferWhenThereIsNotEnoughFunds() {
        final Account noFundsAccount = anAccount().withAmount(0).build();
        final Account anotherAccount = anAccount().build();
        final TransferCheck transferCheck = accountService.transfer(noFundsAccount, anotherAccount, BigDecimal.TEN);
        assertThat(transferCheck, is(deniedDueToNoFunds()));
        assertFalse(repository.isTransferSaved());
    }

    @Test
    public void shouldNotRegisterTransferWhenThereIsNoFunds() {
        final Account noFundsAccount = anAccount().withAmount(0).build();
        final Account anotherAccount = anAccount().build();
        accountService.transfer(noFundsAccount, anotherAccount, BigDecimal.TEN);
        assertFalse(repository.isTransferSaved());
    }

    @Test
    public void shouldTransferWhenThereIsEnoughFunds() {
        final Account anAccountWithFunds = anAccount().withAmount(10).build();
        final Account noFundsAccount = anAccount().withAmount(0).build();
        final TransferCheck transferCheck = accountService.transfer(anAccountWithFunds, noFundsAccount, BigDecimal.TEN);
        assertThat(transferCheck, allOf(
                fromAccountHasTotal(BigDecimal.ZERO),
                toAccountHasTotal(BigDecimal.TEN)));
    }

    @Test
    public void shouldRegisterTransferWhenThereIsAValidTransfer() {
        final Account anAccountWithFunds = anAccount().withAmount(10).build();
        final Account noFundsAccount = anAccount().withAmount(0).build();
        final TransferCheck transferCheck = accountService.transfer(anAccountWithFunds, noFundsAccount, BigDecimal.TEN);
        assertTrue(repository.isTransferSaved());
        assertThat(transferCheck, is(repository.getTransferCheckPassedToRepository()));
    }
}