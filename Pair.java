import java.util.Objects;

/**
 * Created by Lambda on 11/16/2015.
 */
public class Pair<L, R> {
  private final L _left;
  private final R _right;

  public Pair(L item1, R item2){
    this._left  = item1;
    this._right = item2;
  } // END CONSTRUCTOR

  public final L get_left(){
    return this._left;
  }
  public final R get_right(){
    return this._right;
  }

  @Override
  public int hashCode(){
    return this._left.hashCode() ^ this._right.hashCode();
  } // END hashCode METHOD

  @Override
  public boolean equals(Object o){
    if(!(o instanceof Pair)){
      return false;
    }
    else{
      Pair comparee = (Pair) o;
      return this._left.equals(comparee._left) &&
             this._right.equals(comparee._right);
    }
  } // END equals METHOD
}
