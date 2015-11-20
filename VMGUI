import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

public class VMGUI extends JFrame{
	private VendingMachine v;
	private JTextField id;
	private JTextField password;
	private ArrayList<Integer> checkoutItems = new ArrayList<Integer>();

	public VMGUI(VendingMachine v){
		this.v = v;
		Container pane = getContentPane();
		pane.setLayout(new BorderLayout());
		pane.add(itemGrid(),BorderLayout.CENTER);
		pane.add(header(), BorderLayout.NORTH);	
		pane.add(statusBox(), BorderLayout.SOUTH);
		pane.add(checkout(), BorderLayout.EAST);
		pane.add(studentID(), BorderLayout.WEST);
	}
	/**
	 * North part of layout. 
	 * @return a JLabel
	 */
	public JComponent header(){
		JLabel label = new JLabel("Vending Machine", JLabel.CENTER);
		label.setFont(new Font("Courier", Font.BOLD, 36));
		return label;
	}
	
	public JComponent statusBox(){
		//JTextField field = new JTextField(v.get_usr_balance(id.getText()).toString());
		JTextField field = new JTextField("");

		field.setEditable(false);
		return field;
	}
	/**
	 * The Log In part part of the JFrame
	 * @return A JPanel that contains ID, PW, and LogIn Button
	 */
	public JComponent studentID(){
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());
		
		id = new JTextField("Enter ID");
		password = new JTextField("Enter PW");
		JButton logIn = new JButton("Log in");
		
		ActionListener bL = new ActionListener(){
			public void actionPerformed(ActionEvent event){
				
			}
		};
		
		logIn.addActionListener(bL);
		
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
		
		panel.add(id);
		panel.add(password);
		panel.add(logIn);
		return panel;
	}
	/**
	 * Display pictures, item name, and quantity 
	 * @return a grid layout containing all the items
	 */
	public JComponent itemGrid(){
		try{
			JPanel grid = new JPanel();
			grid.setLayout(new GridLayout(v.get_tray_item_info().size(),3));
			
			ImageIcon icon = new ImageIcon("coke.png");
			
			JLabel[] images = new JLabel[15];
			JRadioButton[] rButton = new JRadioButton[15];
			JTextField[] names = new JTextField[15];
			
			for (int i = 0; i < 12; i++){
				rButton[i] = new JRadioButton();
				grid.add(rButton[i]);
				
				if(rButton[i].isSelected()){
					checkoutItems.add(i);
				}
				
				images[i] = new JLabel(icon);
				//images[i].setToolTipText("This is " + v.get_item_info(i));
				images[i].setToolTipText("Coke");
				grid.add(images[i]);
				
				names[i] = new JTextField("Names");
				names[i].setEditable(false);
				//names[i] = new JTextField(v.get_item_info(i));
				grid.add(names[i]);
			
			}
			return grid;
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Checkout
	 * @return JPanel 
	 */
	public JComponent checkout(){
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());
		
		JButton checkoutButton = new JButton("Checkout");
		
		ActionListener bL = new ActionListener(){
			public void actionPerformed(ActionEvent event){
				v.check_out(checkoutItems);
			}
		};
		
		checkoutButton.addActionListener(bL);
		panel.add(checkoutButton);
		
		return panel;
	}
	
	/**
	 * Gets id that user enters into textfield
	 * @return id
	 */
	public String giveID(){
		return id.getText();
	}
	
	/**
	 * Gets pw that user enters into textfield
	 * @return pw
	 */
	public String givePassword(){
		return password.getText();
	}
	
	public static void main(String[] args){
		Account a = new Account();
		a.create_section("10");
		VendingMachine v = new VendingMachine(-1, a);
		VMGUI t = new VMGUI(v);
		
		t.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		t.setSize(500,500);
		t.setVisible(true);
	}

}
