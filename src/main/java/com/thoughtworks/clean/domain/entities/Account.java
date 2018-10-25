package com.thoughtworks.clean.domain.entities;

import java.math.BigDecimal;

public class Account {
    private final Integer number;
    private final BigDecimal quantidade;

    public Account(Integer number, BigDecimal quantidade) {
        this.number = number;
        this.quantidade = quantidade;
    }

    public Integer getNumber() {
        return number;
    }

    public BigDecimal getAmount() {
        return quantidade;
    }

    public Account debit(BigDecimal total) {
        return new Account(number, quantidade.subtract(total));
    }

    public Account credit(BigDecimal total) {
        return new Account(number, quantidade.add(total));
    }
}
