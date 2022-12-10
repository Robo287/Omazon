package omazon.elements;

import java.util.Collections;
import java.util.Comparator;
import java.util.ListIterator;
import java.util.Objects;

import omazon.interfaces.MonetarySettings;
import omazon.interfaces.ProductsTemplate;
import omazon.interfaces.TransactionTemplate;
import omazon.prototypes.ListType;

// INNER CLASS
// ********************************************************************
public class Transaction extends ListType<Product> implements ProductsTemplate, MonetarySettings, TransactionTemplate {

  // FIELDS
  private Integer customerID;
  private Double subtotal;
  private Double total;
  private Double taxation;


  public Transaction() {
    super();
    elementCount= 0;
  }

  public Transaction(Integer transactionID, Integer customerID) {
    super();
    this.id = transactionID;
    this.customerID = customerID;
    elementCount= 0;
  }

  // FUNCTIONS

  // SETTER & GETTER FUNCTIONS
  // ********************************************************************

  /**
   * Initializes the customerID
   * @param customerID
   */
  public void setCustomerID(Integer customerID) {
    this.customerID = customerID;
  }

  /**
   * Gets the customer ID
   * @return ID
   */
  public Integer getCustomerID() {
    return customerID;
  }
  
  // SORT FUNCTIONS
  // ********************************************************************

  /**
   * Compares items by their ID
   * @param product1
   * @param product2
   * @return 1 true; -1 false
   */
  @Override
  public int compare(Product product1, Product product2) {
    return product1.getID().compareTo(product2.getID());
  }

  /**
   * Sorts the list by item ID
   */
  public void sort() {
    Comparator<Product> comp = new Transaction();
    Collections.sort(this,comp);
  }

  /**
   * Checks for equality between two Transaction objects
   * @return true if equal otherwise false
   */
  @Override
  public boolean equals(Object otherTransaction) {
    if (!super.equals(otherTransaction)) return false;
    Transaction other = (Transaction) otherTransaction;
    if (size() != other.size()) return false;
    return containsAll(other);
  }

  /**
   * Returns a hash code for object authenticity
   * @return custom hashcode
   */
  @Override
  public int hashCode() {
    return 11 * id.hashCode() + 13 * customerID.hashCode();
  }

  

  // MONETARY FUNCTIONS
  //********************************************************************

  public Double getSubtotal() {
    calculuateSubtotal();
    return subtotal;
  }

  public Double getTotal() {
    calculateTotal();
    return total;
  }

  public Double getTax() {
    calculateTax();
    return taxation;
  }

  /**
   * Calculates the total taxation
   * @return total taxation
   */
  @Override
  public void calculateTax() {
    taxation = STATE_TAX * getSubtotal();
  }

  /**
   * Calculates the total retail cost
   * @return retail total
   */
  @Override
  public void calculuateSubtotal() {
    if (!this.isEmpty()) {
      for (Product t : this) {
        subtotal += t.getRetailPrice();
      }
    }
  }

  /**
   * Caculates total taxation plus total retail cost
   * @return total 
   */
  @Override
  public void calculateTotal() {
    total = getTax() + getSubtotal();
  }

  // TRANSACTION FUNCTIONS
  // ********************************************************************

  /**
   * Searches the list by item ID
   * @param productID is the index of the item
   * @return Product
   */
  public Product search(int productID) {
    ListIterator<Product> iterator = this.listIterator();
    while (iterator.hasNext()) {
      Product result = iterator.next();
      if (result.getID() == productID) {
        return result;
      }
    }
    return null;
  }

  /**
   * Searches by item name
   * @param name
   * @return Product
   */
  public Product search(String name) {
    ListIterator<Product> iterator = this.listIterator();
    while (iterator.hasNext()) {
      Product result = iterator.next();
      if (Objects.equals(result.getName(), name)) {
        return result;
      }
    }
    return null;
  }

  /**
   * Searches by item
   * @param item
   */
  public Product search(Product item) {
    ListIterator<Product> iterator = this.listIterator();
    while (iterator.hasNext()) {
      Product result = iterator.next();
      if (result.equals(item)) {
        return result;
      }
    }
    return null;
  }

  /**
   * Checks for existing item by item id (index)
   * @param productID
   * @return false if item is not in list
   */
  public boolean found(int productID) {
    return search(productID) == null;
  }

  /**
   * Checks for existing item by name
   * @param name
   * @return false if item is not in list
   */
  public boolean found(String name) {
    return search(name) == null;
  }

  /**
   * Checks for existing item by item
   * @param item
   * @return false if item is not in list
   */
  public boolean found(Product item) {
    return search(item) == null;
  }

  /**
   * Adds a new item to the list by productID, name, price, and quantity
   * @param productID item index
   * @param name item name
   * @param price item price
   * @param quantity number of items in stock
   */
  @Override
  public String addItem(int productID, String name, String price, String quantity) {
    if (!found(productID)) {
      Product item = new Product(name, price, quantity);
      this.add(productID, item);
      return ADDED;
    }
    return EXISTS;
  }

  /**
   * Adds a new item to list by name, prce, and quanityt
   * @param name
   * @param price
   * @param quantity
   */
  @Override
  public String addItem(String name, String price, String quantity) {
    if (!found(name)) {
      Product item = new Product(name, price, quantity);
      this.add(item);
      return ADDED;
    }
    return EXISTS;
  }

  /**
   * Adds an item to the list by item
   * @param item
   */
  @Override
  public String addItem(Product item) {
    if (!found(item)) {
      this.add(item);
      return ADDED;
    }
    return EXISTS;
  }

  /**
   * Removes an item from the list by productID
   * @param productID
   */
  @Override
  public String removeItem(int productID) {
    if (found(productID)) {
      this.remove(productID);
      return REMOVED;
    }
    return NOT_EXISTS;
  }

  /**
   * Removes an item from the list by name
   * @param name
   */
  @Override
  public String removeItem(String name) {
    if (found(name)) {
      this.remove(search(name));
      return REMOVED;
    }
    return NOT_EXISTS;
  }

  /**
   * 
   * @param item
   */
  @Override
  public String removeItem(Product item) {
    if (found(item)) {
      this.remove(item);
      return REMOVED;
    }
    return NOT_EXISTS;
  }

  

}
