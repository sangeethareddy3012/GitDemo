Feature: Error Validations
@ErrorValidation
Scenario Outline: validating error while logging in
Given I landed on Ecommerce Page
When Logged in with username <name> and password <password>
Then "Incorrect email or password." message is displayed
Examples:
 |       name                 ||password | 
 |sangeetharey1229@gmail.com||Mangoes@7| 