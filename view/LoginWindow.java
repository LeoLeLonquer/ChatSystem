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
		 private User current; 
		 
		 private void initUs (User us){ 
				Inet4Address ip;
				try { 
					ip = (Inet4Address) Inet4Address.getLocalHost();
					 this.current = new User("default", 0, ip, false); 

				} catch (UnknownHostException e){ 
					System.out.println("ERROR: Unknown Host");
				}
		 }
		 
		//action performed 
		public void actionPerformed(ActionEvent e) {
			if (e.getSource()==login ) {
				//Inet4Address ip;
				//try {
				//	ip = (Inet4Address) Inet4Address.getLocalHost();
					this.initUs(this.current);
					this.current.setPseudo(loginArea.getText());
					this.current.setStatus(true);
					
					ListUsers checkList = new ListUsers(this.current);
					this.setVisible(false);
					
//				} catch (UnknownHostException e1) {
//					System.out.println("ERROR: Unknown Host");
//				}
				
			}
		}
		
	 public LoginWindow (String titre) { 
		 super(titre); 
//			this.loginArea.addKeyListener(new KeyListener(){
//			    @Override
//			    public void keyPressed(KeyEvent e){
//			 
//			    }
//
//			    @Override
//			    public void keyTyped(KeyEvent e) {
//			    	Inet4Address ip;
//			    	
//					try {
//				        if(e.getKeyCode() == KeyEvent.VK_ENTER){
//						ip = (Inet4Address) Inet4Address.getLocalHost();
//						
//						
//						
//						
//
//						this.setVisible(false);
//			        	loginArea.setText("" );
//				        }
//					} catch (UnknownHostException e1) {
//						System.out.println("ERROR: Unknown Host");
//					}
//			    }
//
//			    @Override
//			    public void keyReleased(KeyEvent e) {
//			    }
//			});

		 initComponents();

	 }
	 
	 public User getCurrentUs(){ 
		 return this.current;
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
	 PressEnter pe = new PressEnter (this.current, this.loginArea, this); 
	 

	 // configures the JFrame layout using a grid layout
	 this.setLayout(new GridLayout(2,2)); // 1 ligne 3 colonnes 
	 // places the components in the layout
	 this.add(enterUser); 
	 this.add(bouchetrou);
	 this.add(loginArea); 
	 this.add(login); 

	 this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE) ; // pcq par défaut est en mode 
	 //hide_on_close ce qui ne tue PAS le processus... --> pas correctement fermé!! 

	 // packs the fenetre: size is calculated
	 // regarding the added components
//	 this.pack();
	 this.setSize(300, 150);
     this.setLocationRelativeTo(null);
	 // the JFrame is visible now
	 this.setVisible(true);
	 }
	 
	 /** main entry point */
	 public static void main(String[] args){
	    LoginWindow log = new LoginWindow("Welcome to the Chat System");  

  }

}

