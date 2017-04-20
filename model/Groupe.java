package model;

import java.util.ArrayList;

public class Groupe implements Dest {
	private String nom;	
	private int id; 
	private ArrayList<User> Users;
	private Conv conversation; 

	
	public Groupe(String nom, int id){
		this.nom = nom;
		this.id = id; 
		Users=new ArrayList<User>();
		conversation = new Conv(); 
	}
	
	public String getName (Groupe g) {
		return g.nom; 
	}
	
	public int getID (Groupe g) {
		return g.id; 
	}
	
	public String toString() {
		if (this.Users.isEmpty()){ 
			return ("Group " + this.nom + " (id: " + this.id + ") " + " (empty)"); 
		}
		else { 
			return ("Group " + this.nom + " (id: " + this.id + ") " + "with users " + this.Users.toString()); 
		}
	}
	
}
