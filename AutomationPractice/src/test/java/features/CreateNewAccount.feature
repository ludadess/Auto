Feature: Creating a new User Account

Scenario: Create a new user account  (positive testing)
Given New User is on Landing page
When Navigate to Create Account page
When Enter data in all requered fields "Scenario1" and submit account
Then Verify newly created account "Scenario1" info is displayed correcty

