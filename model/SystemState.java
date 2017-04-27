package model;

import java.net.*;

import communication.Communication;
import communication.ToolsCom;

public class SystemState {
// login
// disconnect 
//	private String name; 
//	private boolean isConnected ; 
//	private Conv conv = new Conv(); 
	
	public User loggedUser; 
	public AllDests allDests; 
	public Communication comModule;
	int sommetID=0;

	
	public SystemState(String chosenName){
	    // this name is chosen when the user fist logs in ; 
		// a window should pop to let the user choose their name --> value transferred to LoggedUser
		allDests = new AllDests(this); 
		try {
			this.loggedUser= new User(chosenName,0,ToolsCom.getLocalHostLANAddress(),true);
		} catch (UnknownHostException e) {
			System.out.println("Erreur à la création de loggedUser");
			e.printStackTrace();
		}
		comModule=new Communication(this);

	}
	
	private void setLoggedUser (String chosenName, int id) throws UnknownHostException {
		InetAddress  IP = (InetAddress) InetAddress.getLocalHost(); 
		loggedUser = new User(chosenName, id, IP, true) ; 
	}
	
	
	public User getLoggedUser(){
		return this.loggedUser;
	}
	
	public int getSommetID(){
		return this.sommetID;
	}

	// m�thodes pour modif attribue
	
// selectConv 
	
}
