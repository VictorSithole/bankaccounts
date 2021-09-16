package com.wonderlabz.accounting.bankaccounts.account.dto;

import com.wonderlabz.accounting.bankaccounts.common.BaseDto;
import com.wonderlabz.accounting.bankaccounts.enums.AccountType;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class CreateAccountDto extends BaseDto implements Serializable {

  private AccountType accountType;
  private String accountNumber;
  private String customerIdNumber;
  private String currency;
  private double  accountBalance;
  private LocalDate creationDate;
}
