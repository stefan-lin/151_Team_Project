/**
 * Created by Lambda on 11/15/2015.
 */
public class DummyAccount extends Student{
  @Override
  public boolean checkID(String usrID, String usrPWD){
    return true;
  }

  // FACTORY METHOD
  //public Student create_section(String id){
  //  return new Student("TEST_NAME", "0001", "500.00");
  //}

  @Override
  public boolean pay(String id, double due_amount){
    // CHANGE BALANCE HERE
    return true;
  }

  @Override
  public double getBal(String id){
    return 100.0;
  } // get_balance

  @Override
  public String getStudentInfo(String id, String pwd){
    return "DUMMY STUDENT INFO";
  }
}
