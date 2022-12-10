package omazon.prototypes;

import java.util.Collections;
import java.util.Comparator;
import java.util.ListIterator;
import java.util.Objects;

import omazon.elements.Card;
import omazon.elements.Product;
import omazon.interfaces.InventorySettings;
import omazon.interfaces.MonetarySettings;
import omazon.interfaces.ProductsTemplate;

public abstract class AccountType {

  // FIELDS
  private int userID;
  private String firstName;
  private String lastName;
  private String email;
  protected Products productsList;
  protected Wallet walletList;

  protected AccountType(String firstName, String lastName, String email) {
    this.userID = 0;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    productsList = new Products(userID);
    walletList = new Wallet(userID);
  }

  // FUNCTIONS

  // Accessor Methods
  
  /**
   * Gets the object ID
   * @return
   */
  public int getID() {
    return userID;
  }

  /**
   * Gets the user's full name
   * @return full name
   */
  public String getName() {
    return firstName + " " + lastName;
  }

  /**
   * Gets the user's first name
   * @return first name
   */
  public String getFirstName() {
    return firstName;
  }

  /**
   * Gets the user's last name
   * @return last name
   */
  public String getLastName() {
    return lastName;
  }


  /**
   * Gets the user's email address
   * @return email address
   */
  public String getEmail() {
    return email;
  }

  // INNER CLASS
  // ********************************************************************

  /**
   * An inner class to encapsulate the user's products list (cart or inventory)
   */
  public class Products extends ListType<Product> implements ProductsTemplate, MonetarySettings {

    // FIELDS
    private Integer customerID;

    // CONSTRUCTORS
    public Products() {
      super();
      elementCount = 0;
    }

    public Products(int customerID) {
      super();
      elementCount = 0;
      this.customerID = customerID;
    }

    // FUNCTIONS

    // SORT FUNCTIONS
    // ********************************************************************

    /**
     * Compares products by their ID
     */
    public int compare(Product Product1, Product Product2) {
      return Product1.getID().compareTo(Product2.getID());
    }

    /**
     * Sorts the list by Product ID
     */
    public void sort() {
      Comparator<Product> comp = new Products();
      Collections.sort(this, comp);
    }

    /**
     * Checks for equality between two Products list objects
     * @return true if all items are the same
     */
    @Override
    public boolean equals(Object otherList) {
      if (!super.equals(otherList))
        return false;
      Products other = (Products) otherList;
      if (size() != other.size())
        return false;
      return containsAll(other);
    }

    @Override
    public int hashCode() {
      return 11 * id.hashCode() + 13 * customerID.hashCode();
    }

    // SETTER & GETTER FUNCTIONS
    // ********************************************************************

    /**
     * Initializes customer ID
     * @param customerID
     */
    public void setCustomerID(int customerID) {
      this.customerID = customerID;
    }

    /**
     * Gets the customer ID
     */
    public int getCustomerID() {
      return customerID;
    }

    // PRODUCTS FUNCTIONS
    // ********************************************************************


    /**
     * Searches the list by the Product ID
     * @param index is the index of the Product
     * @return Product
     */
    public Product search(Integer ProductID) {
      ListIterator<Product> iterator = this.listIterator();
      while (iterator.hasNext()) {
        Product result = iterator.next();
        if (result.getID().equals(ProductID)) {
          return result;
        }
      }
      return null;
    }

    /**
     * 
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
     * @param Product
     */
    public Product search(Product Product) {
      ListIterator<Product> iterator = this.listIterator();
      while (iterator.hasNext()) {
        Product result = iterator.next();
        if (result.equals(Product)) {
          return result;
        }
      }
      return null;
    }

    /**
     * 
     * @param index
     * @return false if Product is not in list
     */
    public boolean found(int index) {
      return search(index) == null;
    }

    /**
     * 
     * @param name
     * @return false if Product is not in list
     */
    public boolean found(String name) {
      return search(name) == null;
    }

    /**
     * 
     * @param Product
     * @return false if Product is not in list
     */
    public boolean found(Product Product) {
      return search(Product) == null;
    }
    /**
     * 
     * @param index
     * @param name
     * @param price
     * @param quantity
     */
    public String addProduct(int index, String name, String price, String quantity) {
      if (!found(index)) {
        Product Product = new Product(name,price,quantity);
        this.add(index,Product);
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
    public String addProduct(String name, String price, String quantity) {
      if (!found(name)) {
        Product Product = new Product(name, price, quantity);
        this.add(Product);
        return ADDED;
      }
      return EXISTS;
    }

    /**
     * 
     * @param Product
     */
    public String addProduct(Product Product) {
      if (!found(Product)){
        this.add(Product);
        return ADDED;
      }
      return EXISTS;
    }

    /**
     * 
     * @param index
     */
		public String removeProduct(int index) {
      if (found(index)) {
        this.remove(index);
        return REMOVED;
      }
      return NOT_EXISTS;
		}

    /**
     * 
     * @param name
     */
		public String removeProduct(String name) {
      if (found(name)) {
        this.remove(search(name));
        return REMOVED;
      }
      return NOT_EXISTS;
		}

    /**
     * 
     * @param Product
     */
		public String removeProduct(Product Product) {
      if (found(Product)) {
        this.remove(Product);
        return REMOVED;
      }
      return NOT_EXISTS;
		}

    @Override
    public Product search(int id) {
      // TODO Auto-generated method stub
      return null;
    }

    @Override
    public void calculateTax() {
      // TODO Auto-generated method stub
      
    }

    @Override
    public void calculuateSubtotal() {
      // TODO Auto-generated method stub
      
    }

    @Override
    public void calculateTotal() {
      // TODO Auto-generated method stub
      
    }

    @Override
    public Double getSubtotal() {
      // TODO Auto-generated method stub
      return null;
    }

    @Override
    public Double getTotal() {
      // TODO Auto-generated method stub
      return null;
    }

    @Override
    public Double getTax() {
      // TODO Auto-generated method stub
      return null;
    }

  } // END Products INNER CLASS 

  
  class Wallet extends ListType<Card> {

    // FIELDS
    private Integer ownerID;

    // CONSTRUCTORS
    public Wallet() {
      super();
      setDefaultSortOrder(1);
    }

    public Wallet(int ownerID) {
      super();
      this.ownerID = ownerID;
      setDefaultSortOrder(1);
    }

    /**
     * Compares cards by their ID
     * @param card1
     * @param card2
     * @return 1 true; -1 false
     */
    public int compare(Card card1, Card card2) {
      return card1.getID().compareTo(card2.getID());
    }

    /**
     * Sorts the list by ID
     */
    public void sort() {
      Comparator<Card> comp = new Wallet();
      Collections.sort(this,comp);
    }

    /**
     * Checks for equality between copies
     */
    @Override
    public boolean equals(Object otherWallet) {
      if(!super.equals(otherWallet)) return false;
      Wallet other = (Wallet) otherWallet;
      if (size() != other.size()) return false;
      return containsAll(other);
    }

    /**
     * Returns a hash code for object authenticity
     */
    @Override
    public int hashCode() {
      return 11 * id.hashCode() + 13 * ownerID.hashCode();
    }

  } // END Wallet INNER CLASS 
  
} // END OUTER CLASS
