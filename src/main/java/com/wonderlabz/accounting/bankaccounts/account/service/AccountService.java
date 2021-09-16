package com.wonderlabz.accounting.bankaccounts.account.service;



import com.wonderlabz.accounting.bankaccounts.account.dto.CreateAccountDto;
import com.wonderlabz.accounting.bankaccounts.account.dto.ViewAccountDto;
import com.wonderlabz.accounting.bankaccounts.account.model.Account;
import com.wonderlabz.accounting.bankaccounts.customer.model.Customer;

import java.util.List;

public interface AccountService {

  ViewAccountDto openAccount(CreateAccountDto createAccountDto);
  List<Account> checkAccount(Customer customer);
  ViewAccountDto findAccountById(Long id);
  ViewAccountDto findByAccountNumber(String accountNumber);
  List<ViewAccountDto> findAllAccountsByNationalId(String nationalId);
  List<ViewAccountDto> findAllAccounts();
}
