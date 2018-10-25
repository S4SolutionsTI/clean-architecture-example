package com.thoughtworks.clean.domain.entities;

import java.math.BigDecimal;
import java.util.Random;

public class AccountTestBuilder {

    // defaults
    private int number = new Random().nextInt();
    private BigDecimal amount = BigDecimal.TEN;

    public Account build() {
        return new Account(number, amount);
    }

    public static AccountTestBuilder anAccount() {
        return new AccountTestBuilder();
    }

    public AccountTestBuilder withAmount(int amount) {
        this.amount = new BigDecimal(amount);
        return this;
    }
}
