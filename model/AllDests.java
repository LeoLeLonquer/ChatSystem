package model; 
import java.util.*;

public class AllDests {
	private ArrayList<User> ListDests ;
	
	public AllDests() { 
		ListDests = new ArrayList<User>() ; 
	}

	public boolean checkAvailable (User us) {
		return us.getStatus(us); 
	}
	
	// rechercher user
	// lire ensemble de la liste (pour voir qui est co)
	// ajouter un utilisateur, change

}