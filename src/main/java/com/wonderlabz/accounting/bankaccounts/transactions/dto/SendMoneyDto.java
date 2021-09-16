package com.wonderlabz.accounting.bankaccounts.transactions.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class SendMoneyDto extends CreateTransactionDto implements Serializable {
  private String destinationAccount;
}
