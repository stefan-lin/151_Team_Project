/**
 * Created by Lambda on 11/15/2015.
 */
public class ItemInfoTuple {
  private String _name  = null;
  private String _price = null;
  private String _dir   = null;

  public ItemInfoTuple(String nm, String pc, String dr){
    this._name  = nm;
    this._price = pc;
    this._dir   = dr;
  } // END CONSTRUCTOR

  public String get(Index idx){
    switch(idx){
      case FIRST:
        return this._name;
      case SECOND:
        return this._price;
      case THIRD:
        return this._dir;
      default:
        return null;
    } // END SWITCH STATEMENT
  } // END get METHOD

}
