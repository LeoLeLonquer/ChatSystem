package model;

import java.net.*;

public class SystemState {
// login
// disconnect 
//	private String name; 
//	private boolean isConnected ; 
//	private Conv conv = new Conv(); 
	static User loggedUser; 
	private AllDests allDests; 
	
	
	public SystemState(String chosenName){
	    // this name is chosen when the user fist logs in ; 
		// a window should pop to let the user choose their name --> value transferred to LoggedUser
		allDests = new AllDests(); 
	}
	
	private void setLoggedUser (String chosenName) throws UnknownHostException {
		Inet4Address  IP = (Inet4Address) Inet4Address.getLocalHost(); 
		loggedUser = new User(chosenName, IP, true) ; 
	}

	// méthodes pour modif attribue
	
// selectConv 
	
}
