package com.wonderlabz.accounting.bankaccounts.customer.service;

import com.wonderlabz.accounting.bankaccounts.account.repository.AccountRepository;
import com.wonderlabz.accounting.bankaccounts.customer.dto.CreateCustomerDto;
import com.wonderlabz.accounting.bankaccounts.customer.model.Customer;
import com.wonderlabz.accounting.bankaccounts.customer.repository.CustomerServiceRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;


@Service
@Slf4j
@Transactional(propagation = Propagation.REQUIRED)
public class CustomerServiceImpl implements CustomerService{

  private final CustomerServiceRepository customerServiceRepository;
  private final AccountRepository accountRepository;

  public CustomerServiceImpl(CustomerServiceRepository customerServiceRepository, AccountRepository accountRepository) {

    this.customerServiceRepository = customerServiceRepository;
    this.accountRepository = accountRepository;
  }

  @Override
  public Customer getCustomerByIdNumber(String customerIdNumber) {
    return customerServiceRepository.findCustomerByCustomerIdNumber(customerIdNumber);
  }

  @Override
  public Customer createCustomer(CreateCustomerDto customerDto) {

    log.info("Register Customer :{}", customerDto);
    if(customerServiceRepository.findCustomerByCustomerIdNumber(customerDto.getCustomerIdNumber())==null){

      return customerServiceRepository.save(createAcccount(customerDto));

    }else {

      throw new IllegalArgumentException("The user with Customer ID Number is not found: "+ customerDto.getCustomerIdNumber());
    }

  }

  @Override
  public Customer getCustomerById(Long Id) {


      Optional<Customer> customer = customerServiceRepository.findById(Id);
      if(Objects.isNull(customer)){
        throw new IllegalArgumentException("Customer with this CustomerIDNumber does not exist "+ Id);
      }

      return customer.get();
    }


  //create account
  protected Customer createAcccount(CreateCustomerDto customerDto) {

    Customer customer = new Customer();
    customer.setCustomerIdNumber(customerDto.getCustomerIdNumber());
    customer.setAddress(customerDto.getAddress());
    customer.setCustomerName(customerDto.getCustomerName());
    customer.setSurname(customerDto.getSurname());
    customer.setDateOfBirth(customerDto.getDateOfBirth());
    customer.setEmail(customerDto.getEmail());
    customer.setGender(customerDto.getGender());
    customer.setPhoneNumber(customerDto.getPhoneNumber());

    return customer;
  }

}
