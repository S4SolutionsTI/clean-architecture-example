package com.thoughtworks.clean.domain.entities;

import org.hamcrest.FeatureMatcher;
import org.hamcrest.Matcher;

import java.math.BigDecimal;

import static org.hamcrest.core.IsEqual.equalTo;

public class AccountMatchers {

    public static TransferCheck deniedDueToNoFunds() {
        return TransferCheck.NO_FUNDS;
    }

    public static Matcher<TransferCheck> toAccountHasTotal(BigDecimal total) {
        return new FeatureMatcher<TransferCheck, BigDecimal>(equalTo(total), "fromAccount amount equals to", "") {
            @Override
            protected BigDecimal featureValueOf(TransferCheck actual) {
                return actual.getToAccount().getAmount();
            }
        };
    }

    public static Matcher<TransferCheck> fromAccountHasTotal(BigDecimal total) {
        return new FeatureMatcher<TransferCheck, BigDecimal>(equalTo(total), "toAccount amount equals to", "") {
            @Override
            protected BigDecimal featureValueOf(TransferCheck actual) {
                return actual.getFromAccount().getAmount();
            }
        };
    }
}
