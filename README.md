# bank-rest-api

Exercise:  Bank simulation
We will simulate the workings of a Bank.  The bank will have customers and accounts and make loans.

Project details
We will need the following classes
	Bank
	Customer
	Account
	Loan
 
Account will have 3 sub classes:
	SavingsAccount
	CheckingAccount
	CDAccount
		CD accounts have a specified term
  
Loan will have 3 sub classes
	HomeLoan (aka mortgage)
		Home loans will be for 15, 20 or 30 years
		Maximum amount for a home loan is 2,000,000
	CarLoan
		Car loans will be for 3, 4 or 5 years
		Maximum amount for a car loan is 50,000
	PersonalLoan
		Personal loans will not have a term
		Maximum amount for a personal loan is 45,000
  
The Bank will be responsible for knowing all of its Customers, Loans and Accounts

Restrictions and Features
	All interactions with the app will be through a UI
	We can add new customers and new accounts
	The system will assign accounts unique account numbers when an account is created
	Customers can make deposits and withdrawals from their accounts
	Customers can transfer money between their accounts.  The transfer may not cause an overdraw condition
	Customers can make payments on their loans
	Savings accounts will pay interest.  Checking accounts will not
	We can make new loans
		The bank may only lend 90% of the sum of all its deposits.
		No customer can have more than 10% of the total loans the bank has made
  
  Customers can pay bills from their checking account
		If making a payment would overdraw the checking account, we will ask the customer if they would like to move money from their savings account to cover the overdraw if:
			the customer has a savings account
			the savings account has sufficient funds to cover the overdraw
		Otherwise, we will decline the payment.
		use an exception to have this situation handled on a case by case basis.  A window will pop up and the bank manager will log in and either approve or disapprove of the payment)
 
