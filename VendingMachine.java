import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Observable;

/**
 * Created by Lambda on 11/15/2015.
 */
public class VendingMachine extends Observable{
  private static final int       _NUM_OF_TRAYS = 24;
  private static ArrayList<Tray> _tray_lists   = new ArrayList<>(_NUM_OF_TRAYS);
  private int                    _vm_num       = -1;
  private static MoneyBox        _money_box    = new MoneyBox();
  private Account                _account_hdlr = null;
  private Student                _curr_stdt_ref= null;
  private ArrayList<Transaction> _transactions = null;

  private void _init(){
    /**
     * INITIALIZE TRAYS
     */

  }

  public VendingMachine(int vm_id, Account accnt){
    this._vm_num = vm_id;
    this._account_hdlr = accnt;
    this._transactions = new ArrayList<>();
    this._init();
  }

  // GUI RELATED

  /**
   * log_in METHOD PUBLIC
   *
   * USER LOG IN - CREATE A STUDENT OBJECT AND SAVE IT FOR LATER USE. IF EITHER
   * ID OR PASSWORD IS INCORRECT, RETURN FALSE ELSE CREATE STUDENT OBJECT AND
   * RETURN TRUE
   *
   * @param id  STUDENT ID NUMBER (STRING)
   * @param pwd STUDENT PASSWORD (STRING)
   * @return TRUE/FALSE INDICATES IF THE ID AND PASSWORD ARE CORRECT
   */
  public boolean log_in(String id, String pwd){
    if(this._account_hdlr.checkID(id, pwd)){
      this._curr_stdt_ref = this._account_hdlr.create_section(id);
      return true;
    }
    return false;
  }

  /**
   *
   * THIS METHOD PROVIDES GUI ACCESS TO THE ITEM INFORMATION FOR HOVER DISPLAY.
   *
   * @param tray_id
   * @return
   */
  public String get_item_info(int tray_id){
    Tray tray_ref = null;
    if(tray_id <= _NUM_OF_TRAYS && tray_id > 0) {
      tray_ref = this._tray_lists.get(tray_id - 1);
      return tray_ref.get_item_info();
    }
    throw new ArrayIndexOutOfBoundsException("TRAY LIST OUT OF BOUND.\n");
  }

  /**
   *
   * THIS METHOD PROVIDE THE NECESSARY INFORMATION ABOUT THE ITEM STORES IN
   * ALL TRAYS - FOR GUI (BUILDING ITEM LIST)
   *
   * @return
   */
  public ArrayList<ItemInfoTuple> get_tray_item_info(){
    ArrayList<ItemInfoTuple> list = new ArrayList<>(_NUM_OF_TRAYS);
    for(Tray t : _tray_lists){
      if(t != null) {
        list.add(t.get_item_tuple());
      }
      else{
        list.add(null);
      }
    }
    return list;
  }

  public BigDecimal calculate_total(ArrayList<Integer> item_list){
    BigDecimal total = new BigDecimal(0.0);
    for(Integer i : item_list){
      total.add(_tray_lists.get(i).get_item_price());
    }
    return total;
  }


  public ArrayList<Item> check_out(ArrayList<Integer> selected_items){
    BigDecimal total_price = this.calculate_total(selected_items);
    /**
     * VALIDATE THE USER BALANCE
     */
    if(this._account_hdlr.pay(
        this._curr_stdt_ref.get_name(),
        total_price.doubleValue()
    )){
      // BALANCE VALIDATED
      this._money_box.collect_payment(total_price);
      // DELIVER
      ArrayList<Item> deliver_items = new ArrayList<>();
      for(Integer i : selected_items){
        deliver_items.add(_tray_lists.get(i).pop());
      } // END FOR LOOP

      // RECORD TRANSACTION
      this._transactions.add(new Transaction(
          this._curr_stdt_ref.get_name(),
          CurrencyConvertor.conver_to_USD_fmt(total_price.doubleValue()),
          deliver_items
      ));

      // SELF CHECK IF INVENTORY IS LOW
      if(this._is_inventory_low()){
        this.setChanged();
        this.notifyObservers();
      }

      return deliver_items;
    }
    // INSUFFICIENT AMOUNT
    return null;
  } // END check_out METHOD

  public void log_out(){
    this._curr_stdt_ref = null;
  }

  public BigDecimal get_usr_balance(String id){
    if(this._curr_stdt_ref != null &&
       (id.compareTo(this._curr_stdt_ref.get_id()) != 0)){
      this._curr_stdt_ref = this._account_hdlr.create_section(id);
    }
    return this._curr_stdt_ref.get_balance();
  }

  private boolean _is_inventory_low(){
    int empty_counter = 0;
    int low_inv_cntr  = 0;

    for(Tray t : _tray_lists){
      switch (t.get_number_of_remaining_item()){
        case 0:
          empty_counter++;
          break;
        case 5:
          low_inv_cntr++;
          break;
        default:
      } // END SWITCH
    } // END FOR

    return (empty_counter >= 3 || low_inv_cntr >= 6)? true: false;
  } // END is_inventory_low METHOD

  public ArrayList<Pair<String, Integer>> restock_check(){
    ArrayList<Pair<String, Integer>> restock_list = new ArrayList<>();
    for(Tray t : _tray_lists){
      if(t.get_number_of_remaining_item() <=5){
        restock_list.add(new Pair<>(
            t.get_containing_product_id(),
            t.get_max_tray_size()-t.get_number_of_remaining_item()
        ));
      } // END IF
    } // END FOR
    return restock_list;
  } // END restock_check METHOD
}
