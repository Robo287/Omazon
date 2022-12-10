package omazon.lists;

import omazon.panels.Store;
import omazon.prototypes.AccountType;

public class Seller extends AccountType {

  // FIELDS
  Store myStore;

  public Seller(String firstName, String lastName, String email) {
    super(firstName, lastName, email);
    myStore = new Store(getID(), "Omazon");
  }

  // FUNCTIONS
  public Products getMyInventory() {
    return this.productsList;
  }

  public Store getStore() {
    return myStore;
  }

  public Wallet getMyWallet() {
    return walletList;
  }

}
