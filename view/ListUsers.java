package view;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import javax.swing.*;

import controller.Controller;
import model.User;

public class ListUsers extends JFrame implements ActionListener {
	
	 private JLabel listUs;
	 private JButton userExample;
	 private String currentUser; 
	 private boolean alreadyTalking = false; 
	 private ArrayList<JButton> listButtons ; 
	 private Controller controller;

	public ListUsers(Controller controller, String current){ 
		listButtons = new ArrayList<JButton>(); 
		this.currentUser=current; 
		this.controller = controller; 
		 initComponents();
	}

	public ArrayList<JButton> computeGrid (ArrayList<User> lu){ 
		for (Iterator<User> it = lu.iterator() ; it.hasNext();){
			User us = it.next(); 
			JButton j = new JButton(us.getPseudo());
			this.listButtons.add(j);
			this.add(j); 
		}
		return this.listButtons; 
	}
	
	
	 private void initComponents() {
	 listUs = new JLabel("Connected Users:"); 
	 // communication with the controller:
	 	controller.updateListUsers(this);  
	 
//	 userExample = new JButton("user1"); 
	 	
	 // configures the JFrame layout using a grid layout
	 this.setLayout(new GridLayout(0,1));
	 this.add(listUs); 
	 //computeGrid(nvu.getListPseudos());
	 computeGrid(this.controller.testListUsersView()); 
	 for (Iterator<JButton> it= this.listButtons.iterator(); it.hasNext();){
		JButton j = it.next(); 
		j.addActionListener(this); 
	 }

	// this.add(userExample);
	 this.setTitle("Welcome " + this.currentUser); 
	 
	 this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE) ; // pcq par d�faut est en mode 
	 
	 // packs the fenetre: size is calculated
//	 this.pack();
	 this.setSize(300, 750);
     this.setLocation(100, 20);
	 // the JFrame is visible now
	 this.setVisible(true);
	 }
	 
	@Override
	public void actionPerformed(ActionEvent e) {
		for (Iterator<JButton> it = this.listButtons.iterator(); it.hasNext(); ){
			JButton b = it.next(); 
			if (e.getSource()==b && !alreadyTalking){
				alreadyTalking = true; 
				Graphic g = new Graphic(this.controller, this.currentUser, b.getText() ); 
			}
		}

	}
	
}
