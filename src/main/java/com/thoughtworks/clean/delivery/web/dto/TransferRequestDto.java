package com.thoughtworks.clean.delivery.web.dto;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import spark.Request;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class TransferRequestDto {

    private final int fromAccountId;
    private final int toAccountId;
    private final BigDecimal amount;

    public TransferRequestDto(int fromAccountId, int toAccountId, int amountInCents) {
        this.fromAccountId = fromAccountId;
        this.toAccountId = toAccountId;
        this.amount = toBigDecimal(amountInCents);
    }

    public static TransferRequestDto from(Request request) throws IOException {
        ObjectMapper objectMapper = request.attribute("objectMapper");
        final JsonNode myMap = objectMapper.readTree(request.body());
        final int fromAccountId = myMap.get("from_account_id").asInt();
        final int toAccountId = myMap.get("to_account_id").asInt();
        final int totalInCents = myMap.get("total_in_cents").asInt();
        return new TransferRequestDto(fromAccountId, toAccountId, totalInCents);
    }

    private BigDecimal toBigDecimal(Integer totalInCents) {
        return new BigDecimal(totalInCents).divide(BigDecimal.valueOf(100), RoundingMode.HALF_UP);
    }

    public int getFromAccountId() {
        return fromAccountId;
    }

    public int getToAccountId() {
        return toAccountId;
    }

    public BigDecimal getAmount() {
        return amount;
    }
}
