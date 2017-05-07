package controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

import model.*;
import view.Interface;

public class Controller {

	private SystemState sysState;
	private Interface itf;

	public Controller(){

	}


	//***************Appel depuis View ************************

	public void initUs (String pseudo){ 
		sysState = new SystemState(this,pseudo); 
	}
	
	public void sendMessage (String srcUser, String destUser, String msg){
		sysState.sendMessage(srcUser, destUser, msg);
	}
	

	public void sendFile (String srcUser, String destUser, File file){ 
		sysState.sendFile(srcUser, destUser, file);
	}


	public boolean askStatusOf(String user){
		boolean status = sysState.getAllDests().getUser(user.hashCode()).getStatus(); 
		return status; 
	}

	public User getCurrentUser(){ 
		return this.sysState.getLoggedUser(); 
	}

	public void logOutFromLoggedUser(){
		sysState.logOutLoggedUser();
	}
	
	
	public ArrayList <String> getListOfConnectedUsers (){
		ArrayList<String> al = new ArrayList<String>();
		
		String pseudo="";
		Iterator<User> it=sysState.getAllDests().getHashMapConnectedUsers().values().iterator();
		
		while(it.hasNext()){
			pseudo=it.next().getPseudo();
			al.add(pseudo);
		}
		return al;
	}

	
	//***************Appel depuis Model************************

	
	public void notifyNewMessageFromModel(String pseudo, String msg) {
		itf.receiveMsg(pseudo,msg);
	}


	public void notifyNewUser(String pseudo){
		itf.addNewUser(pseudo);
	}
	
	public void notifyLogoutUser(String pseudo){
		itf.removeUser(pseudo);
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
