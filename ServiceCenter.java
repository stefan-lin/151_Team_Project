import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by Lambda on 11/16/2015.
 */

/**
 * ServiceCenter CAN TALK TO DATABASE FOR UPDATING ALL THE
 * SALE RECORDS
 */
public class ServiceCenter implements Observer {
  private final String _account     = "12345";
  private Socket       _socket      = null;
  private int          _port_num    = -1;
  private String       _domain_name = null;

  public ServiceCenter(String domain, int port_num) throws IOException {
    this._port_num    = port_num;
    this._domain_name = domain;
    this._socket      = new Socket(domain, port_num); // FOR TESTING PURPOSE
    this._socket.close();
    this._socket      = null;
  }

  @Override
  public void update(Observable o, Object arg) {
    if(o instanceof VendingMachine){
      String request_message = this._make_request_str(
          ((VendingMachine) o).restock_check()
      );
      try {
        this._make_request_to_server(request_message);
      } catch (IOException e) {
        String err_fmt = "[ERROR] FAIL TO TALK TO (DOMAIN : %s, SOCKET : %d)\n";
        System.err.println(String.format(
            err_fmt, this._domain_name, this._port_num
        ));
        e.printStackTrace();
      }
    } // END IF
  }

  private String _make_request_str(ArrayList<Pair<String, Integer>> list){
    String message = this._account;
    for(Pair<String, Integer> p : list){
      message += ":";
      message += p.get_left();
      message += ":";
      message += p.get_right().toString();
      message += ":";
    } // END FOR
    return message;
  } // END _make_request_str METHOD

  private void _make_request_to_server(String message) throws IOException {
    this._socket = new Socket(this._domain_name, this._port_num);
    OutputStream output_hdlr = this._socket.getOutputStream();
    output_hdlr.write(message.getBytes());
    output_hdlr.flush();
    output_hdlr.close();
    this._socket.close();
  }

  public void disconnect_socket() throws IOException {
    this._socket.close();
  }
}
