package omazon.panels;


import java.util.Collections;
import java.util.Comparator;
import java.util.ListIterator;
import java.util.Objects;

import omazon.databases.Accounts;
import omazon.elements.Product;
import omazon.elements.Transaction;
import omazon.interfaces.InventorySettings;
import omazon.interfaces.TransactionsTemplate;
import omazon.lists.Customer;
import omazon.prototypes.ListType;
import omazon.prototypes.PanelType;
import omazon.prototypes.AccountType.Products;

import java.awt.*;

public class Store extends PanelType {

  // FIELDS
  private Integer ownerID;
  private String storeName;
  private Accounts accountDB;
  private Transactions transactionDB;

  public Store(Integer ownerID, String storeName) {
    super();
    id = "store";
    this.ownerID = ownerID;
    this.storeName = storeName;
    accountDB = new Accounts();
    transactionDB = new Transactions();
  }

  public Store(Integer ownerID, String storeName, LayoutManager layout, Integer panelNo) {
    super(layout,panelNo);
    id = "store";
    this.ownerID = ownerID;
    this.storeName = storeName;
    accountDB = new Accounts();
    transactionDB = new Transactions();
  }


  // FUNCTIONS


  public int compare(PanelType panel1, PanelType panel2) {
    return panel1.getPanelNumber().compareTo(panel2.getPanelNumber());
  }


  /**
   * Checks for equality between copies
   * @return true if objects are equal deep copies
   */
  @Override
  public boolean equals(Object otherStore) {
    if (!super.equals(otherStore)) return false;
    Store other = (Store) otherStore;
    return id == other.getID();
  }

  /**
   * Returns hashcode for object authenticity
   * @return custom hash code
   */
  @Override
  public int hashCode() {
    return 11 * this.id.hashCode() + 13 * this.ownerID.hashCode();
  }

  /**
   * Gets the name of the store
   * @return store name
   */
  public String getStoreName() {
    return storeName;
  }

  /**
   * Gets the store owner's ID
   * @return
   */
  public Integer getOwnerID() {
    return ownerID;
  }

  /**
   * Gets the database of user accounts
   * @return
   */
  public Accounts getAccounts() {
    return accountDB;
  }

  
  public Transactions getTransactions() {
    return transactionDB;
  }

  // INNER CLASS ****************************************
  /**
   * An inner class to encapsulate the user's products list (cart or inventory)
   */
  class Transactions extends ListType<Transaction> implements TransactionsTemplate, InventorySettings {

    // FIELDS
    private Double totalRevenue;

    public Transactions() {
      super();
      elementCount = 0;
      totalRevenue = 0.0;
    }

     // FUNCTIONS

     // SORT FUNCTIONS
     // ********************************************************************

     public int compare(Transaction transaction1, Transaction transaction2) {
       return transaction1.getID().compareTo(transaction2.getID());
     }

     /**
      * Sorts list by ID
      */
     public void sort() {
       Comparator<Transaction> comp = new Transactions();
       Collections.sort(this,comp);
     }

     /**
      * Checks for equality between two Transactions objects
      * @return true if both collections contain all the same elements
      */
     @Override
     public boolean equals(Object otherTransactionsList) {
       if (!super.equals(otherTransactionsList))
         return false;
       Transactions other = (Transactions) otherTransactionsList;
       if (size() != other.size())
         return false;
       return containsAll(other);
     }

     /**
      * Returns a hash code for object authenticity
      * @return custom hashcode
      */
     @Override
     public int hashCode() {
       return 11 * id.hashCode() + 13 * totalRevenue.hashCode();
     }
     
     // SETTER & GETTER FUNCTIONS
     // ********************************************************************

     /**
      * Gets the total amount of money earned from all sales saved in the database
      @return total revenue 
      */
     public Double getTotalRevenue() {
       return totalRevenue;
     }

     /**
      * 
      * @return
      */
     @Override
     public Double getTotalExpenses() {
       // TODO Auto-generated method stub
       return null;
     }

     /**
      * 
      * @return
      */
     @Override
     public Double getRevenues() {
       // TODO Auto-generated method stub
       return null;
     }

     /**
      * 
      * @return
      */
     @Override
     public Double getProfits() {
       // TODO Auto-generated method stub
       return null;
     }

