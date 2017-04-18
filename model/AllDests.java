package model; 
import java.util.*;

public class AllDests {
	static int id; 
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
	
	public void addUser(User us) { 
		this.ListUsers.put(id, us) ; 
		id++;
	}

	public void addGroup(Groupe group) {
		this.ListGroups.put(id, group); 
		id++;
	}
	
	public void removeUser(int identify){ 
		this.ListUsers.remove(identify);
	}
	
	public void removeGroup(int identify){ 
		this.ListGroups.remove(identify); 
	}
	
	public User searchUser(int identifier){ 
//		if (ListUsers.containsKey(identifier)){ 
//			for (Iterator<Integer> it = this.ListUsers.keySet().iterator(); it.hasNext() ;) {
//				Integer id = it.next(); 
//				if (id==identifier) {
		return this.ListUsers.get(identifier);
//				}
//			}
//		}
//		return null; 
	}
	
	public Groupe searcGroup(int identifier){ 
		return this.ListGroups.get(identifier); 
	}
}