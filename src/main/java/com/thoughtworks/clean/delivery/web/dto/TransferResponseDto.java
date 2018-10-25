package com.thoughtworks.clean.delivery.web.dto;

import com.thoughtworks.clean.domain.entities.TransferCheck;

import java.util.HashMap;

public class TransferResponseDto extends HashMap<String, Object> {
    public static TransferResponseDto from(TransferCheck transferCheck) {
        if (transferCheck == TransferCheck.NO_FUNDS) {
            return new TransferResponseDto();
        }
        final TransferResponseDto result = new TransferResponseDto();
        result.put("id", transferCheck.getId());
        result.put("from_account_id", transferCheck.getFromAccount().getNumber());
        result.put("to_account_id", transferCheck.getToAccount().getNumber());
        return result;
    }
}
