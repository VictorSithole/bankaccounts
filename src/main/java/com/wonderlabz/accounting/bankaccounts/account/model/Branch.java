package com.wonderlabz.accounting.bankaccounts.account.model;

import lombok.Data;

import java.util.List;

@Data
public class Branch extends Bank {
    private String branchNo;
    private String location;
    private List<Account> accounts;
}
