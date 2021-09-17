package com.wonderlabz.accounting.bankaccounts.customer.controller;

import com.wonderlabz.accounting.bankaccounts.customer.dto.CreateCustomerDto;
import com.wonderlabz.accounting.bankaccounts.customer.model.Customer;
import com.wonderlabz.accounting.bankaccounts.customer.service.CustomerService;
import com.wonderlabz.accounting.bankaccounts.enums.Gender;
import com.wonderlabz.accounting.bankaccounts.exceptions.BadRequestDataException;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CustomerControllerTest {

    @Mock
    private CustomerService mockCustomerService;

    private CustomerController customerControllerUnderTest;

    @Before
    public void setUp() {
        customerControllerUnderTest = new CustomerController(mockCustomerService);
    }

    @Test
    public void testCreateCustomer() throws NullPointerException {
        // Setup
        final CreateCustomerDto createCustomerDto = new CreateCustomerDto();
        createCustomerDto.setCustomerName("customerName");
        createCustomerDto.setCustomerIdNumber("customerIdNumber");
        createCustomerDto.setSurname("surname");
        createCustomerDto.setAddress("address");
        createCustomerDto.setPhoneNumber("phoneNumber");
        createCustomerDto.setGender(Gender.MALE);
        createCustomerDto.setEmail("email");
        createCustomerDto.setDateOfBirth(LocalDate.of(2020, 1, 1));

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

        // Configure CustomerService.createCustomer(...).
        final Customer customer1 = new Customer();
        customer1.setCustomerName("customerName");
        customer1.setCustomerIdNumber("customerIdNumber");
        customer1.setSurname("surname");
        customer1.setAddress("address");
        customer1.setPhoneNumber("phoneNumber");
        customer1.setGender(Gender.MALE);
        customer1.setEmail("email");
        customer1.setDateOfBirth(LocalDate.of(2020, 1, 1));
        Assertions.assertThrows(NullPointerException.class, () -> customerControllerUnderTest.createCustomer(null));
    }

    @Test
    public void testCreateCustomer_ThrowsBadRequestDataException() {
        // Setup
        final CreateCustomerDto createCustomerDto = new CreateCustomerDto();
        createCustomerDto.setCustomerName("customerName");
        createCustomerDto.setCustomerIdNumber("customerIdNumber");
        createCustomerDto.setSurname("surname");
        createCustomerDto.setAddress("address");
        createCustomerDto.setPhoneNumber("phoneNumber");
        createCustomerDto.setGender(Gender.MALE);
        createCustomerDto.setEmail("email");
        createCustomerDto.setDateOfBirth(LocalDate.of(2020, 1, 1));

        // Configure CustomerService.getCustomerByIdNumber(...).
        final Customer customer = new Customer();
        customer.setCustomerName("customerName");
        customer.setCustomerIdNumber(null);
        customer.setSurname("surname");
        customer.setAddress("address");
        customer.setPhoneNumber("phoneNumber");
        customer.setGender(null);
        customer.setEmail("email");
        customer.setDateOfBirth(LocalDate.of(2020, 1, 1));
        when(mockCustomerService.getCustomerByIdNumber("customerIdNumber")).thenReturn(customer);

        // Configure CustomerService.createCustomer(...).
        final Customer customer1 = new Customer();
        customer1.setCustomerName("customerName");
        customer1.setCustomerIdNumber("customerIdNumber");
        customer1.setSurname("surname");
        customer1.setAddress("address");
        customer1.setPhoneNumber("phoneNumber");
        customer1.setGender(Gender.MALE);
        customer1.setEmail("email");
        customer1.setDateOfBirth(LocalDate.of(2020, 1, 1));

        // Run the test
        Assertions.assertThrows(BadRequestDataException.class, () -> customerControllerUnderTest.createCustomer(createCustomerDto));
    }
}
