**BANK ACCOUNTS SERVICE**

**Requirements:**

**For building and running the application you need:**

JDK 1.8

Maven 3

**Running the application locally**

mvn spring-boot:run

Alternatively you can execute the main method in the com.wonderlabz.accounting.bankaccounts.BankaccountsApplication

The server will start at <http://localhost:8080>.

The h2 database instance is accessible at <http://localhost:8080/h2-console>.

Get username,password and database name for the h2 database instance
in the application properties.

Check also unit test on the unit test package.

**Recommendations for the live environment:**

1.  Check all transactions using security token, userid, or login key(jwt).
2.  Global exception handlers with all possible exceptions to allow user to be aware of what is exactly missing from the request in case a transaction does not go through.
3.  Field validation for all requests fields.
4.  Allow customers to be able to adjust their withdrawal daily limits.
5.  Allow customers to create joint accounts and transact.
6.  Unit tests that cover all scenarios and setting up a CI/CD pipeline that ensures no code is pushed into production with breaking unit tests.
7.  Use flyway scripts for database versioning.
