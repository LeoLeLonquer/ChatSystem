package model;

import java.net.InetAddress;

public class User implements Dest{
	private String pseudo;
	private InetAddress  IP;
	private boolean isConnected ;
	public Conv conversation; 

	
	public User(String pseudo, InetAddress  IP, boolean isConnected){
		this.pseudo=pseudo;
		this.IP=IP;
		this.isConnected=isConnected;
		conversation = new Conv(); 
	}
	
	public String getPseudo (){ 
		return this.pseudo; 
	}
	
	public int getUserID () {
		return pseudo.hashCode(); 
	}
	
	public InetAddress getIP (){
		return this.IP; 
	}
	
	public boolean getStatus (){ 
		return this.isConnected; 
	}
	
	public void setPseudo (String newPseudo) {
		this.pseudo = newPseudo; 
	}
	
	public void setIP(InetAddress IP){
		this.IP=IP;
	}
	
	public void setStatus (boolean newStatus) {
		this.isConnected = newStatus; 
	}
	
	public Conv getConv() {
		return this.conversation;
	}
	
	@Override
	public String toString() {
		String status = ""; 
		if (this.isConnected){ 
			status = " is connected"; 
			}
		else {
			status = " is disconnected"; 
		}
		return ("Pseudo : " + this.pseudo + " | id : " + this.getUserID() + " | IP : "+ this.IP.toString() + " | Status : " + status); 
	}

}
