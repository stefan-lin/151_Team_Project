import java.math.BigDecimal;

/**
 * Created by Lambda on 11/15/2015.
 */
public class Student {
  private String     _name = null;
  private String     _id   = null;
  private BigDecimal _balance = null;

  public Student(String name, String id, String bal){
    this._name    = name;
    this._id      = id;
    this._balance = new BigDecimal(bal);
  }

  public final String get_id(){
    return this._id;
  }

  public final BigDecimal get_balance(){
    return this._balance;
  }

  public final String get_name(){
    return this._name;
  }
}
