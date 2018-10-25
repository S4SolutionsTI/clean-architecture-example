package com.thoughtworks.clean.domain.entities;

import java.util.UUID;

public class TransferCheck {

    public static final TransferCheck NO_FUNDS = new TransferCheck(null, null);

    private final String id;
    private final Account fromAccount;
    private final Account toAccount;

    public TransferCheck(Account fromAccount, Account toAccount) {
        this.id = UUID.randomUUID().toString();
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
    }

    public String getId() {
        return id;
    }

    public Account getFromAccount() {
        return fromAccount;
    }

    public Account getToAccount() {
        return toAccount;
    }
}
