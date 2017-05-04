package model;

import java.net.*;

import communication.Communication;
import communication.ControlMessage;
import communication.ToolsCom;

public class SystemState {
// login
// disconnect 
//	private String name; 
//	private boolean isConnected ; 
//	private Conv conv = new Conv(); 
	
	public User loggedUser; 
	public AllDests allDests; 
	public Communication comModule;

	
	public SystemState(String chosenName){
	    // this name is chosen when the user fist logs in ; 
		// a window should pop to let the user choose their name --> value transferred to LoggedUser
		allDests = new AllDests(this); 
		try {
			this.loggedUser= new User(chosenName,ToolsCom.getLocalHostLANAddress(),true);
		} catch (UnknownHostException e) {
			System.out.println("Erreur à la création de loggedUser");
			e.printStackTrace();
		}
		comModule=new Communication(this);

	}
	
	private void setLoggedUser (String chosenName, int id) throws UnknownHostException {
		InetAddress  IP = (InetAddress) InetAddress.getLocalHost(); 
		loggedUser = new User(chosenName, IP, true) ; 
	}
	
	
	public User getLoggedUser(){
		return this.loggedUser;
	}
	
	
	public int manageNewUser(ControlMessage ctrlMsg) {
		int id=0;
		if (allDests.checkAvailable(ctrlMsg.getUserName())){	//le nouvel utilisateur n'ecistait pas avant
			
			User newUser = new User(ctrlMsg.getUserName(),ctrlMsg.getUserAdresse(),true);
			System.out.println("nouveau User : " + newUser.toString());
			allDests.addUser(newUser);
			id=ctrlMsg.getUserName().hashCode();
			
		}
		else {															//le nouvel utilisateur existait déjà avant
			id=ctrlMsg.getUserName().hashCode();
			if (allDests.getUser(id).getStatus()){  //un utilisateur à ce nom est déjà connecté
				System.out.println("!!!!!!!Il existe déjà un utilisateur nommé "+ctrlMsg.getUserName()+" !!!!!!!!!!");
				id=-1;
			}
			else{
				allDests.getUser(id).setIP(ctrlMsg.getUserAdresse());
				allDests.getUser(id).setStatus(true);
			}
		}
		return id;
	}
	
}
