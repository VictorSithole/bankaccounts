package com.wonderlabz.accounting.bankaccounts.customer.repository;

import com.wonderlabz.accounting.bankaccounts.customer.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerServiceRepository extends JpaRepository<Customer,Long> {

  Customer findCustomerByCustomerIdNumber(String customerIdNumber);
  Optional<Customer> findById(Long Id);

}
