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
	 private ArrayList<String> pseudos; 

	public ListUsers(Controller controller, String current){ 
		listButtons = new ArrayList<JButton>(); 
		this.currentUser=current; 
		this.controller = controller; 
		 initComponents();
	}
	
	public ListUsers(String current, ArrayList<String> pseudos){ 
		listButtons = new ArrayList<JButton>(); 
		this.currentUser=current; 
		this.pseudos = pseudos; 
		 initComponents();
	}

	public ArrayList<JButton> computeGrid (ArrayList<String> lu){ 
		for (Iterator<String> it = lu.iterator() ; it.hasNext();){
			String us = it.next(); 
			JButton j = new JButton(us);
			this.listButtons.add(j);
			this.add(j); 
		}
		return this.listButtons; 
	}
	
	public ArrayList<JButton> getListButtons(){
		return this.listButtons;
	}
	
	
	 private void initComponents() {
	 listUs = new JLabel("Connected Users:"); 
	 	
	 this.setLayout(new GridLayout(0,1));
	 this.add(listUs); 
	 
	 if (!this.pseudos.isEmpty()){
		 computeGrid(this.pseudos);
		 for (Iterator<JButton> it= this.listButtons.iterator(); it.hasNext();){
				JButton j = it.next(); 
				j.addActionListener(this); 
			 }
	 }

	 this.setTitle("Welcome " + this.currentUser); 
	 
	 this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE) ; 
	 

	 this.setSize(500, 900);
     this.setLocation(100, 20);
	 this.setVisible(true);
	 }
	 
	@Override
	public void actionPerformed(ActionEvent e) {
		for (Iterator<JButton> it = this.listButtons.iterator(); it.hasNext(); ){
			JButton b = it.next(); 
			if (e.getSource()==b && !alreadyTalking){
				alreadyTalking = true; 
				Graphic g = new Graphic(this.currentUser, b.getText() ); 
			}
		}
	}
		

}
