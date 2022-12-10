package omazon.databases;

import java.util.Collections;
import java.util.Comparator;

import omazon.elements.Product;
import omazon.elements.Transaction;
import omazon.interfaces.MonetarySettings;
import omazon.interfaces.ReceiptsTemplate;
import omazon.prototypes.ListType;

/**
 * A class that takes in a transaction as a receipt
 */
public class Receipts extends ListType<Receipts.Receipt> implements ReceiptsTemplate, MonetarySettings {

  // FIELDS
  private Integer customerID;
  private Double subtotal;
  private Double total;
  private Double taxation;
  private Receipt receiptList;

  public Receipts() {
    super();
    elementCount = 0;
  }

  // FUNCTIONS


  // SORT FUNCTIONS
  // ********************************************************************

  /**
   * Sorts list by ID
   */
  public int compare(Receipt item1, Receipt item2) {
    return item1.getID().compareTo(item2.getID());
  }

  /**
   * Sorts the list by Receipt ID
   */
  public void sort() {
    Comparator<Receipts.Receipt> comp = new Receipts();
    Collections.sort(this, comp);
  }

  /**
   * Checks for equality between two Receipts objects
   * @return true if both collections contain all the same elements
   */
  @Override
  public boolean equals(Object otherReceiptsList) {
    if (!super.equals(otherReceiptsList)) return false;
    Receipts other = (Receipts) otherReceiptsList;
    if (size() != other.size()) return false;
    return containsAll(other);
  }

  @Override
  public int hashCode() {
    return 11 * id.hashCode() + 13 * customerID.hashCode();
  }

  // SETTER & GETTER FUNCTIONS
  // ********************************************************************

  /**
   * Initializes the customerID field
   * @param customerID
   */
  public void setCustomerID(int customerID) {
    this. customerID = customerID;
  }

  /**
   * Gets the customer ID
   * @return
   */
  public int getCustomerID() {
    return customerID;
  }

  /**
   * Gets the list of receipts from the inner class
   * @return list
   */
  public Receipt getReceiptList() {
    return receiptList;
  }

  // MONETARY FUNCTIONS
  // ********************************************************************

  /**
   * 
   * @return
   */
  public Double getSubtotal() {
    calculuateSubtotal();
    return subtotal;
  }

  /**
   * 
   * @return
   */
  public Double getTotal() {
    calculateTotal();
    return total;
  }

  /**
   * 
   * @return
   */
  public Double getTax() {
    calculateTax();
    return taxation;
  }

  /**
   * Calculates the total taxation
   * 
   * @return total taxation
   */
  @Override
  public void calculateTax() {
    taxation = STATE_TAX * getSubtotal();
  }

  /**
   * Calculates the total retail cost (sale price)
   * 
   * @return retail total
   */
  @Override
  public void calculuateSubtotal() {
    if (!this.isEmpty()) {
      for (Product t : getReceiptList()) {
        subtotal += t.getRetailPrice();
      }
    }
  }

  /**
   * Calculates the total
   */
  @Override
  public void calculateTotal() {
    total = getTax() + getSubtotal();
  }

  // RECEIPTS FUNCTIONS
  // ********************************************************************

  public void createReceipt(Transaction transaction) {
    //TODO: define this function
  }

  // INNER CLASS ********************************************************

  public class Receipt extends ListType<Product> implements MonetarySettings {

    // FIELDS
    private Integer customerID;
    private Integer transactionID;
    private Double subtotal;
    private Double total;
    private Double taxation;


    // CONSTRUCTORS
    public Receipt() {
      super();
      elementCount = 0;
    }

    public Receipt(Integer customerID, Integer transactionID) {
      super();
      this.customerID = customerID;
      this.transactionID = transactionID;
      elementCount = 0;
    }

    // FUNCTIONS

    // SORT FUNCTIONS
    // ********************************************************************
    /**
     * Compares products by their ID
     */
    @Override
    public int compare(Product item1, Product item2) {
      return item1.getID().compareTo(item2.getID());
    }

    /**
     * Sorts the list by Product ID
     */
    public void sort() {
      Comparator<Product> comp = new Receipt();
      Collections.sort(this, comp);
    }
    
    /**
     * Checks for equality between two Receipt list objects
     */
    @Override
    public boolean equals(Object otherReceipt) {
      if (!super.equals(otherReceipt))
        return false;
      Receipt other = (Receipt) otherReceipt;
      if (size() != other.size())
        return false;
      return containsAll(other);
    }

    /**
     * Returns a hash code for object authenticity
     * 
     * @return custom hash code
     */
    @Override
    public int hashCode() {
      return 11 * id.hashCode() + 13 * transactionID.hashCode();
    }

    // MONETARY FUNCTIONS
    // ********************************************************************

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
     * 
     * @return total taxation
     */
    @Override
    public void calculateTax() {
      taxation = STATE_TAX * getSubtotal();
    }

    /**
     * Calculates the total retail cost
     * 
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
     * 
     * @return total
     */
    @Override
    public void calculateTotal() {
      total = getTax() + getSubtotal();
    }

    // SETTER & GETTER FUNCTIONS
    // ********************************************************************
    /**
     * Initializes the customer
     * 
     * @param customerID
     */
    public void setCustomerID(int customerID) {
      this.customerID = customerID;
    }

    /**
     * Gets the customer ID
     * @return customer ID
     */
    public Integer getCustomerID() {
      return customerID;
    }

    // RECEIPT FUNCTIONS
    // ********************************************************************

    

  } // END Receipt - INNER CLASS
} // END Receipts - OUTER CLASS
