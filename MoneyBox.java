import java.math.BigDecimal;

/**
 * Created by Lambda on 11/15/2015.
 */

/**
 * MoneyBox CLASS
 *
 * THIS CLASS ACTS AS A REGULAR MONEY BOX FOR VENDING MACHINE THAT COLLECTS
 * PAYMENTS.
 */
public class MoneyBox {
  private BigDecimal _balance = null;

  /**
   * CONSTRUCTOR
   */
  public MoneyBox(){
    this._balance = new BigDecimal("0.0");
  }

  /**
   * OVERLOAD CONSTRUCTOR
   *
   * INITIALIZE THE MONEYBOX OBJECT WITH AN INITIAL AMOUNT OF MONEY
   *
   * @param initial_amount INITIAL AMOUNT OF MONEY
   */
  public MoneyBox(String initial_amount){
    this._balance = new BigDecimal(initial_amount);
  }

  /**
   * collect_payment METHOD (PUBLIC)
   *
   * COLLECT PAYMENT AND ADD IT INTO BALANCE
   *
   * @param payment PAYMENT FOR A TRANSACTION
   */
  public void collect_payment(BigDecimal payment){
    this._balance = this._balance.add(payment);
  }

  /**
   * get_balance METHOD (PUBLIC)
   *
   * SIMPLY GETTER METHOD TO SHOW THE CURRENT BALANCE
   *
   * @return THE CURRENT BALANCE
   */
  public final BigDecimal get_balance(){
    return this._balance;
  }

  /**
   * collect money METHOD (PUBLIC)
   *
   * THIS METHOD ALLOWS VENDING MACHINE TO COLLECT ALL THE MONEY STORES INSIDE
   * THE MONEYBOX
   *
   * @return BALANCE OF MONEYBOX
   */
  public BigDecimal collect_money(){
    BigDecimal temp = this._balance;
    this._balance = new BigDecimal("0.0");
    return temp;
  }
}
