package omazon.elements;

import omazon.interfaces.ProductTemplate;

public class Product implements ProductTemplate {

  // FIELDS
  private Integer id;
  private String name;
  private String brand;
  private Double retail; // list price
  private Double price; // sale price
  private Integer quantity;
  private String description;
  private boolean available;

  // CONSTRUCTORS

  public Product(String name, String retail, String quantity) {
    this.id = 0;
    this.name = name;
    this.retail = Double.parseDouble(retail);
    this.quantity = Integer.parseInt(quantity);
    setPriceFormat();
  }

  public Product(Integer productID, String name, String retail, String quantity) {
    this.id = productID;
    this.name = name;
    this.retail = Double.parseDouble(retail);
    this.quantity = Integer.parseInt(quantity);
    setPriceFormat();
  }

  // FUNCTIONS

  // SORT FUNCTIONS
  // ********************************************************************

  /**
   * Checkes for equality between two Product objects
   * 
   * @return true if both are equal
   */
  public boolean equals(Object otherItem) {
    if (this == otherItem)
      return true;
    if (otherItem == null)
      return false;
    if (getClass() != otherItem.getClass())
      return false;
    Product other = (Product) otherItem;
    return this.getID() == other.getID();
  }

  /**
   * Returns a hash code for object authenticity
   */
  @Override
  public int hashCode() {
    return 11 * id.hashCode() + 13 * name.hashCode();
  }

  // SETTER & GETTER FUNCTIONS
  // ********************************************************************

  /**
   * Formats the price to two decimal places.
   */
  public void setPriceFormat() {
    if (quantity > 400) {
      price = retail * .5D;
    } else if (quantity > 200) {
      price = retail * .6D;
    } else {
      price = retail * .7D;
    }
    price = Math.floor(price * 100 + 0.5) / 100;
  }

  /**
   * Gets the product ID
   * 
   * @return ID
   */
  public Integer getID() {
    return id;
  }

  /**
   * Gets the product name or title
   * 
   * @return name of product
   */
  public String getName() {
    return name;
  }

  /**
   * Gets the product brand
   * 
   * @return brand of product
   */
  public String getBrandName() {
    return brand;
  }

  /**
   * 
   * @return
   */
  public Double getRetailPrice() {
    return retail;
  }

  /**
   * 
   * @return
   */
  public int getQuantity() {
    return quantity;
  }

  /**
   * 
   * @return
   */
  public Double getPrice() {
    return price;
  }

  public String getDescription() {
    return description;
  }

  /**
   * Increases the quantity by one
   */
  public void incrementQuantity() {
    quantity++;
  }

  /**
   * Decreases the quantity by one
   */
  public void decrementQuantity() {
    quantity--;
  }

  // PRODUCT FUNCTIONS
  // ********************************************************************

  /**
   * Increases the quantity of a product by a given number
   * @param add quantity by how many?
   */
  public void increaseQuantity(int add) {
    quantity += add;
  }

  /**
   * Decreases the quantity of a product by given number
   * @param subtract quantity by how many?
   */
  public void decreaseQuantity(int subtract) {
    quantity -= subtract;
  }

  

}
