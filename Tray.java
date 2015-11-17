import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by Lambda on 11/15/2015.
 */
public class Tray {
  private final int   _TRAY_SIZE = 24;
  private Queue<Item> _tray      = null;
  private int         _tray_num  = -1;

  public Tray(int id){
    this._tray_num = id;
    this._tray = new LinkedList<Item>();
  }

  public final Integer get_number_of_remaining_item() {
    return this._tray.size();
  }

  public final boolean is_full(){
    return (this._tray.size() == _TRAY_SIZE)? true: false;
  }

  public final boolean is_empty(){
    return (this._tray.size() == 0)? true: false;
  }

  public void push(Item item){
    if(!this.is_full()) {
      this._tray.add(item);
    }
    throw new IndexOutOfBoundsException("[ERROR] TRY IS FULL.\n");
  }

  public Item pop(){
    if(!this.is_empty()) {
      return this._tray.remove();
    }
    throw new IndexOutOfBoundsException("[ERROR] TRAY IS EMPTY.\n");
  }

  public ItemInfoTuple get_item_tuple(){
    Item item_ref = this._tray.peek();
    return new ItemInfoTuple(
      item_ref.get_name(),
      CurrencyConvertor.conver_to_USD_fmt(item_ref.get_price().doubleValue()),
      item_ref.get_icon_path()
    );
  }

  public final String get_item_info(){
    if(this._tray.size() != 0){
      return this._tray.peek().get_info();
    }
    throw new ArrayIndexOutOfBoundsException("EMPTY TRAY.\n");
  }

  public String get_tray_info(){
    String tray_fmt =
        "Tray Number    : %d\n" +
        "Max Size       : %d\n" +
        "Item Left      : %d\n" +
        "Available Room : %d\n" +
        "Stored Item ID : %d\n";

    String return_str = String.format(
        tray_fmt, this._tray_num,
        this._TRAY_SIZE, this._tray.size(),
        this._TRAY_SIZE-this._tray.size(), this._tray.peek().get_id()
    );
    return return_str;
  }

  public final BigDecimal get_item_price(){
    //return this._tray.peek().get_price();
    PriceInterface pInterface = null;
    switch(this._tray.peek().get_type()){
      case SODA:
        pInterface = new RecycleTaxableItem(this._tray.peek());
        break;
      case GRADE_BOOK:
        pInterface = this._tray.peek();
        break;
    }
    return pInterface.get_price();
  }

  public final String get_containing_product_id(){
    return new Integer(_tray.peek().get_id()).toString();
  }

  public final Integer get_max_tray_size(){
    return _TRAY_SIZE;
  }
}
