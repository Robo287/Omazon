package omazon.interfaces;

public interface InventorySettings {
  Double TAX = 0.06;
  void calculateExpenses();
  void calculuteRevenues();
  void calculateProfits();
  Double getTotalExpenses();
  Double getRevenues();
  Double getProfits();
}
