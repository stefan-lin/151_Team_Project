import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Lambda on 11/22/2015.
 */
public class VM_Tester {
  public ArrayList<String> init_input() {
    String item0  = "coke:1:1.75:21:/desktop/pictures/1.jpg:null:null:SODA:n160:";
    String item1  = "pepsi:2:1.75:14:/desktop/pictures/2.jpg:null:null:SODA:160:";
    String item2  = "crush:3:1.75:10:/desktop/pictures/3.jpg:null:null:SODA:155:";
    String item3  = "royal:4:2.25:15:/desktop/pictures/4.jpg:null:null:SODA:170:";
    String item4  = "Peanut M&M's:5:1.75:9:/desktop/pictures/5.jpg:null:null:SNACK:250:";
    //String item23 = "Boston Baked Beans:6:2.5:12:/desktop/pictures/6.jpg:null:null:SNACK:200:";
    String item5  = "item11:11:9.21:5:/desktop/pictures/11.jpg:Smith:1111:GRADE_BOOK:0:";
    String item6  = "Cheetos Crunchy:7:1.75:30:/desktop/pictures/7.jpg:null:null:SNACK:160:";
    String item7  = "Sun Chips Original:8:1.75:30:/desktop/pictures/8.jpg:null:null:SNACK:140:";
    String item8  = "Mini Pretzels:9:1.25:30:/desktop/pictures/9.jpg:null:null:SNACK:110:";
    String item9  = "Fig Newtons:10:1.5:30:/desktop/pictures/10.jpg:null:null:SNACK:200:";
    String item10 = "grade_book1:12:11.11:20:/desktop/pictures/12.jpg:Thomas:1212:BRADE_BOOK:0:";
    //String item11 = "grade_book2:13:7.42:10:/desktop/pictures/13.jpg:Michael:1313:GRADE_BOOK:0:";
    //String item12 = "grade_book3:14:10.32:18:/desktop/pictures/14.jpg:Johnson:1414:GRADE_BOOK:0:";
    String item13 = "sprite:15:1.75:30:/desktop/pictures/15.jpg:::SODA:200:";
    String item14 = "7-up:16:3.25:30:/desktop/pictures/16.jpg:::SODA:140:";
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
    input.add(item14);

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

  public static void main(String[] args) {
    VM_Tester vm_tester = new VM_Tester();
    ArrayList<String> inputArr = vm_tester.init_input();
    vm_tester.parseString(inputArr);
  }
}

/**
 * coke:1:1.75:21:/desktop/pictures/1.jpg:null:null:SODA:null:
 * pepsi:2:1.75:14:/desktop/pictures/2.jpg:null:null:SODA:null:
 * crush:3:1.75:10:/desktop/pictures/3.jpg:null:null:SODA:null:
 * royal:4:2.25:15:/desktop/pictures/4.jpg:null:null:SODA:null:
 * Peanut M&M's:5:1.75:9:/desktop/pictures/5.jpg:null:null:SNACK:250:
 * Boston Baked Beans:6:2.5:12:/desktop/pictures/6.jpg:null:null:SNACK:200:
 * item11:11:9.21:5:/desktop/pictures/11.jpg:Smith:1111:GRADE_BOOK:0:
 * Cheetos Crunchy:7:1.75:30:/desktop/pictures/7.jpg:null:null:SNACK:160:
 * Sun Chips Original:8:1.75:30:/desktop/pictures/8.jpg:null:null:SNACK:140:
 * Mini Pretzels:9:1.25:30:/desktop/pictures/9.jpg:null:null:SNACK:110:
 * Fig Newtons:10:1.5:30:/desktop/pictures/10.jpg:null:null:SNACK:200:
 * grade_book1:12:11.11:20:/desktop/pictures/12.jpg:Thomas:1212:BRADE_BOOK:0:
 * grade_book2:13:7.42:10:/desktop/pictures/13.jpg:Michael:1313:GRADE_BOOK:0:
 * grade_book3:14:10.32:18:/desktop/pictures/14.jpg:Johnson:1414:GRADE_BOOK:0:
 * sprite:15:1.75:30:/desktop/pictures/15.jpg:::SODA:0:
 * 7-up:16:3.25:30:/desktop/pictures/16.jpg:::SODA:0:
 * Mountain Dew:17:3.25:30:/desktop/pictures/17.jpg:::SODA:0:
 * coca cola:18:3.25:30:/desktop/pictures/18.jpg:::SODA:0:
 * diet coke:19:1.75:30:/desktop/pictures/19.jpg:::SODA:0:
 * diet pepsi:20:1.75:30:/desktop/pictures/20.jpg:::SODA:0:
 * trident:21:1.99:30:/desktop/pictures/21.jpg:::GUM:0:
 * extra:22:1.99:30:/desktop/pictures/22.jpg:::GUM:
 * 0:stride:23:2.59:30:/desktop/pictures/23jpg:::GUM:0:
 * eclipse:24:3.99:30:/desktop/pictures/24.jpg:::GUM:0:
 */
