package com.wonderlabz.accounting.bankaccounts.customer.controller;

import com.wonderlabz.accounting.bankaccounts.customer.dto.CreateCustomerDto;
import com.wonderlabz.accounting.bankaccounts.customer.model.Customer;
import com.wonderlabz.accounting.bankaccounts.customer.service.CustomerService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Objects;

@Slf4j
@RestController
public class CustomerController {

  private final CustomerService customerService;
  public CustomerController(CustomerService customerService) {
    this.customerService = customerService;
  }

  @SneakyThrows
  @PostMapping(value = "v1/customers/create")

  public ResponseEntity<Customer> createCustomer(@RequestBody @Validated CreateCustomerDto createCustomerDto) throws IOException {

    log.info("New Registration : {} ", createCustomerDto);
    Customer customer = customerService.getCustomerByIdNumber(createCustomerDto.getCustomerIdNumber());
    if (Objects.isNull(customer)) {
      final Customer cust = customerService.createCustomer(createCustomerDto);
      return new ResponseEntity<>( cust, HttpStatus.CREATED);
    }else{
      throw new IllegalArgumentException("There is a customer with ID Number "+ createCustomerDto.getCustomerIdNumber());
    }

  }
}
