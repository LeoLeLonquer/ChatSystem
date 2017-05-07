package view; 
import model.User;
import controller.* ; 
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.Inet4Address;
import java.net.UnknownHostException;

import javax.swing.*;

/* current things to fix:
 * when clicking on the "User", the window should launch once... and then when the chat closes, should be able to open it again
 * little "are you sure?" windows when clicking on the closing button
 * how to not close all windows by closing one? or perhaps that's not important 
 * make the listUsers prettier bc it's ugly af 
 */

public class LoginWindow extends JFrame implements ActionListener{
		
		 private JLabel enterUser;
		 // text areas
		 private JTextArea loginArea;
		 // a button to perform an action: e.g. say hello (TBD) */
		 private JButton login;
		 private String currentUser; 
//		 private Controller controller; 
		 private boolean connected; 
		 private Interface itf; 
	 
	 public LoginWindow (Interface itf, String titre) { 
		 super(titre); 
		 this.connected = false; 
		 this.itf = itf; 
		 
		 initComponents();
	 }
	 
	 public String getCurrentUs(){ 
		 return this.currentUser;
	 }
	 
	 public boolean getStatus(){
		 return this.connected; 
	 }

	 /** Initializes the window components */

	 private void initComponents() {
	 // create the components
	 // a new label with the "Nom" as value
	 enterUser = new JLabel("Enter username: ");
	 JLabel bouchetrou = new JLabel(""); 
	 // a new text field with 20 columns
	 loginArea = new JTextArea(); 
	 // a new button identified as OK
	 login = new JButton("Log in");
	 login.addActionListener(this); 
	 PressEnter (this.loginArea, this); 
	 

	 // configures the JFrame layout using a grid layout
	 this.setLayout(new GridLayout(2,2)); // 1 ligne 3 colonnes 
	 // places the components in the layout
	 this.add(enterUser); 
	 this.add(bouchetrou);
	 this.add(loginArea); 
	 this.add(login); 

	 this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE) ; 
	 this.setSize(300, 150);
     this.setLocationRelativeTo(null);
	 // the JFrame is visible now
	 this.setVisible(true);
	 }

	 
	 private void PressEnter(JTextArea loginArea, LoginWindow lw){ 
			this.loginArea.addKeyListener(new KeyListener(){
			@Override
			public void keyPressed(KeyEvent e) {		    	
				if(e.getKeyCode() == KeyEvent.VK_ENTER){					
					String currentUser = loginArea.getText(); 
					lw.connected = true; 
					lw.currentUser= currentUser; 
					lw.itf.setCurrentUs(currentUser);
					lw.itf.launchListUsers();
						//ListUsers checkList =  new ListUsers(currentUser, TestView.uneListeTest ());
				

				lw.setVisible(false);
				}		
			}
			@Override
			public void keyReleased(KeyEvent e) {
			}

			@Override
			public void keyTyped(KeyEvent e) {			
			}	
		});
	 }
	 
	//action performed 
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==login ) {
			this.currentUser = this.loginArea.getText(); 
			this.connected = true; 
//			ListUsers checkList = new ListUsers(this.currentUser, TestView.uneListeTest ());
			this.itf.launchListUsers();
			this.itf.setCurrentUs(currentUser);
			this.setVisible(false);		
			
		}
	}

}

