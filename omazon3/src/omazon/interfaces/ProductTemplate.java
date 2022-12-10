package omazon.interfaces;

// IMPLEMENTING CLASSES:
// Product

public interface ProductTemplate {

  Integer getID();
  String getName();
  String getBrandName();
  Double getRetailPrice();
  int getQuantity();
  Double getPrice();
  String getDescription();
  void incrementQuantity();
  void decrementQuantity();
  void increaseQuantity(int quantity);
  void decreaseQuantity(int quantity);
  void setPriceFormat();
}
