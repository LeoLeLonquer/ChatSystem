package model;

import java.net.Inet4Address;

public class User implements Dest{
	private String pseudo;
	private int id; 
	private Inet4Address  IP;
	private boolean isConnected ;
	private Conv conversation; 

	
	public User(String pseudo,int id, Inet4Address  IP, boolean isConnected){
		this.pseudo=pseudo;
		this.id = id; 
		this.IP=IP;
		this.isConnected=isConnected;
		conversation = new Conv(); 
	}
	
	public String getPseudo (User us){ 
		return us.pseudo; 
	}
	
	public int getID (User us) {
		return us.id; 
	}
	
	public Inet4Address getIP (User us){
		return us.IP; 
	}
	
	public boolean getStatus (User us){ 
		return us.isConnected; 
	}
	
	public void setPseudo (String newPseudo) {
		this.pseudo = newPseudo; 
	}
	
	public void setStatus (boolean newStatus) {
		this.isConnected = newStatus; 
	}
	
	public String toString() {
		String status = ""; 
		if (this.isConnected){ 
			status = " is connected"; 
			}
		else {
			status = " is disconnected"; 
		}
		return ("User " + this.pseudo + " (id: " + (this.id) + ") " + this.IP.toString() + ", " + status); 
	}
}
