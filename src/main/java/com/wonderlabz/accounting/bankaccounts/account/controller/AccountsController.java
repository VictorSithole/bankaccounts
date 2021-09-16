package com.wonderlabz.accounting.bankaccounts.account.controller;

import com.wonderlabz.accounting.bankaccounts.account.dto.CreateAccountDto;
import com.wonderlabz.accounting.bankaccounts.account.dto.ViewAccountDto;
import com.wonderlabz.accounting.bankaccounts.account.service.AccountService;
import com.wonderlabz.accounting.bankaccounts.customer.model.Customer;
import com.wonderlabz.accounting.bankaccounts.customer.service.CustomerService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Objects;

@Slf4j
@RestController
public class AccountsController {

  private final AccountService accountService;
  private final CustomerService customerService;
  public AccountsController(AccountService accountService, CustomerService customerService) {
    this.accountService = accountService;
    this.customerService = customerService;
  }

  @SneakyThrows
  @PostMapping(value = "/accounts/create")

  public ResponseEntity<ViewAccountDto> openAccount(@RequestBody CreateAccountDto accountDto) throws IOException {

    log.info("New Registration : {} ", accountDto);

    Customer customer = customerService.getCustomerByIdNumber(accountDto.getCustomerIdNumber());
    if (!Objects.isNull(customer)) {
      final ViewAccountDto createAccountDto = accountService.openAccount(accountDto);
      return new ResponseEntity<>( createAccountDto, HttpStatus.CREATED);
    }else{
      throw new IllegalArgumentException("There is no customer with ID Number "+ accountDto.getCustomerIdNumber());
    }

  }

}
