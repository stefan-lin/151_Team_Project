import java.io.IOException;
import java.net.ServerSocket;

/**
 * Created by Lambda on 11/16/2015.
 */
public class RequestServer {
  public static void main(String[] args) {
    String  request_message  = null;
    String  response_message = null;
    Integer port_number      = 8800;

    try {
      ServerSocket server = new ServerSocket(port_number);
      System.out.println("Listening to port : " + port_number.toString());

      while(true){

      } // END WHILE LOOP

    } catch (IOException e) {
      String error_fmt = "[ERROR] FAIL TO LISTEN TO PORT %d";
      System.err.println(String.format(error_fmt, port_number));
      e.printStackTrace();
    }
  }
}
