import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Lambda on 11/25/2015.
 */
public class DummyDatabase extends DatabaseConnection{
  private ArrayList<String> _dummy_item_data = null;
  private HashMap<Integer, String> _dummy_student_data = null;
  private HashMap<Integer, Double> _dummy_balance_data = null;

  public DummyDatabase(){
    this._dummy_item_data = init_item_input();
    this._dummy_student_data = init_stdt_input();
    this._dummy_balance_data = init_balance_input();
  }

  public String getStudentPassword(int inputID){
    if(this._dummy_student_data.containsKey(inputID)){
      return this._dummy_student_data.get(inputID);
    }
    return null;
  }

  public double getStudentBalance(int inputID){
    if(this._dummy_balance_data.containsKey(inputID)){
      return this._dummy_balance_data.get(inputID);
    }
    return -1;
  }

  // displays the data of Student table
  public String getStudentData(int studentID, String password){
    return "TEST DUMMY DATA";
  }

  // ----> checkItemLeave method (no need)

  public String getItemData(String itemID){
    for(String str : this._dummy_item_data){
      String[] fields = str.split(":");
      if(fields[1].compareTo(itemID) == 0){
        return str;
      }
    }
    return null;
  }

  public ArrayList<String> getAllItemInformation(){
    return this._dummy_item_data;
  }

  // getMoneyBoxData method (no need)?

  public void updateStudent(int IDnumber, double newData){
    if(this._dummy_balance_data.containsKey(IDnumber)){
      this._dummy_balance_data.replace(IDnumber, newData);
    }
  }

  // updateItems method ??

  public HashMap<Integer, Double> init_balance_input(){
    HashMap<Integer, Double> map = new HashMap<>();
    map.put(1, 100.00);
    map.put(2, 200.00);
    map.put(3, 300.00);
    return map;
  }

  public HashMap<Integer, String> init_stdt_input(){
    String pwd1 = "1234";
    String pwd2 = "2345";
    String pwd3 = "3456";
    HashMap<Integer, String> hash_table = new HashMap<>();
    hash_table.put(1, pwd1);
    hash_table.put(2, pwd2);
    hash_table.put(3, pwd3);

    return hash_table;
  }

  public ArrayList<String> init_item_input() {
    String item0  = "coke:1:1.75:6:/desktop/pictures/1.jpg:null:null:SODA:160:";
    String item1  = "pepsi:2:1.75:6:/desktop/pictures/2.jpg:null:null:SODA:160:";
    String item2  = "crush:3:1.75:6:/desktop/pictures/3.jpg:null:null:SODA:155:";
    String item3  = "royal:4:2.25:6:/desktop/pictures/4.jpg:null:null:SODA:170:";
    String item4  = "Peanut M&M's:5:1.75:6:/desktop/pictures/5.jpg:null:null:SNACK:250:";
    //String item23 = "Boston Baked Beans:6:2.5:12:/desktop/pictures/6.jpg:null:null:SNACK:200:";
    String item5  = "item11:11:9.21:6:/desktop/pictures/11.jpg:Smith:1111:GRADE_BOOK:0:";
    String item6  = "Cheetos Crunchy:7:1.75:6:/desktop/pictures/7.jpg:null:null:SNACK:160:";
    String item7  = "Sun Chips Original:8:1.75:6:/desktop/pictures/8.jpg:null:null:SNACK:140:";
    String item8  = "Mini Pretzels:9:1.25:6:/desktop/pictures/9.jpg:null:null:SNACK:110:";
    String item9  = "Fig Newtons:10:1.5:10:/desktop/pictures/10.jpg:null:null:SNACK:200:";
    String item10 = "grade_book1:12:11.11:6:/desktop/pictures/12.jpg:Thomas:1212:GRADE_BOOK:0:";
    //String item11 = "grade_book2:13:7.42:10:/desktop/pictures/13.jpg:Michael:1313:GRADE_BOOK:0:";
    //String item12 = "grade_book3:14:10.32:18:/desktop/pictures/14.jpg:Johnson:1414:GRADE_BOOK:0:";
    String item13 = "sprite:15:1.75:2:/desktop/pictures/15.jpg:::SODA:200:";
    //String item14 = "7-up:16:3.25:8:/desktop/pictures/16.jpg:::SODA:140:";
    //String item15 = "Mountain Dew:17:3.25:30:/desktop/pictures/17.jpg:::SODA:0:";
    //String item16 = "coca cola:18:3.25:30:/desktop/pictures/18.jpg:::SODA:0:";
    //String item17 = "diet coke:19:1.75:30:/desktop/pictures/19.jpg:::SODA:0:";
    //String item18 = "diet pepsi:20:1.75:30:/desktop/pictures/20.jpg:::SODA:0:";
    //String item19 = "trident:21:1.99:30:/desktop/pictures/21.jpg:::GUM:0:";
    //String item20 = "extra:22:1.99:30:/desktop/pictures/22.jpg:::GUM:0:";
    //String item21 = "stride:23:2.59:30:/desktop/pictures/23jpg:::GUM:0:";
    //String item22 = "eclipse:24:3.99:30:/desktop/pictures/24.jpg:::GUM:0:";

    ArrayList<String> input = new ArrayList<>(12);
    input.add(item0);
    input.add(item1);
    input.add(item2);
    input.add(item3);
    input.add(item4);
    input.add(item5);
    input.add(item6);
    input.add(item7);
    input.add(item8);
    input.add(item9);
    input.add(item10);
    input.add(item13);
    //input.add(item14);

    return input;
  }

  public void parseString(ArrayList<String> input){
    for(String str : input){
      String[] arr = str.split(":");
      System.out.println("====================================");
      System.out.println("Size : " + arr.length);
      for(String token : arr){
        System.out.print("[" + token + "], ");
      }
      System.out.println();
      System.out.println("------------------------------------");
    }
  }

  // TESTED OK!
  public static void main(String[] args) {
    DummyDatabase dd = new DummyDatabase();
    for(String str : dd.getAllItemInformation()){
      System.out.println(str);
    }
    System.out.println("-------------------------------------------");
    for(int idx=1; idx<15; idx++){
      String str = dd.getItemData(Integer.toString(idx));
      if(str != null){
        System.out.println(str);
      }
      else {
        System.out.println("ID NOT EXISTS");
      }
    }
    System.out.println("-------------------------------------------");

  }
}
