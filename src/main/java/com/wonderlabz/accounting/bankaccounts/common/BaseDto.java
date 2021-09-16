package com.wonderlabz.accounting.bankaccounts.common;

import lombok.Data;

import java.io.Serializable;

@Data
public class BaseDto implements Serializable {
    private Long id;
    private int version;
}
