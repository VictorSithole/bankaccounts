package com.wonderlabz.accounting.bankaccounts.transactions.controller;

import com.wonderlabz.accounting.bankaccounts.account.model.Account;
import com.wonderlabz.accounting.bankaccounts.customer.model.Customer;
import com.wonderlabz.accounting.bankaccounts.customer.service.CustomerService;
import com.wonderlabz.accounting.bankaccounts.enums.AccountType;
import com.wonderlabz.accounting.bankaccounts.enums.Gender;
import com.wonderlabz.accounting.bankaccounts.enums.TransactionType;
import com.wonderlabz.accounting.bankaccounts.transactions.dto.CreateTransactionDto;
import com.wonderlabz.accounting.bankaccounts.transactions.dto.ViewTransactionDto;
import com.wonderlabz.accounting.bankaccounts.transactions.service.TransactionService;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TransactionControllerTest {

    @Mock
    private TransactionService mockTransactionService;
    @Mock
    private CustomerService mockCustomerService;

    private TransactionController transactionControllerUnderTest;

    @Before
    public void setUp() {
        transactionControllerUnderTest = new TransactionController(mockTransactionService, mockCustomerService);
    }

    @Test
    public void testCreateTransaction() throws Exception {
        // Setup
        final CreateTransactionDto createTransactionDto = new CreateTransactionDto();
        createTransactionDto.setTransactionType(TransactionType.DEPOSIT);
        createTransactionDto.setAmount(0.0);
        createTransactionDto.setAccountNumber("accountNumber");
        createTransactionDto.setCustomerId(0L);
        createTransactionDto.setDestinationAccountNumber("destinationAccountNumber");

        final ViewTransactionDto viewTransactionDto = new ViewTransactionDto();
        final Account account = new Account();
        account.setAccountType(AccountType.CURRENT);
        account.setAccountNumber("accountNumber");
        final Customer customer = new Customer();
        customer.setCustomerName("customerName");
        customer.setCustomerIdNumber("customerIdNumber");
        customer.setSurname("surname");
        customer.setAddress("address");
        customer.setPhoneNumber("phoneNumber");
        customer.setGender(Gender.MALE);
        customer.setEmail("email");
        customer.setDateOfBirth(LocalDate.of(2020, 1, 1));
        account.setCustomer(customer);
        account.setCustomerIdNumber("customerIdNumber");
        account.setCurrency("currency");
        account.setAccountBalance(0.0);
        account.setCreationDate(LocalDate.of(2020, 1, 1));
        viewTransactionDto.setAccount(account);
        final Account destinationAccount = new Account();
        destinationAccount.setAccountType(AccountType.CURRENT);
        destinationAccount.setAccountNumber("accountNumber");
        final Customer customer1 = new Customer();
        customer1.setCustomerName("customerName");
        customer1.setCustomerIdNumber("customerIdNumber");
        customer1.setSurname("surname");
        customer1.setAddress("address");
        customer1.setPhoneNumber("phoneNumber");
        customer1.setGender(Gender.MALE);
        customer1.setEmail("email");
        customer1.setDateOfBirth(LocalDate.of(2020, 1, 1));
        destinationAccount.setCustomer(customer1);
        destinationAccount.setCustomerIdNumber("customerIdNumber");
        destinationAccount.setCurrency("currency");
        destinationAccount.setAccountBalance(0.0);
        destinationAccount.setCreationDate(LocalDate.of(2020, 1, 1));
        viewTransactionDto.setDestinationAccount(destinationAccount);
        final Customer customer2 = new Customer();
        customer2.setCustomerName("customerName");
        customer2.setCustomerIdNumber("customerIdNumber");
        customer2.setSurname("surname");
        customer2.setAddress("address");
        customer2.setPhoneNumber("phoneNumber");
        customer2.setGender(Gender.MALE);
        customer2.setEmail("email");
        customer2.setDateOfBirth(LocalDate.of(2020, 1, 1));
        viewTransactionDto.setCustomer(customer2);
        viewTransactionDto.setTransactionDateTime(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        viewTransactionDto.setTransactionId(0L);
        viewTransactionDto.setTransactionType(TransactionType.DEPOSIT);
        final ResponseEntity<ViewTransactionDto> expectedResult = new ResponseEntity<>(viewTransactionDto, HttpStatus.OK);

        // Configure CustomerService.getCustomerById(...).
        final Customer customer3 = new Customer();
        customer3.setCustomerName("customerName");
        customer3.setCustomerIdNumber("customerIdNumber");
        customer3.setSurname("surname");
        customer3.setAddress("address");
        customer3.setPhoneNumber("phoneNumber");
        customer3.setGender(Gender.MALE);
        customer3.setEmail("email");
        customer3.setDateOfBirth(LocalDate.of(2020, 1, 1));
        when(mockCustomerService.getCustomerById(0L)).thenReturn(customer3);

        // Run the test
        Assertions.assertThrows(NullPointerException.class, () -> transactionControllerUnderTest.createTransaction(createTransactionDto,1));
    }

    @Test
    public void testCreateTransaction_ThrowsIOException() {
        // Setup
        final CreateTransactionDto createTransactionDto = new CreateTransactionDto();
        createTransactionDto.setTransactionType(TransactionType.DEPOSIT);
        createTransactionDto.setAmount(0.0);
        createTransactionDto.setAccountNumber("accountNumber");
        createTransactionDto.setCustomerId(0L);
        createTransactionDto.setDestinationAccountNumber("destinationAccountNumber");

        // Configure CustomerService.getCustomerById(...).
        final Customer customer = new Customer();
        customer.setCustomerName("customerName");
        customer.setCustomerIdNumber("customerIdNumber");
        customer.setSurname("surname");
        customer.setAddress("address");
        customer.setPhoneNumber("phoneNumber");
        customer.setGender(Gender.MALE);
        customer.setEmail("email");
        customer.setDateOfBirth(LocalDate.of(2020, 1, 1));
        when(mockCustomerService.getCustomerById(0L)).thenReturn(customer);

        // Run the test
        assertThatThrownBy(() -> transactionControllerUnderTest.createTransaction(createTransactionDto, 1)).isInstanceOf(NullPointerException.class);
    }

    @Test
    public void testGetTransactionHistoryByType() throws Exception {
        // Setup
        final ViewTransactionDto viewTransactionDto = new ViewTransactionDto();
        final Account account = new Account();
        account.setAccountType(AccountType.CURRENT);
        account.setAccountNumber("accountNumber");
        final Customer customer = new Customer();
        customer.setCustomerName("customerName");
        customer.setCustomerIdNumber("customerIdNumber");
        customer.setSurname("surname");
        customer.setAddress("address");
        customer.setPhoneNumber("phoneNumber");
        customer.setGender(Gender.MALE);
        customer.setEmail("email");
        customer.setDateOfBirth(LocalDate.of(2020, 1, 1));
        account.setCustomer(customer);
        account.setCustomerIdNumber("customerIdNumber");
        account.setCurrency("currency");
        account.setAccountBalance(0.0);
        account.setCreationDate(LocalDate.of(2020, 1, 1));
        viewTransactionDto.setAccount(account);
        final Account destinationAccount = new Account();
        destinationAccount.setAccountType(AccountType.CURRENT);
        destinationAccount.setAccountNumber("accountNumber");
        final Customer customer1 = new Customer();
        customer1.setCustomerName("customerName");
        customer1.setCustomerIdNumber("customerIdNumber");
        customer1.setSurname("surname");
        customer1.setAddress("address");
        customer1.setPhoneNumber("phoneNumber");
        customer1.setGender(Gender.MALE);
        customer1.setEmail("email");
        customer1.setDateOfBirth(LocalDate.of(2020, 1, 1));
        destinationAccount.setCustomer(customer1);
        destinationAccount.setCustomerIdNumber("customerIdNumber");
        destinationAccount.setCurrency("currency");
        destinationAccount.setAccountBalance(0.0);
        destinationAccount.setCreationDate(LocalDate.of(2020, 1, 1));
        viewTransactionDto.setDestinationAccount(destinationAccount);
        final Customer customer2 = new Customer();
        customer2.setCustomerName("customerName");
        customer2.setCustomerIdNumber("customerIdNumber");
        customer2.setSurname("surname");
        customer2.setAddress("address");
        customer2.setPhoneNumber("phoneNumber");
        customer2.setGender(Gender.MALE);
        customer2.setEmail("email");
        customer2.setDateOfBirth(LocalDate.of(2020, 1, 1));
        viewTransactionDto.setCustomer(customer2);
        viewTransactionDto.setTransactionDateTime(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        viewTransactionDto.setTransactionId(0L);
        final ResponseEntity<List<ViewTransactionDto>> expectedResult = new ResponseEntity<>(Arrays.asList(viewTransactionDto), HttpStatus.OK);

        // Configure TransactionService.getTransactionHistoryByType(...).
        final ViewTransactionDto viewTransactionDto1 = new ViewTransactionDto();
        final Account account1 = new Account();
        account1.setAccountType(AccountType.CURRENT);
        account1.setAccountNumber("accountNumber");
        final Customer customer3 = new Customer();
        customer3.setCustomerName("customerName");
        customer3.setCustomerIdNumber("customerIdNumber");
        customer3.setSurname("surname");
        customer3.setAddress("address");
        customer3.setPhoneNumber("phoneNumber");
        customer3.setGender(Gender.MALE);
        customer3.setEmail("email");
        customer3.setDateOfBirth(LocalDate.of(2020, 1, 1));
        account1.setCustomer(customer3);
        account1.setCustomerIdNumber("customerIdNumber");
        account1.setCurrency("currency");
        account1.setAccountBalance(0.0);
        account1.setCreationDate(LocalDate.of(2020, 1, 1));
        viewTransactionDto1.setAccount(account1);
        final Account destinationAccount1 = new Account();
        destinationAccount1.setAccountType(AccountType.CURRENT);
        destinationAccount1.setAccountNumber("accountNumber");
        final Customer customer4 = new Customer();
        customer4.setCustomerName("customerName");
        customer4.setCustomerIdNumber("customerIdNumber");
        customer4.setSurname("surname");
        customer4.setAddress("address");
        customer4.setPhoneNumber("phoneNumber");
        customer4.setGender(Gender.MALE);
        customer4.setEmail("email");
        customer4.setDateOfBirth(LocalDate.of(2020, 1, 1));
        destinationAccount1.setCustomer(customer4);
        destinationAccount1.setCustomerIdNumber("customerIdNumber");
        destinationAccount1.setCurrency("currency");
        destinationAccount1.setAccountBalance(0.0);
        destinationAccount1.setCreationDate(LocalDate.of(2020, 1, 1));
        viewTransactionDto1.setDestinationAccount(destinationAccount1);
        final Customer customer5 = new Customer();
        customer5.setCustomerName("customerName");
        customer5.setCustomerIdNumber("customerIdNumber");
        customer5.setSurname("surname");
        customer5.setAddress("address");
        customer5.setPhoneNumber("phoneNumber");
        customer5.setGender(Gender.MALE);
        customer5.setEmail("email");
        customer5.setDateOfBirth(LocalDate.of(2020, 1, 1));
        viewTransactionDto1.setCustomer(customer5);
        viewTransactionDto1.setTransactionDateTime(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        viewTransactionDto1.setTransactionId(0L);
        final List<ViewTransactionDto> viewTransactionDtos = Arrays.asList(viewTransactionDto1);

        // Verify the results
        assertThatThrownBy(() -> transactionControllerUnderTest.getTransactionHistoryByType(0L, 0)).isInstanceOf(NullPointerException.class);
    }


    @Test
    public void testGetTransactionHistoryByType_ThrowsIOException() {
        // Setup

        // Configure TransactionService.getTransactionHistoryByType(...).
        final ViewTransactionDto viewTransactionDto = new ViewTransactionDto();
        final Account account = new Account();
        account.setAccountType(AccountType.CURRENT);
        account.setAccountNumber("accountNumber");
        final Customer customer = new Customer();
        customer.setCustomerName("customerName");
        customer.setCustomerIdNumber("customerIdNumber");
        customer.setSurname("surname");
        customer.setAddress("address");
        customer.setPhoneNumber("phoneNumber");
        customer.setGender(Gender.MALE);
        customer.setEmail("email");
        customer.setDateOfBirth(LocalDate.of(2020, 1, 1));
        account.setCustomer(customer);
        account.setCustomerIdNumber("customerIdNumber");
        account.setCurrency("currency");
        account.setAccountBalance(0.0);
        account.setCreationDate(LocalDate.of(2020, 1, 1));
        viewTransactionDto.setAccount(account);
        final Account destinationAccount = new Account();
        destinationAccount.setAccountType(AccountType.CURRENT);
        destinationAccount.setAccountNumber("accountNumber");
        final Customer customer1 = new Customer();
        customer1.setCustomerName("customerName");
        customer1.setCustomerIdNumber("customerIdNumber");
        customer1.setSurname("surname");
        customer1.setAddress("address");
        customer1.setPhoneNumber("phoneNumber");
        customer1.setGender(Gender.MALE);
        customer1.setEmail("email");
        customer1.setDateOfBirth(LocalDate.of(2020, 1, 1));
        destinationAccount.setCustomer(customer1);
        destinationAccount.setCustomerIdNumber("customerIdNumber");
        destinationAccount.setCurrency("currency");
        destinationAccount.setAccountBalance(0.0);
        destinationAccount.setCreationDate(LocalDate.of(2020, 1, 1));
        viewTransactionDto.setDestinationAccount(destinationAccount);
        final Customer customer2 = new Customer();
        customer2.setCustomerName("customerName");
        customer2.setCustomerIdNumber("customerIdNumber");
        customer2.setSurname("surname");
        customer2.setAddress("address");
        customer2.setPhoneNumber("phoneNumber");
        customer2.setGender(Gender.MALE);
        customer2.setEmail("email");
        customer2.setDateOfBirth(LocalDate.of(2020, 1, 1));
        viewTransactionDto.setCustomer(customer2);
        viewTransactionDto.setTransactionDateTime(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        viewTransactionDto.setTransactionId(0L);
        final List<ViewTransactionDto> viewTransactionDtos = Arrays.asList(viewTransactionDto);

        // Run the test
        assertThatThrownBy(() -> transactionControllerUnderTest.getTransactionHistoryByType(0L, 0)).isInstanceOf(NullPointerException.class);
    }

    @Test
    public void testGetAllTransactionHistory() throws Exception {
        // Setup
        final ViewTransactionDto viewTransactionDto = new ViewTransactionDto();
        final Account account = new Account();
        account.setAccountType(AccountType.CURRENT);
        account.setAccountNumber("accountNumber");
        final Customer customer = new Customer();
        customer.setCustomerName("customerName");
        customer.setCustomerIdNumber("customerIdNumber");
        customer.setSurname("surname");
        customer.setAddress("address");
        customer.setPhoneNumber("phoneNumber");
        customer.setGender(Gender.MALE);
        customer.setEmail("email");
        customer.setDateOfBirth(LocalDate.of(2020, 1, 1));
        account.setCustomer(customer);
        account.setCustomerIdNumber("customerIdNumber");
        account.setCurrency("currency");
        account.setAccountBalance(0.0);
        account.setCreationDate(LocalDate.of(2020, 1, 1));
        viewTransactionDto.setAccount(account);
        final Account destinationAccount = new Account();
        destinationAccount.setAccountType(AccountType.CURRENT);
        destinationAccount.setAccountNumber("accountNumber");
        final Customer customer1 = new Customer();
        customer1.setCustomerName("customerName");
        customer1.setCustomerIdNumber("customerIdNumber");
        customer1.setSurname("surname");
        customer1.setAddress("address");
        customer1.setPhoneNumber("phoneNumber");
        customer1.setGender(Gender.MALE);
        customer1.setEmail("email");
        customer1.setDateOfBirth(LocalDate.of(2020, 1, 1));
        destinationAccount.setCustomer(customer1);
        destinationAccount.setCustomerIdNumber("customerIdNumber");
        destinationAccount.setCurrency("currency");
        destinationAccount.setAccountBalance(0.0);
        destinationAccount.setCreationDate(LocalDate.of(2020, 1, 1));
        viewTransactionDto.setDestinationAccount(destinationAccount);
        final Customer customer2 = new Customer();
        customer2.setCustomerName("customerName");
        customer2.setCustomerIdNumber("customerIdNumber");
        customer2.setSurname("surname");
        customer2.setAddress("address");
        customer2.setPhoneNumber("phoneNumber");
        customer2.setGender(Gender.MALE);
        customer2.setEmail("email");
        customer2.setDateOfBirth(LocalDate.of(2020, 1, 1));
        viewTransactionDto.setCustomer(customer2);
        viewTransactionDto.setTransactionDateTime(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        viewTransactionDto.setTransactionId(0L);
        final ResponseEntity<List<ViewTransactionDto>> expectedResult = new ResponseEntity<>(Arrays.asList(viewTransactionDto), HttpStatus.OK);

        // Configure TransactionService.getAllTransactions(...).
        final ViewTransactionDto viewTransactionDto1 = new ViewTransactionDto();
        final Account account1 = new Account();
        account1.setAccountType(AccountType.CURRENT);
        account1.setAccountNumber("accountNumber");
        final Customer customer3 = new Customer();
        customer3.setCustomerName("customerName");
        customer3.setCustomerIdNumber("customerIdNumber");
        customer3.setSurname("surname");
        customer3.setAddress("address");
        customer3.setPhoneNumber("phoneNumber");
        customer3.setGender(Gender.MALE);
        customer3.setEmail("email");
        customer3.setDateOfBirth(LocalDate.of(2020, 1, 1));
        account1.setCustomer(customer3);
        account1.setCustomerIdNumber("customerIdNumber");
        account1.setCurrency("currency");
        account1.setAccountBalance(0.0);
        account1.setCreationDate(LocalDate.of(2020, 1, 1));
        viewTransactionDto1.setAccount(account1);
        final Account destinationAccount1 = new Account();
        destinationAccount1.setAccountType(AccountType.CURRENT);
        destinationAccount1.setAccountNumber("accountNumber");
        final Customer customer4 = new Customer();
        customer4.setCustomerName("customerName");
        customer4.setCustomerIdNumber("customerIdNumber");
        customer4.setSurname("surname");
        customer4.setAddress("address");
        customer4.setPhoneNumber("phoneNumber");
        customer4.setGender(Gender.MALE);
        customer4.setEmail("email");
        customer4.setDateOfBirth(LocalDate.of(2020, 1, 1));
        destinationAccount1.setCustomer(customer4);
        destinationAccount1.setCustomerIdNumber("customerIdNumber");
        destinationAccount1.setCurrency("currency");
        destinationAccount1.setAccountBalance(0.0);
        destinationAccount1.setCreationDate(LocalDate.of(2020, 1, 1));
        viewTransactionDto1.setDestinationAccount(destinationAccount1);
        final Customer customer5 = new Customer();
        customer5.setCustomerName("customerName");
        customer5.setCustomerIdNumber("customerIdNumber");
        customer5.setSurname("surname");
        customer5.setAddress("address");
        customer5.setPhoneNumber("phoneNumber");
        customer5.setGender(Gender.MALE);
        customer5.setEmail("email");
        customer5.setDateOfBirth(LocalDate.of(2020, 1, 1));
        viewTransactionDto1.setCustomer(customer5);
        viewTransactionDto1.setTransactionDateTime(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        viewTransactionDto1.setTransactionId(0L);
        final List<ViewTransactionDto> viewTransactionDtos = Arrays.asList(viewTransactionDto1);

        // Run the test
        assertThatThrownBy(() -> transactionControllerUnderTest.getAllTransactionHistory(0L)).isInstanceOf(NullPointerException.class);
    }



    @Test
    public void testGetAllTransactionHistory_ThrowsIOException() {
        // Setup

        // Configure TransactionService.getAllTransactions(...).
        final ViewTransactionDto viewTransactionDto = new ViewTransactionDto();
        final Account account = new Account();
        account.setAccountType(AccountType.CURRENT);
        account.setAccountNumber("accountNumber");
        final Customer customer = new Customer();
        customer.setCustomerName("customerName");
        customer.setCustomerIdNumber("customerIdNumber");
        customer.setSurname("surname");
        customer.setAddress("address");
        customer.setPhoneNumber("phoneNumber");
        customer.setGender(Gender.MALE);
        customer.setEmail("email");
        customer.setDateOfBirth(LocalDate.of(2020, 1, 1));
        account.setCustomer(customer);
        account.setCustomerIdNumber("customerIdNumber");
        account.setCurrency("currency");
        account.setAccountBalance(0.0);
        account.setCreationDate(LocalDate.of(2020, 1, 1));
        viewTransactionDto.setAccount(account);
        final Account destinationAccount = new Account();
        destinationAccount.setAccountType(AccountType.CURRENT);
        destinationAccount.setAccountNumber("accountNumber");
        final Customer customer1 = new Customer();
        customer1.setCustomerName("customerName");
        customer1.setCustomerIdNumber("customerIdNumber");
        customer1.setSurname("surname");
        customer1.setAddress("address");
        customer1.setPhoneNumber("phoneNumber");
        customer1.setGender(Gender.MALE);
        customer1.setEmail("email");
        customer1.setDateOfBirth(LocalDate.of(2020, 1, 1));
        destinationAccount.setCustomer(customer1);
        destinationAccount.setCustomerIdNumber("customerIdNumber");
        destinationAccount.setCurrency("currency");
        destinationAccount.setAccountBalance(0.0);
        destinationAccount.setCreationDate(LocalDate.of(2020, 1, 1));
        viewTransactionDto.setDestinationAccount(destinationAccount);
        final Customer customer2 = new Customer();
        customer2.setCustomerName("customerName");
        customer2.setCustomerIdNumber("customerIdNumber");
        customer2.setSurname("surname");
        customer2.setAddress("address");
        customer2.setPhoneNumber("phoneNumber");
        customer2.setGender(Gender.MALE);
        customer2.setEmail("email");
        customer2.setDateOfBirth(LocalDate.of(2020, 1, 1));
        viewTransactionDto.setCustomer(customer2);
        viewTransactionDto.setTransactionDateTime(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        viewTransactionDto.setTransactionId(0L);
        final List<ViewTransactionDto> viewTransactionDtos = Arrays.asList(viewTransactionDto);

        // Run the test
        assertThatThrownBy(() -> transactionControllerUnderTest.getAllTransactionHistory(0L)).isInstanceOf(NullPointerException.class);
    }
}
