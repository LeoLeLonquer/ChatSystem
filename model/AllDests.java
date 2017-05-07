package model; 
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.*;

public class AllDests {
	
	private HashMap<Integer, User> listUsers; 
	private HashMap<Integer, Groupe> ListGroups; 
	
	public AllDests() { 
		listUsers = new HashMap<Integer, User>() ; 
		ListGroups = new HashMap <Integer, Groupe> (); 
	}
	
	public HashMap<Integer,User> getListUsers(){
		return this.listUsers; 
	}
	
	public HashMap<Integer,Groupe> getListGroups(){
		return this.ListGroups; 
	}
	
	public User getUser(int id){ 
		return this.listUsers.get(id);
	}
	
	public int getUserID(String pseudo){
		if (listUsers.containsKey(pseudo.hashCode())){
			return pseudo.hashCode();
			}
		else {
			return -1; 
		}
	}
	
	public void addUser(User us) { 
		this.listUsers.put(us.getUserID(), us) ;
	}
	
	public void setLoggedUser (User user) throws UnknownHostException {
		InetAddress  IP = (InetAddress) InetAddress.getLocalHost(); 
		listUsers.put(0, user); 
	}

	public void addGroup(Groupe group) {
		this.ListGroups.put(group.getID(group), group); 
	}
	
	public void removeUser(int id){ 
		this.listUsers.remove(id);
	}
	
	public void removeGroup(int id){ 
		this.ListGroups.remove(id); 
	}
	
	
	public Groupe searchGroup(int id){ 
		return this.ListGroups.get(id); 
	}
	
	public boolean checkAvailable (String pseudo) {
		if (listUsers.containsKey(pseudo.hashCode())){
			return false;
		}
		else {
			return true;
		}
	}
	
	public HashMap<Integer,User> getHashMapConnectedUsers(){
		HashMap<Integer,User> listConnectedUsers=new HashMap<Integer,User>();
		
		Iterator<User> it=this.listUsers.values().iterator();
		
		User us=null;
		while(it.hasNext()){
			us=it.next();
			if (us.getStatus()){
				listConnectedUsers.put(us.getUserID(), us);
			}
		}
		
		return listConnectedUsers;
	}
	
	@Override
	public String toString(){
		String str="";
		for (Iterator<Integer> it = this.listUsers.keySet().iterator(); it.hasNext() ;) {
			User us = this.listUsers.get(it.next()); 
			str=str+us.toString()+"\n";
		}
		return str;
	}
	
}