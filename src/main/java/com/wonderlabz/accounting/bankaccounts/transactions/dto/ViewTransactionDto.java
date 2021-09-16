package com.wonderlabz.accounting.bankaccounts.transactions.dto;

import com.wonderlabz.accounting.bankaccounts.account.model.Account;
import com.wonderlabz.accounting.bankaccounts.customer.model.Customer;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class ViewTransactionDto extends CreateTransactionDto implements Serializable {

  private Account account;
  private Account destinationAccount;
  private Customer customer;
  private LocalDateTime transactionDateTime;
  private Long transactionId;

}
