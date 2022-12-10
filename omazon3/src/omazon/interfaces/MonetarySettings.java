package omazon.interfaces;

//IMPLEMENTING CLASSES:
//Receipts

public interface MonetarySettings {
  Double STATE_TAX = 0.06;
  void calculateTax();
  void calculuateSubtotal();
  void calculateTotal();
  Double getSubtotal();
  Double getTotal();
  Double getTax();
}
