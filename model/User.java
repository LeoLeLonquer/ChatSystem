package model;

import java.net.InetAddress;

public class User implements Dest{
	private String pseudo;
	private int id; 
	private InetAddress  IP;
	private boolean isConnected ;
	private Conv conversation; 

	
	public User(String pseudo,int id, InetAddress  IP, boolean isConnected){
		this.pseudo=pseudo;
		this.id = id; 
		this.IP=IP;
		this.isConnected=isConnected;
		conversation = new Conv(); 
	}
	
	public String getPseudo (){ 
		return this.pseudo; 
	}
	
	public int getID () {
		return this.id; 
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
		return ("Pseudo : " + this.pseudo + " | id : " + this.id + " | IP : "+ this.IP.toString() + " | Status : " + status); 
	}

}
