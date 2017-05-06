package model;

import java.net.*;

import network.Communication;
import network.ControlMessage;
import network.ToolsCom;


public class SystemState {
// login
// disconnect 
//	private String name; 
//	private boolean isConnected ; 
//	private Conv conv = new Conv(); 
	
	private User loggedUser;
	public AllDests allDests; 
	public Communication comModule;

	
	public SystemState(String chosenName){
	    // this name is chosen when the user fist logs in ; 
		// a window should pop to let the user choose their name --> value transferred to LoggedUser
		allDests = new AllDests(); 

		InetAddress localAdr = null;
		try {
			localAdr=ToolsCom.getLocalHostLANAddress();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}

		int idLoggedUser=manageNewUser(chosenName,localAdr);
		this.loggedUser=allDests.getUser(idLoggedUser);
		
	
		comModule=new Communication(this);
		
		
		//AJOUT DU PERROQUET
		//int idPerroquet=manageNewUser("perroquet",localAdr);
		//int loggedUserPort = comModule.getPortOfUser(idLoggedUser);	
		//this.comModule.createManagerTCP(idLoggedUser, null, 0);
		//this.comModule.createManagerTCP(idPerroquet, localAdr, loggedUserPort);



	}
	
	private void setLoggedUser (String chosenName, int id) throws UnknownHostException {
		InetAddress  IP = (InetAddress) InetAddress.getLocalHost(); 
		loggedUser = new User(chosenName, IP, true) ; 
	}
	
	
	public User getLoggedUser(){
		return this.loggedUser;
	}
	

	
	public int manageNewUser(String userName, InetAddress adr) {
		int id=0;
		if (allDests.checkAvailable(userName)){	//le nouvel utilisateur n'ecistait pas avant
			
			User newUser = new User(userName,adr,true);
			System.out.println("nouveau User : " + newUser.toString());
			allDests.addUser(newUser);
			id=userName.hashCode();
			
		}
		else {															//le nouvel utilisateur existait déjà avant
			id=userName.hashCode();
			if (allDests.getUser(id).getStatus()){  //un utilisateur à ce nom est déjà connecté
				System.out.println("!!!!!!!Il existe déjà un utilisateur nommé "+userName+" !!!!!!!!!!");
				id=-1;
			}
			else{
				allDests.getUser(id).setIP(adr);
				allDests.getUser(id).setStatus(true);
			}
		}
		return id;
	}

	
}
