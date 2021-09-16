package com.wonderlabz.accounting.bankaccounts.transactions.repository;

import com.wonderlabz.accounting.bankaccounts.enums.TransactionType;
import com.wonderlabz.accounting.bankaccounts.transactions.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction,Long> {
  List<Transaction> findByTransactionTypeAndCustomerId(TransactionType transactionType, Long customerId);
  List<Transaction> findByCustomerId(Long customerId);

}
