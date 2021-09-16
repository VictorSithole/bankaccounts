package com.wonderlabz.accounting.bankaccounts.transactions.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.wonderlabz.accounting.bankaccounts.account.model.Account;
import com.wonderlabz.accounting.bankaccounts.common.BaseEntity;
import com.wonderlabz.accounting.bankaccounts.enums.TransactionType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Table(name = "transactions", indexes = {@Index(name = "indx_transactions", columnList = "id", unique = true)})
@Getter
@Setter
@ToString
public class Transaction extends BaseEntity {

  @Column(name = "transactiontype", nullable = false)
  private TransactionType transactionType;

  @Column(name = "amount", nullable = false)
  private double amount;

  @Column(name = "transactiondate", nullable = false)
  private LocalDateTime transactionDate;

  @ManyToOne
  @JoinColumn(name="account_id",referencedColumnName = "id")
  @JsonIgnoreProperties
  @Transient
  private Account account;

  @Column(name = "sourceaccountNo", nullable = true)
  private String accountNumber;

  @Column(name = "destinationaccount", nullable = true)
  private String destinationAccount;

  @Column(name = "customerid", nullable = false)
  private Long customerId;

}
