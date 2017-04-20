package model; 
import java.net.Inet4Address;
import java.util.*;

public class AllDests {
	private HashMap<Integer, User> ListUsers; 
	private HashMap<Integer, Groupe> ListGroups; 
	
	public AllDests() { 
		ListUsers = new HashMap<Integer, User>() ; 
		ListGroups = new HashMap <Integer, Groupe> (); 
	}

//	public boolean checkAvailable (User us) {
//		return us.getStatus(us); 
//	}
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
		this.ListUsers.put(us.getID(us), us) ; 
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
	
	public User searchUser(int id){ 
//		if (ListUsers.containsKey(identifier)){ 
//			for (Iterator<Integer> it = this.ListUsers.keySet().iterator(); it.hasNext() ;) {
//				Integer id = it.next(); 
//				if (id==identifier) {
		return this.ListUsers.get(id);
//				}
//			}
//		}
//		return null; 
	}
	
	public Groupe searcGroup(int id){ 
		return this.ListGroups.get(id); 
	}
	
	public User searchByIP(Inet4Address IP){ 
		for (Iterator<Integer> it = this.ListUsers.keySet().iterator(); it.hasNext() ;) {
			User us = this.ListUsers.get(it.next()); 
			if( us.getIP(us)==IP) { 
				return us; 
			}
		}
		return null; 
	}
}