package model;

import java.net.Inet4Address;

public class User implements Dest{
	private String pseudo;
	private Inet4Address  IP;
	private boolean isConnected ;
	private Conv conversation; 

	
	public User(String pseudo,Inet4Address  IP, boolean isConnected){
		this.pseudo=pseudo;
		this.IP=IP;
		this.isConnected=isConnected;
		conversation = new Conv(); 
	}
	
public String getPseudo (User us){ 
	return us.pseudo; 
}

public Inet4Address getIP (User us){
	return us.IP; 
}

public boolean getStatus (User us){ 
	return us.isConnected; 
}
	
}
