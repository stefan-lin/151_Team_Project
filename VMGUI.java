import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.*;

public class VMGUI extends JFrame{
  private VendingMachine v;
  private JTextField id;
  private JPasswordField password;
  private ArrayList<Integer> checkoutItems = new ArrayList<Integer>();

  public VMGUI(VendingMachine v){
    this.v = v;
    initialize();
  }

  /**
   * Initialize the vending machine
   */
  private void initialize(){
    Container pane = getContentPane();
    pane.setLayout(new BorderLayout());
    pane.add(itemGrid(),BorderLayout.WEST);
    pane.add(header(), BorderLayout.NORTH);
    //pane.add(statusBox(), BorderLayout.SOUTH);
    pane.add(checkout(), BorderLayout.EAST);
    pane.add(studentID(), BorderLayout.SOUTH);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(500,500);
    setVisible(true);
  }

  /**
   * North part of layout.
   * @return JLabel
   */
  private JComponent header(){
    JLabel label = new JLabel("Vending Machine", JLabel.CENTER);
    label.setFont(new Font("Courier", Font.BOLD, 36));
    return label;
  }
  //ignore for now
  private JComponent statusBox(){
    //JTextField field = new JTextField(v.get_usr_balance(id.getText()).toString());
    JTextField field = new JTextField("");

    field.setEditable(false);
    return field;
  }
  /**
   * The Log In part part of the JFrame
   * @return JPanel that contains ID, PW, and LogIn Button
   */
  private JComponent studentID(){
    JPanel panel = new JPanel();
    panel.setLayout(new FlowLayout());
    JLabel enterID = new JLabel("Enter ID");
    JLabel enterPW = new JLabel("Enter PW");

    id = new JTextField("1");
    password = new JPasswordField("        ");
    JButton logIn = new JButton("Log In");

    ActionListener logInListener = new ActionListener(){
      public void actionPerformed(ActionEvent event){
        char[] input = password.getPassword();
        //System.out.println("[" + id.getText() + "]");
        //System.out.println("[" + String.valueOf(input) + "]");
        if(v.log_in(id.getText(), String.valueOf(input)) == false){
          JOptionPane.showMessageDialog(null, "Incorrect ID or Password", "Error", JOptionPane.ERROR_MESSAGE);
        }
        else{
          JOptionPane.showMessageDialog(null, "Log In Successful", "Success", JOptionPane.INFORMATION_MESSAGE);
          Arrays.fill(input, '0');
        }
      }
    };

    logIn.addActionListener(logInListener);

    //MouseListeners to clear the text box
    id.addMouseListener(new MouseAdapter(){
      public void mouseClicked(MouseEvent e){
        id.setText("");
      }
    });

    password.addMouseListener(new MouseAdapter() {
      public void mouseClicked(MouseEvent e){
        password.setText("");
      }
    });

    panel.add(enterID);
    panel.add(id);
    panel.add(enterPW);
    panel.add(password);
    panel.add(logIn);
    return panel;
  }
  /**
   * Display pictures and item name
   * @return grid layout containing all the items
   */
  private JComponent itemGrid(){
    try{
      JPanel grid = new JPanel();
      grid.setLayout(new GridLayout(v.get_tray_item_info().size(),3));

      //ImageIcon icon = new ImageIcon("coke.png");

      JLabel[] images = new JLabel[15];
      JRadioButton[] rButton = new JRadioButton[15];
      JTextField[] names = new JTextField[15];
      ButtonGroup group = new ButtonGroup();

      ArrayList<ItemInfoTuple> item_list = v.get_tray_item_info();

      for (int i = 1; i < 13; i++){ //change 12 later to tray size
        rButton[i] = new JRadioButton();

        ActionListener radioL = new ActionListener(){
          public void actionPerformed(ActionEvent event){
            int i = 1;
            while (i < 13){
              if(rButton[i].isSelected()){
                checkoutItems.add(i);
                i = 14;
              }
              else if (!rButton[i].isSelected()){
                i++;
                checkoutItems.removeAll(checkoutItems);
              }
            } //END WHILE
          }
        };
        rButton[i].addActionListener(radioL);
        group.add(rButton[i]);
        grid.add(rButton[i]);

        ImageIcon icon = new ImageIcon(
            item_list.get(i-1).get(Index.ICON_DIRECTORY)
        );
        images[i] = new JLabel(icon);
        //images[i].setToolTipText("This is " + v.get_item_info(i));
        images[i].setToolTipText(
            v.get_item_info(i-1)
        );
        grid.add(images[i]);

        //names[i] = new JTextField("Names");
        //names[i] = new JTextField(v.get_item_info(i));
        names[i] = new JTextField(item_list.get(i-1).get(Index.NAME));
        names[i].setEditable(false);
        grid.add(names[i]);

      } //END FOR
      return grid;
    }
    catch(NullPointerException e){
      e.printStackTrace();
    }
    catch (Exception e){
      e.printStackTrace();
    }
    return null;
  }

  /**
   * A checkout button
   * @return JPanel with checkout button
   */
  private JComponent checkout(){
    try{
      JPanel panel = new JPanel();
      panel.setLayout(new FlowLayout());

      JButton checkoutButton = new JButton("Checkout");

      ActionListener bL = new ActionListener(){
        public void actionPerformed(ActionEvent event){
          v.check_out(id.getText(), checkoutItems);
          //System.out.println(checkoutItems);

        }
      };

      checkoutButton.addActionListener(bL);
      panel.add(checkoutButton);

      return panel;
    }
    catch(NullPointerException e){
      System.out.println("Empty");
    }
    catch(Exception e){
      e.printStackTrace();
    }
    return null;
  }

  /**
   * Gets id that user enters into textfield
   * @return id
   */
  protected String getID(){
    return id.getText();
  }

  /**
   * Gets pw that user enters into textfield
   * @return pw
   */
  protected String getPassword(){
    char[] input = password.getPassword();
    return input.toString();
  }

  //public static void main(String[] args){
  //  //Account a = new Account();
  //  //a.create_section("10");
  //  //VendingMachine v = new VendingMachine(-1, a);
  //  VendingMachine vm = new VendingMachine(
  //      1, new DummyAccount(), new DummyDatabase()
  //  );
//
  //  VMGUI t = new VMGUI(vm);
//
  //  //System.out.println(t.checkoutItems);
  //}

}