package model; 
import java.util.*;

public class AllUsers {
	private ArrayList<User> ListUsers ;
	
	public AllUsers() { 
		ListUsers = new ArrayList<User>() ; 
	}

	public boolean checkAvailable (User us) {
		return us.getStatus(us); 
	}

}