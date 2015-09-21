Feature: Exercise 6
  Assign each Transaction a sequential id number on creation with nextNumber.
  (Hint: nextNumber can be static.)

  Scenario: Each transaction is assigned a sequential id number.
    Given an empty ledger
    When a deposit of 100 is made
    And a deposit of 100 is made
    And a payment of 100 is made to amazon.com
    And a payment of 100 is made to amazon.com
    And a deposit of 100 is made
    Then the statement contains
    """
    1. Deposit: $1.00
    2. Deposit: $1.00
    3. Payment to amazon.com: ($1.00)
    4. Payment to amazon.com: ($1.00)
    5. Deposit: $1.00
    Total: $1.00
    """
