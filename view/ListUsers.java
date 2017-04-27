package view;
import java.awt.*;
import java.awt.event.*;
import java.io.*; 
import javax.swing.*;

public class ListUsers extends JFrame implements ActionListener {
	
	 private JLabel listUs;

	 // a button to perform an action: e.g. say hello (TBD) */
	 private JButton userExample;
	 

	public ListUsers(){ 
		 initComponents();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	 private void initComponents() {
	 // create the components
	 // a new label with the "Nom" as value
	 listUs = new JLabel("Connected Users:"); 
	 userExample = new JButton("user1"); 
	 userExample.addActionListener(this); 
	 

	 // configures the JFrame layout using a grid layout
	 this.setLayout(new GridLayout(0,1));
	 // places the components in the layout
	 this.add(listUs); 
	 this.add(userExample);


	 
	 this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE) ; // pcq par défaut est en mode 
	 //hide_on_close ce qui ne tue pas le processus... 
	 
//	  le thread de reception de mesg 
//		ThreadsReceive t = new ThreadsReceive ("receive", reader, writer, msgRcv) ; 
//		t.start(); 

	 // packs the fenetre: size is calculated
	 // regarding the added components
	 this.pack();
	 // the JFrame is visible now
	 this.setVisible(true);
	 }
	 
//	 /** main entry point */
//	 public static void main(String[] args) {
//	    ListUsers lu = new ListUsers();  
//
//	  }
	
}
