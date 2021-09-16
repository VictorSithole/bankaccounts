package com.wonderlabz.accounting.bankaccounts.transactions.processing;


import com.wonderlabz.accounting.bankaccounts.account.model.Account;
import com.wonderlabz.accounting.bankaccounts.account.repository.AccountRepository;
import com.wonderlabz.accounting.bankaccounts.transactions.dto.CreateTransactionDto;
import com.wonderlabz.accounting.bankaccounts.transactions.dto.ViewTransactionDto;
import com.wonderlabz.accounting.bankaccounts.transactions.model.Transaction;
import com.wonderlabz.accounting.bankaccounts.transactions.repository.TransactionRepository;
import com.wonderlabz.accounting.bankaccounts.transactions.service.TransactionStrategy;
import com.wonderlabz.accounting.bankaccounts.util.CheckBankDetailsUtil;

public class WithdrawalProcessor implements TransactionStrategy {

  private AccountRepository accountRepository;
  private TransactionRepository transactionRepository;

  public WithdrawalProcessor(){

  }
  public WithdrawalProcessor(AccountRepository accountRepository,TransactionRepository transactionRepository) {
    this.accountRepository = accountRepository;
    this.transactionRepository = transactionRepository;

  }

  @Override
  public ViewTransactionDto createTransaction(CreateTransactionDto transactionDto) {
    //check if account exists else throw exception
    Account sourceAccount = CheckBankDetailsUtil.checkAccountInfo(transactionDto.getAccountNumber());
    // create transaction
    Transaction transaction = CheckBankDetailsUtil.getTransaction(transactionDto, sourceAccount);

    //check for bank account withdrawal limits
    CheckBankDetailsUtil.checkWithdrawalLimit(sourceAccount.getAccountNumber(),transactionDto.getAmount());
    double sourceAccountBalance = sourceAccount.getAccountBalance();
    double debitedAccountBalance = sourceAccountBalance - transactionDto.getAmount();

    //we make it here we are ready to commit debit in the source account
    sourceAccount.setAccountBalance(debitedAccountBalance);
    CheckBankDetailsUtil.updateAccount(sourceAccount);
    //save transaction
    CheckBankDetailsUtil.saveTransaction(transaction);

    return CheckBankDetailsUtil.getViewTransactionDto(transaction,sourceAccount);
  }


}
