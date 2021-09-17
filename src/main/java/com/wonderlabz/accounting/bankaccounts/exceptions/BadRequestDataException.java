package com.wonderlabz.accounting.bankaccounts.exceptions;

public class BadRequestDataException extends RuntimeException{

  public BadRequestDataException(String message) {
    super(message);
  }

  public BadRequestDataException(String message, Throwable cause) {
    super(message, cause);
  }

  public BadRequestDataException(final Throwable cause) {
    super(cause);
  }
}
//TODO use global exception handler to cater for all possible exception scenarios that are verbose to the user