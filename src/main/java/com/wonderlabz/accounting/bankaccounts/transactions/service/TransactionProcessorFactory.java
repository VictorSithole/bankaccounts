package com.wonderlabz.accounting.bankaccounts.transactions.service;

import com.wonderlabz.accounting.bankaccounts.enums.TransactionType;
import com.wonderlabz.accounting.bankaccounts.transactions.processing.DepositProcessor;
import com.wonderlabz.accounting.bankaccounts.transactions.processing.SendMoneyProcessor;
import com.wonderlabz.accounting.bankaccounts.transactions.processing.WithdrawalProcessor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@Transactional(propagation = Propagation.REQUIRED)
public class TransactionProcessorFactory {

  public static TransactionStrategy getTransactionType ( int transactionTypeId) {

    if (transactionTypeId == TransactionType.SEND_MONEY.getTransactionTypeId()) {
      return new SendMoneyProcessor();
    } else if (transactionTypeId == TransactionType.WITHDRAW.getTransactionTypeId()) {
      return new WithdrawalProcessor();
    } else if (transactionTypeId == TransactionType.DEPOSIT.getTransactionTypeId()) {
      return new DepositProcessor();
  }
    else {
      throw new IllegalArgumentException("Unknown TransactionType with ID: " + transactionTypeId);
    }

  }
}
