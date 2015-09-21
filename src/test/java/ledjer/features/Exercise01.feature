Feature: Exercise 1
  The ledger can accept deposits.
  Deposits increase the balance of the ledger.

  Scenario: Deposits.
    Given an empty ledger
    When a deposit of 100 is made
    And a deposit of 200 is made
    Then the balance is 300
    And the statement contains
    """
    Deposit: $1.00
    Deposit: $2.00
    Total: $3.00
    """