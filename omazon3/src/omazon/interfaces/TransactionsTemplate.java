package omazon.interfaces;

// IMPLEMENTING CLASSES:
// Store.Transactions

import omazon.elements.Transaction;
import omazon.lists.Customer;
import omazon.prototypes.AccountType.Products;

public interface TransactionsTemplate {

  // Transaction Alerts
  String CREATED = "Transaction successfully created!";
  String ADDED = "Transaction successfully added!";
  String REMOVED = "Transaction successfully removed!";
  String EXISTS = "Transaction already added!";
  String NOT_EXISTS = "Transaction not found!";

  Double getTotalRevenue();
  Transaction createTransaction(Products cart);

  // Search
  Transaction search(Integer transactionID);
  Transaction search(Customer customer);
  Transaction search(Transaction transaction);

  // Check
  boolean found(int transactionID);
  boolean found(Customer customer);
  boolean found(Transaction transaction);
  
  // Add
  String addTransaction(int id, int customerID);
  String addTransaction(Transaction transaction);

  String removeTransaction(int transactionID);
  String removeTransaction(Transaction transaction);

}
