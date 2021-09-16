package com.wonderlabz.accounting.bankaccounts.transactions.service;

import com.wonderlabz.accounting.bankaccounts.transactions.dto.CreateTransactionDto;
import com.wonderlabz.accounting.bankaccounts.transactions.dto.ViewTransactionDto;

public interface TransactionStrategy {

    ViewTransactionDto createTransaction(CreateTransactionDto transactionDto);
}
