package model;

import java.util.ArrayList;

public class Groupe implements Dest {
	private String nom;	
	private ArrayList<User> Users;
	private Conv conversation; 

	
	public Groupe(String nom){
		this.nom = nom;
		Users=new ArrayList<User>();
		conversation = new Conv(); 
	}
	
}