     // INVENTORY FUNCTIONS
     // ********************************************************************

     /**
      * 
      */
     @Override
     public void calculateExpenses() {
       // TODO Auto-generated method stub

     }

     /**
      * 
      */
     @Override
     public void calculuteRevenues() {
       // TODO Auto-generated method stub

     }

     /**
      * 
      */
     @Override
     public void calculateProfits() {
       // TODO Auto-generated method stub

     }

     // TRANSACTIONS FUNCTIONS
     // ********************************************************************

     /**
      * Creates a new transaction from a customer's cart
      */
     public Transaction createTransaction(Products cart) {
       ListIterator<Product> iterator = cart.listIterator();
       Transaction newTransaction = new Transaction();
       newTransaction.setCustomerID(cart.getID());
       while (iterator.hasNext()) {
         Product item = iterator.next();
         newTransaction.add(item);
       }
       return newTransaction;
     }

    /**
     * 
     * @param index is the index of the item
     * @return Product
     */
    @Override
    public Transaction search(Integer transactionID) {
      ListIterator<Transaction> iterator = this.listIterator();
      Transaction result = null;
      if (!this.isEmpty()) {
        while (iterator.hasNext()) {
          result = iterator.next();
          if (result.getID().equals(transactionID)) {
            return result;
          }
        }
      }
      return result;
    }

    /**
     * 
     * @param customer
     * @return Product
     */
    @Override
    public Transaction search(Customer customer) {
      ListIterator<Transaction> iterator = this.listIterator();
      Transaction result = null;
      if (!this.isEmpty()) {
        while (iterator.hasNext()) {
          result = iterator.next();
          if (Objects.equals(result.getCustomerID(), customer.getID())) {
            return result;
          }
        }
      }
      return result;
    }

    /**
     * @param item
     */
    public Transaction search(Transaction transaction) {
      ListIterator<Transaction> iterator = this.listIterator();
      Transaction result = null;
      if (!contains(transaction)) return result;
      while (iterator.hasNext()) {
        result = iterator.next();
        if (result.equals(transaction)) {
          return result;
        }
      }
      return result;
    }

    /**
     * 
     * @param index
     * @return false if item is not in list
     */
    public boolean found(int index) {
      return search(index) == null;
    }

    /**
     * 
     * @param customer
     * @return false if item is not in list
     */
    public boolean found(Customer customer) {
      return search(customer) == null;
    }

    /**
     * 
     * @param item
     * @return false if item is not in list
     */
    public boolean found(Transaction transaction) {
      return contains(transaction);
    }

    /**
     * 
     * @param index
     * @param name
     * @param price
     * @param quantity
     */
    public String addTransaction(int transactionID, int customerID) {
      if (!found(transactionID)) {
        Transaction newTransaction = new Transaction(transactionID,customerID);
        newTransaction.setID(getElementCount());
        this.add(newTransaction);
        incrementElementCount();
        return ADDED;
      }
      return EXISTS;
    }

    /**
     * 
     * @param name
     * @param price
     * @param quantity
     */
    public String addTransaction(Transaction transaction) {
      if (!found(transaction)) {
        Transaction newTransaction = (Transaction) transaction.clone();
        newTransaction.setID(getElementCount());
        this.add(newTransaction);
        incrementElementCount();
        return ADDED;
      }
      return EXISTS;
    }

    

    /**
     * Removes a trandaction by index
     * @param transactionID
     */
		public String removeTransaction(int transactionID) {
      // transactionID is the index
      if (found(transactionID)) {
        this.remove(transactionID);
        decrementElementCount();
        return REMOVED;
      }
      return NOT_EXISTS;
		}

    /**
     * Removes a transaction by object
     * @param transaction
     */
		public String removeTransaction(Transaction transaction) {
      if (found(transaction)) {
        this.remove(search(transaction));
        decrementElementCount();
        return REMOVED;
      }
      return NOT_EXISTS;
		}

    /**
     * Removes a transaction by the customerID
     * @param transaction
     */
		public String removeTransaction(Customer customer) {
      if (found(customer)) {
        this.remove(search(customer));
        decrementElementCount();
        return REMOVED;
      }
      return NOT_EXISTS;
		}
  } 
  
}
