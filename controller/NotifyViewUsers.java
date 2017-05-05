package controller;
import model.*;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import model.User;
import view.ListUsers;

public class NotifyViewUsers {
	
	public NotifyViewUsers(){ 
		
	}
	
	private AllDests ad; 
//	private HashMap<Integer,User> theListus = ad.getListUsers(); 
	private ArrayList<User> ListUsersForView; 
	
	public void addConnectedUser (User newUs, ListUsers lu){ 
		
	}
	
	public void updateListUsers(ListUsers lu){ 
		
	}
	
	public ArrayList<User> getListPseudos (){ 
		return this.ListUsersForView; 
	}
	
	public ArrayList<User> testListUsersView(){ 
		Inet4Address IP1; 
		Inet4Address IP2; 
		Inet4Address IP3; 
		User u1; 
		User u2; 
		User u3; 
		ArrayList<User> al = new ArrayList<User>(); 
		
		try {
			IP1 = (Inet4Address) InetAddress.getLocalHost();
			IP2 = (Inet4Address) InetAddress.getByName("192.168.11.2"); 
			IP3 = (Inet4Address) InetAddress.getByName("192.168.11.3"); 
			
			
			u1 = new User("Does", 1, IP1, true); 
			u2 = new User("It", 2, IP2, true); 
			u3 = new User("Work?", 3, IP3, false); 
			
			al.add(u1); 
			al.add(u2); 
			al.add(u3); 
			
		} catch (UnknownHostException e) {
			System.out.println("ERROR: Unkown Host ");
		}
		return al; 


		
		
	}

}
