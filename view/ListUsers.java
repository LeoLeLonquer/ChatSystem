package view;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import javax.swing.*;

import controller.NotifyViewUsers;
import model.User;

public class ListUsers extends JFrame implements ActionListener {
	
	 private JLabel listUs;
	 private JButton userExample;
	 private User current; 
	 private boolean alreadyTalking = false; 
	 private ArrayList<JButton> listButtons ; 

	public ListUsers(User current){ 
		listButtons = new ArrayList<JButton>(); 
		this.current=current; 
		 initComponents();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		for (Iterator<JButton> it = this.listButtons.iterator(); it.hasNext(); ){
			JButton b = it.next(); 
			if (e.getSource()==b && !alreadyTalking){
				alreadyTalking = true; 
				Graphic g = new Graphic(this.current, b.getText()); 
			}
		}

	}
	
	public ArrayList<JButton> computeGrid (ArrayList<User> lu){ 
		for (Iterator<User> it = lu.iterator() ; it.hasNext();){
			User us = it.next(); 
			JButton j = new JButton(us.getPseudo(us));
			this.listButtons.add(j);
			this.add(j); 
		}
		return this.listButtons; 
	}
	
	
	 private void initComponents() {
	 listUs = new JLabel("Connected Users:"); 
	 // communication with the controller:
	 	NotifyViewUsers nvu = new NotifyViewUsers(); 
	 
//	 userExample = new JButton("user1"); 
	 	
	 // configures the JFrame layout using a grid layout
	 this.setLayout(new GridLayout(0,1));
	 this.add(listUs); 
	 //computeGrid(nvu.getListPseudos());
	 computeGrid(nvu.testListUsersView()); 
	 for (Iterator<JButton> it= this.listButtons.iterator(); it.hasNext();){
		JButton j = it.next(); 
		j.addActionListener(this); 
	 }

	// this.add(userExample);
	 this.setTitle("Welcome " + current.getPseudo(current)); 
	 
	 this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE) ; // pcq par défaut est en mode 
	 
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
