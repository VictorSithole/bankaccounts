package com.wonderlabz.accounting.bankaccounts.account.controller;

import com.wonderlabz.accounting.bankaccounts.account.dto.CreateAccountDto;
import com.wonderlabz.accounting.bankaccounts.account.dto.ViewAccountDto;
import com.wonderlabz.accounting.bankaccounts.account.service.AccountService;
import com.wonderlabz.accounting.bankaccounts.customer.model.Customer;
import com.wonderlabz.accounting.bankaccounts.customer.service.CustomerService;
import com.wonderlabz.accounting.bankaccounts.enums.AccountType;
import com.wonderlabz.accounting.bankaccounts.enums.Gender;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AccountsControllerTest {

    @Mock
    private AccountService mockAccountService;
    @Mock
    private CustomerService mockCustomerService;

    private AccountsController accountsControllerUnderTest;

    @Before
    public void setUp() {
        accountsControllerUnderTest = new AccountsController(mockAccountService, mockCustomerService);
    }

    @Test
    public void testOpenAccount() throws Exception {
        // Setup
        final CreateAccountDto accountDto = new CreateAccountDto();
        accountDto.setAccountType(AccountType.CURRENT);
        accountDto.setAccountNumber("accountNumber");
        accountDto.setCustomerIdNumber("customerIdNumber");
        accountDto.setCurrency("currency");
        accountDto.setAccountBalance(0.0);
        accountDto.setCreationDate(LocalDate.of(2020, 1, 1));

        final ViewAccountDto viewAccountDto = new ViewAccountDto();
        viewAccountDto.setAccountType(AccountType.CURRENT);
        viewAccountDto.setAccountNumber("accountNumber");
        viewAccountDto.setCustomerIdNumber("customerIdNumber");
        viewAccountDto.setCustomerName("customerName");
        viewAccountDto.setSurname("surname");
        viewAccountDto.setCurrency("currency");
        viewAccountDto.setAccountBalance(0.0);
        viewAccountDto.setCreationDate(LocalDate.of(2020, 1, 1));
        final ResponseEntity<ViewAccountDto> expectedResult = new ResponseEntity<>(viewAccountDto, HttpStatus.CREATED);

        // Configure CustomerService.getCustomerByIdNumber(...).
        final Customer customer = new Customer();
        customer.setCustomerName("customerName");
        customer.setCustomerIdNumber("customerIdNumber");
        customer.setSurname("surname");
        customer.setAddress("address");
        customer.setPhoneNumber("phoneNumber");
        customer.setGender(Gender.MALE);
        customer.setEmail("email");
        customer.setDateOfBirth(LocalDate.of(2020, 1, 1));
        when(mockCustomerService.getCustomerByIdNumber("customerIdNumber")).thenReturn(customer);

        // Configure AccountService.openAccount(...).
        final ViewAccountDto viewAccountDto1 = new ViewAccountDto();
        viewAccountDto1.setAccountType(AccountType.CURRENT);
        viewAccountDto1.setAccountNumber("accountNumber");
        viewAccountDto1.setCustomerIdNumber("customerIdNumber");
        viewAccountDto1.setCustomerName("customerName");
        viewAccountDto1.setSurname("surname");
        viewAccountDto1.setCurrency("currency");
        viewAccountDto1.setAccountBalance(0.0);
        viewAccountDto1.setCreationDate(LocalDate.of(2020, 1, 1));
        //when(mockAccountService.openAccount(new CreateAccountDto())).thenReturn(viewAccountDto1);

        // Run the test
        final ResponseEntity<ViewAccountDto> result = accountsControllerUnderTest.openAccount(accountDto);

        // Verify the results
        assertThat(result.getStatusCode()).isEqualTo(expectedResult.getStatusCode());
    }

    @Test
    public void testOpenAccount_ThrowsIOException() {
        // Setup
        final CreateAccountDto accountDto = new CreateAccountDto();
        accountDto.setAccountType(null);
        accountDto.setAccountNumber("accountNumber");
        accountDto.setCustomerIdNumber("customerIdNumber");
        accountDto.setCurrency("currency");
        accountDto.setAccountBalance(0.0);
        accountDto.setCreationDate(LocalDate.of(2020, 1, 1));

        // Configure CustomerService.getCustomerByIdNumber(...).
        final Customer customer = new Customer();
        customer.setCustomerName("customerName");
        customer.setCustomerIdNumber("customerIdNumber");
        customer.setSurname("surname");
        customer.setAddress("address");
        customer.setPhoneNumber("phoneNumber");
        customer.setGender(Gender.MALE);
        customer.setEmail("email");
        customer.setDateOfBirth(LocalDate.of(2020, 1, 1));

        // Configure AccountService.openAccount(...).
        final ViewAccountDto viewAccountDto = new ViewAccountDto();
        viewAccountDto.setAccountType(AccountType.CURRENT);
        viewAccountDto.setAccountNumber("accountNumber");
        viewAccountDto.setCustomerIdNumber("customerIdNumber");
        viewAccountDto.setCustomerName("customerName");
        viewAccountDto.setSurname("surname");
        viewAccountDto.setCurrency("currency");
        viewAccountDto.setAccountBalance(0.0);
        viewAccountDto.setCreationDate(LocalDate.of(2020, 1, 1));
        // Run the test
        Assertions.assertThrows(NullPointerException.class, () -> accountsControllerUnderTest.openAccount(null));
    }
}
