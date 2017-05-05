package view;
import java.awt.*;
import java.awt.event.*;
import java.io.*; 
import javax.swing.*;

import model.User;

public class ListUsers extends JFrame implements ActionListener {
	
	 private JLabel listUs;
	 private JButton userExample;
	 private User current; 
	 private boolean alreadyTalking = false; 

	public ListUsers(User current){ 
		this.current=current; 
		 initComponents();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==userExample && !alreadyTalking){
			alreadyTalking = true; 
			Graphic g = new Graphic(this.current); 
		}
	}
	 private void initComponents() {
	 listUs = new JLabel("Connected Users:"); 
	 userExample = new JButton("user1"); 
	 
	 // TODO: will use AllDests to display all connected users? 
	 
	 userExample.addActionListener(this); 

	 // configures the JFrame layout using a grid layout
	 this.setLayout(new GridLayout(0,1));
	 this.add(listUs); 
	 this.add(userExample);
	 this.setTitle("Welcome " + current.getPseudo()); 
	 
	 this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE) ; // pcq par d�faut est en mode 
	 
	 // packs the fenetre: size is calculated
//	 this.pack();
	 this.setSize(300, 750);
     this.setLocation(100, 20);
	 // the JFrame is visible now
	 this.setVisible(true);
	 }
	 
//	 /** main entry point */
//	 public static void main(String[] args) {
//	    ListUsers lu = new ListUsers();  
//
//	  }
	
}
