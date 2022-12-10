package omazon.interfaces;

// IMPLEMENTING CLASSES:
// Transaction

import omazon.elements.Product;

public interface ProductsTemplate {

  // FIELDS
  String ADDED = "Product successfully added!";
  String REMOVED = "Product successfully removed!";
  String EXISTS = "Product already added!";
  String NOT_EXISTS = "Product not found!";

  // Search
  Product search(int id);
  Product search(String name);
  Product search(Product product);

  // Sort
  void sort();

  // Check
  boolean found(int id);
  boolean found(String name);
  boolean found(Product item);

  // Add
  String addProduct(int id, String name, String price, String quantity);
  String addProduct(String name, String price, String quantity);
  String addProduct(Product product);
  
  // Remove
  String removeProduct(int id);
  String removeProduct(String name);
  String removeProduct(Product product);

  boolean equals(Object obj);
  
}
