Feature: Exercise 9
  The statement() for a ledger prints all transactions in chronological order.

  Scenario: Transactions happen on a date
    Given an empty ledger
    When a deposit of 100 is made on Jan 31, 2013
    And a deposit of 100 is made on Jan 30, 2013
    And a payment of 100 is made on Jan 29, 2013 to amazon.com
    And a payment of 100 is made on Jan 28, 2013 to amazon.com
    And a deposit of 100 is made on Jan 27, 2013
    Then the statement contains
    """
    Jan 27, 2013 5. Deposit: $1.00
    Jan 28, 2013 4. Payment to amazon.com: ($1.00)
    Jan 29, 2013 3. Payment to amazon.com: ($1.00)
    Jan 30, 2013 2. Deposit: $1.00
    Jan 31, 2013 1. Deposit: $1.00
    Total: $1.00
    """
