package com.wonderlabz.accounting.bankaccounts.transactions.service;
import com.wonderlabz.accounting.bankaccounts.enums.TransactionType;
import com.wonderlabz.accounting.bankaccounts.transactions.dto.ViewTransactionDto;

import java.util.List;

public interface TransactionService {

  List<ViewTransactionDto> getTransactionHistoryByType(TransactionType transactionType, Long customerId);
  List<ViewTransactionDto> getAllTransactions(Long customerId );

}
