package omazon.prototypes;

import javax.swing.*;
import java.awt.*;
import java.util.Comparator;

public abstract class PanelType extends JPanel implements Comparator<PanelType> {


  // FIELDS
  protected String id;
  protected Integer panelNo;

  // CONSTRUCTORS
  protected PanelType() {
    super();
    id = "abstract";
  }

  protected PanelType(LayoutManager layout, int panelNo) {
    super(layout);
    this.panelNo = panelNo;
  }

  // FUNCTIONS

  /**
   * 
   */
  @Override
  public boolean equals(Object otherList) {
    if (!super.equals(otherList)) return false;
    PanelType other = (PanelType) otherList;
    return id == other.id;
  }

  @Override
  public int hashCode() {
    return 13 * id.hashCode() + 11 * panelNo.hashCode();
  }

  /**
   * 
   */
  public void setPanelNumber(Integer number) {
    this.panelNo = number;
  }

  /**
   * 
   */
  public void setPanelLayout(LayoutManager layout) {
    //TODO: add layout
  }

  /**
   * Gets the store ID
   * 
   * @return store id
   */
  public String getID() {
    return id;
  }

  /**
   * 
   * @return
   */
  public Integer getPanelNumber() {
    return panelNo;
  }
  
} 
