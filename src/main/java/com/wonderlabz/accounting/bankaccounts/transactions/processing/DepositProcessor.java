package com.wonderlabz.accounting.bankaccounts.transactions.processing;

import com.wonderlabz.accounting.bankaccounts.account.model.Account;
import com.wonderlabz.accounting.bankaccounts.account.repository.AccountRepository;
import com.wonderlabz.accounting.bankaccounts.transactions.dto.CreateTransactionDto;
import com.wonderlabz.accounting.bankaccounts.transactions.dto.ViewTransactionDto;
import com.wonderlabz.accounting.bankaccounts.transactions.model.Transaction;
import com.wonderlabz.accounting.bankaccounts.transactions.repository.TransactionRepository;
import com.wonderlabz.accounting.bankaccounts.transactions.service.TransactionStrategy;
import com.wonderlabz.accounting.bankaccounts.util.CheckBankDetailsUtil;

import java.util.Objects;

public class DepositProcessor implements TransactionStrategy {

  private static TransactionRepository transactionRepository;
  private static AccountRepository accountRepository;


  public DepositProcessor(){

  }
   public DepositProcessor(TransactionRepository transactionRepository, AccountRepository accountRepository) {
    this.accountRepository = accountRepository;
    this.transactionRepository = transactionRepository;
  }

  @Override
  public ViewTransactionDto createTransaction(CreateTransactionDto transactionDto) {
    Account account = CheckBankDetailsUtil.checkAccountInfo(transactionDto.getAccountNumber());
    Transaction transaction = null;
    if (!Objects.isNull(account)) {
      transaction = CheckBankDetailsUtil.getTransaction(transactionDto, account);
      transaction= CheckBankDetailsUtil.saveTransaction(transaction);

    } else {
      throw new IllegalArgumentException("The Account provided is not valid:" + transactionDto.getAccountNumber() + " we can not complete the transaction");
    }
    //update/credit deposit account
    account.setAccountBalance(transactionDto.getAmount()+ account.getAccountBalance());
    CheckBankDetailsUtil.updateAccount(account);
    return CheckBankDetailsUtil.getViewTransactionDto(transaction, account);

  }

}