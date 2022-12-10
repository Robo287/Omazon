package omazon.lists;

import omazon.databases.Receipts;
import omazon.databases.Receipts.Receipt;
import omazon.prototypes.AccountType;

public class Customer extends AccountType {

  // FIELDS
  Receipts myReceipts;

  // CONSTRUCTORS
  public Customer(String firstName, String lastName, String email) {
    super(firstName, lastName, email);
  }

  // FUNCTIONS

  public Products getMyCart() {
    return this.productsList;
  }

  public Receipts getMyReceipts() {
    return myReceipts;
  }

  public Wallet getMyWallet() {
    return walletList;
  }

  public void saveReceipt(Receipt newReceipt) {
    myReceipts.add(newReceipt);
  }
}
