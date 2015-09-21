Feature: Exercise 8
  Support unlimited transactions using a LinkedList.

  Scenario: Many transactions
    Given an empty ledger
    When 100 deposits of 100 are made
    Then the balance is 10000
