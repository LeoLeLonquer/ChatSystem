package view;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JButton;

import controller.Controller;
import javax.swing.*; 

public class Interface {
	
	private ListUsers lu; 
	private LoginWindow lw; 
	private String currentUs;
	private String friendUs; 
	private Controller controller; 
	private JOptionPane jop; 
	private static boolean loginout; 
	private HashMap<Integer,Graphic> listGraphics;
	
	public Interface(Controller controller){
		this.controller = controller;
		controller.setInterface(this);
		this.lw = new LoginWindow(this, "Chat System!"); 		
		loginout = false; 
		this.currentUs = lw.getCurrentUs(); 
		listGraphics=new HashMap<Integer,Graphic>();
	}
	
	public void launchListUsers(){
			this.lu=new ListUsers(this.currentUs,new ArrayList<String>() , this);
			//System.out.println("Avant de fermer: " + loginout);
			this.lu.addWindowListener(new WindowAdapter() {
	            @Override
	            public void windowClosing(WindowEvent e) {
	               JOptionPane jop = new JOptionPane(); 
	               if (jop.showConfirmDialog(lu, "Are you sure you want to log out?" )== JOptionPane.YES_OPTION){
	      	           loginout = true; 
	      			//	System.out.println("Apr�s : " + loginout);

	            	   System.exit(0);
	               }
	               
	            }

	            @Override
	            public void windowClosed(WindowEvent e) {
	            }

	        });

	}
	
	public void setLoginoutTrue (){
		this.loginout = true; 
	}
	
	public ListUsers getListUsers(){
		return this.lu;
	}
	
	public LoginWindow getLoginWindow(){
		return this.lw; 
	}
	
	public String getCurrentUs(){ 
		return this.currentUs ; 
	}
	
	
	public void setCurrentUs(String s){ 
		this.currentUs=s; 
		
	}
	
	public String getFriendUs(){
		return this.friendUs; 
	}
	
	public Controller getController (){
		return this.controller; 
	}
	
	public void addNewUser (String pseudoNew){
		JButton j = new JButton (pseudoNew); 
		j.addActionListener(this.lu);
		this.lu.getListButtons().add(j); // gotta add it bc the actionperformed method in ListUsers checks the list 
		this.lu.add(j);
		this.lu.revalidate();
		this.lu.repaint();
	}
	
	public void transferMsgToController(String source, String dest, String msg){
		this.controller.sendMessage(source, dest, msg);
	}
	
	public void transferFileToController(String source, String dest, File file){
		this.controller.sendFile(source, dest, file);
	}
	
	public void receiveMsg(String friendUser, String message){
		this.listGraphics.get(friendUser.hashCode()).getConvoPane().update( friendUser,  message);
	}
	
	public void receiveFile(String friendUser, File file){
		this.listGraphics.get(friendUser.hashCode()).getConvoPane().getConvoTextArea().append(friendUser + "send " + file.getName());
		// click on file to open it?? 
	}
	
	public boolean notifyLogout(){ 
		return loginout ; 
	}

	public void removeUser(String pseudo) {
		
	}

	public void askInitSys(String currentUser) {
		controller.initUs(currentUser);
	}

	public HashMap<Integer,Graphic> getListGraphics() {
		return listGraphics;
	}


}
