package com.wonderlabz.accounting.bankaccounts.util;

import com.wonderlabz.accounting.bankaccounts.account.model.Account;
import com.wonderlabz.accounting.bankaccounts.account.model.CurrentAccount;
import com.wonderlabz.accounting.bankaccounts.account.model.SavingsAccount;
import com.wonderlabz.accounting.bankaccounts.account.repository.AccountRepository;
import com.wonderlabz.accounting.bankaccounts.customer.model.Customer;
import com.wonderlabz.accounting.bankaccounts.customer.repository.CustomerServiceRepository;
import com.wonderlabz.accounting.bankaccounts.enums.AccountType;
import com.wonderlabz.accounting.bankaccounts.enums.TransactionType;
import com.wonderlabz.accounting.bankaccounts.exceptions.BadRequestDataException;
import com.wonderlabz.accounting.bankaccounts.transactions.dto.CreateTransactionDto;
import com.wonderlabz.accounting.bankaccounts.transactions.dto.ViewTransactionDto;
import com.wonderlabz.accounting.bankaccounts.transactions.model.Transaction;
import com.wonderlabz.accounting.bankaccounts.transactions.repository.TransactionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

@Service
@Slf4j
@Transactional(propagation = Propagation.REQUIRED)
public class CheckBankDetailsUtil {

  //To try to agree to Dry principles
  private static AccountRepository accountRepository ;
  private static CustomerServiceRepository customerServiceRepository ;
  private static TransactionRepository transactionRepository;

  public CheckBankDetailsUtil(AccountRepository accountRepository, CustomerServiceRepository customerServiceRepository, TransactionRepository transactionRepository){
     CheckBankDetailsUtil.accountRepository = accountRepository;
     CheckBankDetailsUtil.customerServiceRepository = customerServiceRepository;
     CheckBankDetailsUtil.transactionRepository = transactionRepository;

  }

  public static Account checkAccountInfo(String accountNumber){

    Account account = accountRepository.findByAccountNumber(accountNumber);
    if(Objects.isNull(account)){
      throw new BadRequestDataException("Account with this Account ID does not exist "+ accountNumber);
    }

    return account;
  }

  public static Customer checkCustomer(Long customerId){

    Optional<Customer> customer = customerServiceRepository.findById(customerId);
    if(Objects.isNull(customer)){
      throw new IllegalArgumentException("Customer with this CustomerIDNumber does not exist "+ customerId);
    }

    return customer.get();
  }

  public static Customer checkCustomerByIdNumber(String customerIdNumber){

    Customer customer = customerServiceRepository.findCustomerByCustomerIdNumber(customerIdNumber);
    if(Objects.isNull(customer)){
      throw new BadRequestDataException("Customer with this CustomerIDNumber does not exist "+ customerIdNumber);
    }

    return customer;
  }

  public static boolean checkWithdrawalLimit(String accountNumber, double transactionAmount){

    Account sourceAccount = CheckBankDetailsUtil.checkAccountInfo(accountNumber);

    double sourceAccountBalance = sourceAccount.getAccountBalance();
    double debitedAccountBalance = sourceAccountBalance - transactionAmount;
    if(sourceAccount.getAccountType()== AccountType.SAVINGS_ACCOUNT){
      if(debitedAccountBalance<= SavingsAccount.MIN_BALANCE){
        throw new IllegalArgumentException("Account balance not sufficient to transact this amount, the minimum amount for savings account is "+SavingsAccount.MIN_BALANCE);
      }
    }else {

      if (sourceAccount.getAccountBalance() -(CurrentAccount.OVERDRAFT)<= debitedAccountBalance) {

        throw new BadRequestDataException("Account balance not sufficient to transact this amount, the minimum overdraft amount for savings account is "+CurrentAccount.OVERDRAFT);
      }
    }

    return true;
  }

  public static ViewTransactionDto getViewTransactionDto(Transaction transaction, Account account) {
    ViewTransactionDto viewTransactionDto = new ViewTransactionDto();
    viewTransactionDto.setAccount(account);
    viewTransactionDto.setAccountNumber(transaction.getAccountNumber());
    viewTransactionDto.setCustomerId(transaction.getCustomerId());
    viewTransactionDto.setCustomer(CheckBankDetailsUtil.checkCustomer(transaction.getCustomerId()));

    if(transaction.getTransactionType()== TransactionType.SEND_MONEY){
      viewTransactionDto.setDestinationAccountNumber(transaction.getDestinationAccount());
      viewTransactionDto.setDestinationAccount(checkAccountInfo(transaction.getDestinationAccount()));
    }else{
      viewTransactionDto.setDestinationAccount(null);
    }

    viewTransactionDto.setAmount(transaction.getAmount());
    viewTransactionDto.setTransactionType(transaction.getTransactionType());
    viewTransactionDto.setTransactionDateTime(transaction.getTransactionDate());
    viewTransactionDto.setTransactionId(transaction.getId());

    return viewTransactionDto;
  }

   public static  Transaction getTransaction(CreateTransactionDto transactionDto, Account sourceAccount) {
    // create transaction if both accounts exist
    Transaction transaction = new Transaction();
    transaction.setTransactionType(transactionDto.getTransactionType());
    transaction.setTransactionDate(LocalDateTime.now());
    transaction.setCustomerId(transactionDto.getCustomerId());
    transaction.setAmount(transactionDto.getAmount());

    if(transaction.getTransactionType() == TransactionType.SEND_MONEY){
      transaction.setDestinationAccount(transaction.getDestinationAccount());
    }else{
       transaction.setDestinationAccount(null);
     }

    transaction.setAccountNumber(sourceAccount.getAccountNumber());
    return transaction;
  }

  public static  Transaction saveTransaction(Transaction transaction) {
    // create transaction if both accounts exist
    log.info("Register Transaction :{}", transaction);
    return transactionRepository.save(transaction);

  }

  public static  Account updateAccount(Account account) {
    // create transaction if both accounts exist
    log.info("Register Transaction :{}", account);
    return accountRepository.save(account);

  }


}
