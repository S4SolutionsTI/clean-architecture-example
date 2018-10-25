package com.thoughtworks.clean.domain.entities;

import org.junit.Test;

import java.math.BigDecimal;

import static com.thoughtworks.clean.domain.entities.AccountTestBuilder.anAccount;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class AccountTest {

    @Test
    public void shouldDebitAmount() {
        final Account account = anAccount().withAmount(10).build();
        final Account updatedAccount = account.debit(BigDecimal.TEN);
        assertThat(updatedAccount.getAmount(), is(BigDecimal.ZERO));
    }

    @Test
    public void shouldCreditAmount() {
        final Account account = anAccount().withAmount(10).build();
        final Account updatedAccount = account.credit(BigDecimal.TEN);
        assertThat(updatedAccount.getAmount(), is(new BigDecimal(20)));
    }
}