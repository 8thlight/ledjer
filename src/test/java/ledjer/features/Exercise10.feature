Feature: Exercise 10
  Ledger.save() serializes to a file, Ledger.load() deserializes.

  Scenario: Saving and loading a ledger
    Given an empty ledger
    And a deposit of 1000 is made on Jan 4, 2013
    And a payment of 500 is made on Feb 5, 2013 to Apple
    When saving the ledger
    And a deposit of 1234 is made on Feb 6, 2013
    And loading the saved ledger
    Then the statement contains
    """
    Jan 4, 2013 1. Deposit: $10.00
    Feb 5, 2013 2. Payment to Apple: ($5.00)
    Total: $5.00
    """