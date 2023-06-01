Feature: Purchase the order from Ecommerce Website
Background:
Given I landed on Ecommerce Page

Scenario Outline: Positive Test of Submitting the order
 Given Logged in with username <name> and password <password>
 When I add the product <productName> to Cart
 And checkout <productName> and submit the order
 Then check "THANKYOU FOR THE ORDER." message is displayed on confirmation page
 
 Examples:
 |       name                 ||password | |productName|
 |sangeethareddy1229@gmail.com||Mangoes@7| |ZARA COAT 3|