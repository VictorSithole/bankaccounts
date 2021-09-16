package com.wonderlabz.accounting.bankaccounts.customer.dto;

import com.wonderlabz.accounting.bankaccounts.account.model.Account;
import com.wonderlabz.accounting.bankaccounts.common.BaseDto;
import com.wonderlabz.accounting.bankaccounts.enums.Gender;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Data
public class ViewCustomerDetailsDto extends BaseDto implements Serializable {

  private String customerName;
  private String customerIdNumber;
  private String surname;
  private String address;
  private String phoneNumber;
  private Gender gender;
  private String email;
  private LocalDate dateOfBirth;
  private List<Account> accountList;
}
