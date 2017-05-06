package model; 
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.*;

public class AllDests {
	
	private HashMap<Integer, User> ListUsers; 
	private HashMap<Integer, Groupe> ListGroups; 
	
	public AllDests() { 
		ListUsers = new HashMap<Integer, User>() ; 
		ListGroups = new HashMap <Integer, Groupe> (); 
	}


	// rechercher user
	// lire ensemble de la liste (pour voir qui est co)
	// ajouter un utilisateur, change
	
//	public void makeListeCo () {
//		//for (int i = 0 ; i< this.ListDests.size() ; i++) {
//			//if (this.ListDests.get(i).getStatus(ListDests.get(i)) ) {
//		for (Iterator<User> iter = this.ListDests.iterator(); iter.hasNext(); ) {
//			User us = iter.next();
//		    if (us.getStatus(us)) { 
//		    	this.ListCo.add(us); 
//			}
//		}
//	}
	
	public HashMap<Integer,User> getListUsers(){
		return this.ListUsers; 
	}
	
	public HashMap<Integer,Groupe> getListGroups(){
		return this.ListGroups; 
	}
	
	public void addUser(User us) { 
		this.ListUsers.put(us.getUserID(), us) ;
	}
	
	public void setLoggedUser (User user) throws UnknownHostException {
		InetAddress  IP = (InetAddress) InetAddress.getLocalHost(); 
		ListUsers.put(0, user); 
	}

	public void addGroup(Groupe group) {
		this.ListGroups.put(group.getID(group), group); 
	}
	
	public void removeUser(int id){ 
		this.ListUsers.remove(id);
	}
	
	public void removeGroup(int id){ 
		this.ListGroups.remove(id); 
	}
	
	public User getUser(int id){ 
		return this.ListUsers.get(id);
	}
	
	public int getUserID(String pseudo){
		if (ListUsers.containsKey(pseudo.hashCode())){
			return pseudo.hashCode();
			}
		else {
			return -1; 
		}
	}
	
	public Groupe searchGroup(int id){ 
		return this.ListGroups.get(id); 
	}
	
	public boolean checkAvailable (String pseudo) {
		if (this.getUserID(pseudo)!=-1)
			return false;
		else 
			return true; 
	}
	
	@Override
	public String toString(){
		String str="";
		for (Iterator<Integer> it = this.ListUsers.keySet().iterator(); it.hasNext() ;) {
			User us = this.ListUsers.get(it.next()); 
			str=str+us.toString()+"\n";
		}
		return str;
	}
	
}