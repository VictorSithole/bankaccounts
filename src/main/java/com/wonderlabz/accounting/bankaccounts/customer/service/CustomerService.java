package com.wonderlabz.accounting.bankaccounts.customer.service;

import com.wonderlabz.accounting.bankaccounts.customer.dto.CreateCustomerDto;
import com.wonderlabz.accounting.bankaccounts.customer.model.Customer;

public interface CustomerService {
  Customer getCustomerByIdNumber(String customerIdNumber);
  Customer createCustomer(CreateCustomerDto customerDto);
  Customer getCustomerById(Long Id);

}
