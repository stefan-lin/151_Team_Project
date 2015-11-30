import javax.swing.*;
import java.awt.*;

/**
 * Created by Lambda on 11/26/2015.
 */
public class VM_GUI {
  private JFrame _frame            = null;
  private JPanel _login_root_panel = null;
  private JPanel _login_sub_panel  = null;
  private JPanel _root_panel       = null;
  private JPanel _list_panel       = null;
  private JPanel _dlpy_panel       = null;
  private JPanel _bttn_panel       = null;

  private JLabel _id_label  = null;
  private JLabel _pwd_label = null;
  private JTextField _id_tField  = null;
  private JTextField _pwd_tField = null;

  public VM_GUI(){

  }

  private void _init_login_gui(){
    this._login_root_panel = new JPanel();
    this._login_root_panel.setBackground(Color.GRAY);
    this._login_sub_panel = new JPanel();
    this._login_root_panel.setPreferredSize(new Dimension(
        150, 150
    ));
    this._id_label  = new JLabel("ID  : ");
    this._pwd_label = new JLabel("PWD : ");
    this._id_tField  = new JTextField();
    this._pwd_tField = new JTextField();
  }
}
