import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.SocketHandler;

/**
 * Created by Lambda on 11/16/2015.
 */
public class RequestServer {
  private String _domain = null;
  private int _port_num = -1;
  private ServerSocket _server_socket = null;
  private ExecutorService _thread_pool = null;
  private List<Future<String>> _list = null;

  private final int _NUM_THREADS = 32;

  // GUI //////////////////////////////////////////////////////////////////////
  //public JFrame frame;
  //public JTextArea text_area;
  //public JTextArea server_area;


  //private void _init_GUI(){
  //  frame = new JFrame("Requests Handling Server");
  //  frame.setPreferredSize(new Dimension(
  //      Toolkit.getDefaultToolkit().getScreenSize().height * 2 / 3,
  //      Toolkit.getDefaultToolkit().getScreenSize().width * 2 / 3
  //  ));
  //  frame.setLayout(new FlowLayout());

  //  text_area = new JTextArea(30, 50);
  //  server_area = new JTextArea(30, 50);

  //  text_area.setBorder(new TitledBorder("Slave"));
  //  server_area.setBorder(new TitledBorder("Master"));
  //  text_area.setBackground(Color.YELLOW);
  //  server_area.setBackground(Color.BLUE);

  //  text_area.setEnabled(false);
  //  frame.add(text_area);
  //  frame.add(server_area);

  //  frame.pack();
  //  frame.setVisible(true);
  //}
  /////////////////////////////////////////////////////////////////////////////

  public RequestServer(String domain, int port_num){
    this._domain = domain;
    this._port_num = port_num;

    this._init();
    //this._init_GUI();
    this._run_server();
  }

  private void _init(){
    this._thread_pool = Executors.newFixedThreadPool(this._NUM_THREADS);
    try {
      this._server_socket = new ServerSocket(
          this._port_num, 0, InetAddress.getByName(null)
      );
    }
    catch (IOException e) {
      e.printStackTrace();
    } // END TRY-CATCH
    this._list = new ArrayList<Future<String>>();
  } // END init METHOD

  private void _run_server(){
    System.out.println("WAITING FOR CONNECTION....");
    //server_area.append("WAITING FOR CONNECTION....\n");
    while(true){
      Socket client = null;
      try{
        client = this._server_socket.accept();
        System.out.println("RELIEVED A CLIENT");
        //synchronized (server_area) {
        //  server_area.append("RELIEVED A CLIENT\n");
        //}
      }
      catch(IOException e){
        e.printStackTrace();
      }

      // CREATE WORKER
      this._list.add(this._thread_pool.submit(
          new Worker(client)
      ));

      if(this._list.size() == 5){
        //System.out.println("GOT 5 REQUESTS!");
        synchronized (this._list){
          //System.out.println("List size : " + this._list.size());
          for(Future<String> fu: this._list){
            try {
              System.out.println("TEMP --->" + fu.get());
              //synchronized (server_area) {
              //  server_area.append(fu.get() + "\n");
              //}
            } catch (InterruptedException e) {
              e.printStackTrace();
            } catch (ExecutionException e) {
              e.printStackTrace();
            }
          }
          this._list.clear();
          //System.out.println("List size : " + this._list.size());
        } // END synchronized BLOCK
      }

    } // END WHILE
  } // END _run_server METHOD

  public class Worker implements Callable {
    private Socket _client_socket = null;
    private String _result = null;

    public Worker(Socket client){
      this._client_socket = client;
    }

    @Override
    public String call(){
      try {
        InputStream input  = this._client_socket.getInputStream();
        System.out.println("LocaleHost OK.");
        //synchronized (text_area) {
        //  text_area.append("LocaleHost OK.\n");
        //}
        this._result = new Scanner(input, "UTF-8").useDelimiter("\\A").next();
        System.out.println("REQUEST : " + this._result);
        input.close();
        //synchronized (text_area) {
        //  text_area.append(this._result + "\n");
        //}
        return this._result;
      }
      catch (IOException e) {
        e.printStackTrace();
      } // END TRY-CATCH
      return null;
    } // END run METHOD

    public final String get_result(){
      return this._result;
    }
  } // END Worker CLASS
  ///////////////////////////// TEST //////////////////////////////////////////
  public static void main(String[] args) {
    RequestServer s = new RequestServer("localhost", 8080);
    //s.display_requests();
  }
} // END RequestServer CLASS
