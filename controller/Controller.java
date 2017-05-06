package controller;

import java.io.File;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;

import javax.swing.JButton;

import model.*;
import view.Interface;
import view.ListUsers;
import view.LoginWindow;

public class Controller {

	private SystemState sysState;
	private Interface intf;

	
	public Controller(){
		
	}
	
	
	public static void main(String[] args) {
		Controller controller = new Controller();
		Interface intf = new Interface(); 
		 //LoginWindow win = new LoginWindow("Hello! ");
		 }
	
	//***************interface model-Controller************************
	
	public void notifyNewMessageFromModel(int id) {
		
	}

	
	public boolean checkNewUser(ArrayList<User> ListofUsers){
		
		return false;
	}
	
	//**************************************************************
	
	//***************interface view-Controller************************
	
	public void sendMessage (String currentUser, String destUser, String msg){
		
	}
	
	public boolean askStatusOf(String user){
		boolean status = false; 
		return status; 
	}
	
	public void sendFile (String current, String destination, File file){ 
		
	}
	
	public User getCurrentUser(){ 
		return this.sysState.getLoggedUser(); 
	}
	
	 
	 public void initUs (String pseudo){ 
			SystemState sys = new SystemState(pseudo); 
	 }

	public void logOutFromLoggedUser(){
		
	}
	
	public void gotNewUser(User newUser){
		this.intf.addNewUser(newUser.getPseudo());
	}
	
	public ArrayList <String> getListOfConnectedUsers (){
		ArrayList<String> al = new ArrayList<String>();
		return al;
	}
	
	/*************************************** TEST METHODS ************************************/
	
	// makes a list of connected users upon first connecting 
	public ArrayList<String> testListUsersEmpty (){ 
		ArrayList<String> al = new ArrayList<String>(); 
		return al; 
	}
	
	public ArrayList<String> testListUsersView(){
		String u1; 
		String u2; 
		String u3; 
		ArrayList<String> al = new ArrayList<String>(); 	
		
			u1 = "Does";
			u2 ="It"; 
			u3 = "Work?"; 
			
			al.add(u1); 
			al.add(u2); 
			al.add(u3); 
		return al; 
	}
	
	
	
}
