package omazon.elements;

import java.time.*;
import java.util.Collections;
import java.util.Comparator;

import omazon.prototypes.ListType;

public class Card extends ListType<Card.CardTransaction> {

  // FIELDS
  private Double balance;
  private Long cardNo;
  private int cVV;
  private String cardName;
  private YearMonth expirationDate;

  // CONSTRUCTORS
  protected Card() {
    id = 0;
    elementCount = 0;
    balance = 1000.00;
    cardNo = 4321098723457890L;
    cVV = 890;
    expirationDate = YearMonth.of(2024,12);
    this.cardName = "";
    setDefaultSortOrder(1);
  }

  protected Card(String cardName) {
    id = 0;
    elementCount = 0;
    balance = 1000.00;
    cardNo = 4321098723457890L;
    cVV = 890;
    expirationDate = YearMonth.of(2024, 12);
    this.cardName = cardName;
    setDefaultSortOrder(1);
  }

  // FUNCTIONS

  /**
   * Compares cards by their ID
   * @param card1
   * @param card2
   * @return 1 true; -1 false
   */
  public int compare(CardTransaction transaction1, CardTransaction transaction2) {
    return transaction1.getID().compareTo(transaction2.getID());
  }
  
  /**
   * Sorts the list by ID
   */
  public void sort() {
    Comparator<CardTransaction> comp = new Card();
    Collections.sort(this,comp);
  }

  /**
   * Checks for equality between copies
   * @return true if objects are equal deep copies
   */
  @Override
  public boolean equals(Object otherCard) {
    if (!super.equals(otherCard)) return false;
    Card other = (Card) otherCard;
    return id == other.getID();
  }

  /**
   * Returns a hashcode for object authenticity
   * @return custom hash code
   */
  @Override
  public int hashCode() {
    return 11 * this.id.hashCode() + 13 * this.cardNo.hashCode();
  }

  /**
   * Initializes the cardName field
   * @param name
   */
  public void setCardName(String name) {
    cardName = name;
  }


  /**
   * Initializes the card with a 16-digit card number
   * @param cardNo
   */
  public void setCardNo(Long cardNo) {
    this.cardNo = cardNo;
  }

  /**
   * Initializes the card expiration date
   * @param year of expiration
   * @param month of expiration
   */
  public void setCardExpiration(int year, int month) {
    expirationDate = YearMonth.of(year,month);
  }

  /**
   * Gets the name of the card
   * @return
   */
  public String getCardName() {
    return cardName;
  }

  /**
   * Gets the amount of available funds
   * @return available balance
   */
  public Double getAvailableBalance() {
    return balance;
  }

  /**
   * Gets the card number
   * @return credit/debit card number
   */
  public Long getCardNo() {
    return cardNo;
  }

  public YearMonth getCardExpiration() {
    return expirationDate;
  }

  /**
   * Gets the card security code
   * @return card security code
   */
  public int getCVV() {
    return cVV;
  }

  /**
   * Updates the balance field
   * @return total available funds based on all past transactions
   */
  public Double updateAvailableBalance() {
    if (!this.isEmpty()) {
      for (CardTransaction t : this) {
        if (t.type.equals("credit")) {
          balance += t.amount;
        } else if (t.type.equals("debit")) {
          balance -= t.amount;
        }
      }
    }
    return balance;
  }

  
  /**
   * Returns true value if the account has suffucient funds
   * @param amount
   * @return
   */
  public boolean sufficientFunds(Double amount) {
    return balance > amount;
  }
  
  /**
   * Adds money to account
   * @param amount
   */
  public Double credit(Double amount) {
    CardTransaction newTransaction = new CardTransaction(elementCount, "credit", amount);
    this.add(newTransaction);
    incrementElementCount();
    return updateAvailableBalance();
  }

  // Search Function omitted

  // Remove Function omitted

  /**
   * Takes money from account if it has sufficient funds
   * @param amount
   */
  public Double debit(Double amount) {
    if (!sufficientFunds(amount)) return null;
    CardTransaction newTransaction = new CardTransaction(elementCount, "debit", amount);
    this.add(newTransaction);
    return updateAvailableBalance();
  }

  // INNER CLASS *************************************************************************
  class CardTransaction {

    // FIELDS
    private Integer cardTransactionID;
    private String type;
    private Double amount;
    private LocalDate dateStamp;
  

    public CardTransaction() {
      cardTransactionID = 0;
      dateStamp = LocalDate.now();
    }

    public CardTransaction(Integer cardTransactionID, String type, Double amount) {
      this.cardTransactionID = cardTransactionID;
      this.type = type;
      this.amount = amount;
      dateStamp = LocalDate.now();
    }

    // FUNCTIONS


    /**
     * Initializes the card ID
     * @param cardTransactionID
     */
    public void setID(Integer cardTransactionID) {
      this.cardTransactionID = cardTransactionID;
    }

    /**
     * Initializes the card type (debit or credit)
     * @return
     */
    public void setType(String type) {
      this.type = type;
    }

    /**
     * Initializes the balance with new amount
     * Assumes balance is zero
     * NOTE: This will reset the balance unnecessarily if misused
     * @return amount passed as parameter
     */
    public void setAmount(Double amount) {
      this.amount = amount;
    }

    /**
     * Gets the date of the transaction
     * @return
     */
    public LocalDate getDateStamp() {
      return dateStamp;
    }

    /**
     * Gets the object id
     * @return
     */
    public Integer getID() {
      return cardTransactionID;
    }

    /**
     * Gets the card type (debit or credit)
     * @return
     */
    public String getType() {
      return type;
    }

    /**
     * Gets the amount of the transaction
     * @return
     */
    public Double getAmount() {
      return amount;
    } // END CardTransaction - INNER CLASS
  } // END Card - OUTER CLASS
}
