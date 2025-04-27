Feature: Assign Metadata

  Scenario Outline: Assign metadata to document in different queues
    Given I navigate to "<Queue>"
    When I select the document
    Then I assign metadata to the document

    Examples:
      | TestcaseID | Queue    |
      | TC001      | queue001 |
      | TC002      | queue002 |
