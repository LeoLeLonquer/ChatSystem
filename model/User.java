package model;

import java.net.Inet4Address;

public class User implements Dest{
	private String pseudo;
	private Inet4Address  IP;
	private boolean isConnected ;
	
	public User(String pseudo,Inet4Address  IP, boolean isConnected){
		this.pseudo=pseudo;
		this.IP=IP;
		this.isConnected=isConnected;
	}
	
}
