package com.wonderlabz.accounting.bankaccounts.account.repository;

import com.wonderlabz.accounting.bankaccounts.account.model.Account;
import com.wonderlabz.accounting.bankaccounts.customer.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account,Long> {

  Account findByAccountNumber(String accountId);
  Optional<Account> findById(Long Id);
  List<Account> findByCustomer(Customer customer);
}
