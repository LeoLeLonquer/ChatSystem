package model; 
import java.net.Inet4Address;
import java.util.*;

public class AllDests {
	
	SystemState sysState;
	private HashMap<Integer, User> ListUsers; 
	private HashMap<Integer, Groupe> ListGroups; 
	
	public AllDests(SystemState sysState) { 
		this.sysState=sysState;
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
		this.ListUsers.put(us.getID(), us) ;
		sysState.sommetID++;
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
	
	public int searchUserIDByIP(Inet4Address IP){ 
		for (Iterator<Integer> it = this.ListUsers.keySet().iterator(); it.hasNext() ;) {
			User us = this.ListUsers.get(it.next()); 
			if( us.getIP()==IP) { 
				return us.hashCode(); 
			}
		}
		return -1; 
	}
	
	public int searchUserIDByPseudo(String pseudo){
		for (Iterator<Integer> it = this.ListUsers.keySet().iterator(); it.hasNext() ;) {
			User us = this.ListUsers.get(it.next()); 
			if( us.getPseudo().equals(pseudo)) { 
				return us.hashCode(); 
			}
		}
		return -1; 
	}
	
	public Groupe searchGroup(int id){ 
		return this.ListGroups.get(id); 
	}
	
	public boolean checkAvailable (String pseudo) {
		if (this.searchUserIDByPseudo(pseudo)!=-1)
			return true;
		else 
			return false; 
	}
	

}