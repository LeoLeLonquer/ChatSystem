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
		 private Controller controller; 
		 
	 public LoginWindow (Controller controller, String titre) { 
		 super(titre); 
		 this.controller= controller; 
		 initComponents();
	 }
	 
	 public String getCurrentUs(){ 
		 return this.currentUser;
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
	 PressEnter (this.currentUser, this.loginArea, this); 
	 

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

	 
	 private void PressEnter(String currentUser, JTextArea loginArea, LoginWindow lw){ 
			this.loginArea.addKeyListener(new KeyListener(){
			@Override
			public void keyPressed(KeyEvent e) {		    	
				if(e.getKeyCode() == KeyEvent.VK_ENTER){					

			controller.initUs(currentUser);
			 System.out.print(currentUser);

				ListUsers checkList = new ListUsers(controller, currentUser);
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

				controller.initUs(this.currentUser);				
				ListUsers checkList = new ListUsers(this.controller, this.currentUser);
				this.setVisible(false);
				
			
		}
	}

}

