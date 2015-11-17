/**
 * Created by Lambda on 11/15/2015.
 */
public class Account {

  public boolean checkID(String usrID, String usrPWD){
    return true;
  }

  // FACTORY METHOD
  public Student create_section(String id){
    return new Student("TEST_NAME", "0001", "500.00");
  }

  public boolean pay(String id, Double due_amount){
    // CHANGE BALANCE HERE
    return true;
  }
}
