package omazon.prototypes;

import java.util.ArrayList;
import java.util.Comparator;

public abstract class ListType<E> extends ArrayList<E> implements Comparator<E> {

  // FIELDS
  protected Integer id;
  protected Integer elementCount;
  protected int sortOrder;

  protected ListType() {
    super();
    elementCount = 0;
  }

  /**
   * Sets sort in ascending order
   * @param ascendingOrder
   */
  public void setDefaultSortOrder(int...ascendingOrder) {
    sortOrder = ascendingOrder[0];
  }

  /**
   * Initializes the id fields
   * @param id object id
   */
  public void setID(Integer id) {
    this.id = id;
  }

  /**
   * Gets the object ID
   * @return id
   */
  public Integer getID() {
    return id;
  }

  /**
   * Gets the number of elements in the list
   * @return total number of elements in the list
   */
  public Integer getElementCount() {
    return elementCount;
  }


  public int getSize() {
    return this.size();
  }

  /**
   * Increases element number by one
   */
  public void incrementElementCount() {
    elementCount++;
  }

  /**
   * Decreases element number by one
   */
  public void decrementElementCount() {
    elementCount--;
  }

  /**
   * Sets the order of the sort; true (ascending) or false (descending)
   * 
   * @param order 1 true; -1 false
   */
  public void ascendDescend(boolean order) {
    if (order)
      sortOrder = 1;
    else
      sortOrder = -1;
  }

  @SuppressWarnings("unchecked")
  @Override
  public boolean equals(Object otherList) {
    if (!super.equals(otherList)) return false;
    ListType<E> other = (ListType<E>) otherList;
    return id == other.id;
  }

  @Override
  public int hashCode() {
    return 13 * id.hashCode() + 11 * elementCount.hashCode();
  }

}