package com.wonderlabz.accounting.bankaccounts.transactions.dto;

import com.wonderlabz.accounting.bankaccounts.account.model.Account;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class ViewSendMoneyDto extends ViewTransactionDto implements Serializable {
  private Account fromAccount;
  private Account destinationAccount;
  private LocalDateTime transactionDateTime;

}
