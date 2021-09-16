package com.wonderlabz.accounting.bankaccounts.transactions.dto;

import com.wonderlabz.accounting.bankaccounts.common.BaseDto;
import com.wonderlabz.accounting.bankaccounts.enums.TransactionType;
import lombok.Data;

import java.io.Serializable;

@Data
public class CreateTransactionDto extends BaseDto implements Serializable {

  private TransactionType transactionType;
  private double amount;
  private String accountNumber;
  private Long customerId;
  private String destinationAccountNumber;

}
