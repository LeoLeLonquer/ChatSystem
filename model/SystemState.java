package model;

import java.io.File;
import java.io.IOException;
import java.net.*;
import java.util.Iterator;

import controller.Controller;
import network.Communication;
import network.ControlMessage;
import network.Message;
import network.ToolsCom;
import network.Message.DataType;


public class SystemState {

	private Controller controller;
	private User loggedUser;
	private AllDests allDests; 
	private Communication comModule;


	public SystemState(Controller controller, String chosenName){
		// this name is chosen when the user fist logs in ; 
		// a window should pop to let the user choose their name --> value transferred to LoggedUser
		this.controller=controller;
		allDests = new AllDests(); 
		InetAddress localAdr = null;
		try {
			localAdr=ToolsCom.getLocalHostLANAddress();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		loggedUser= new User(chosenName,localAdr,true);
		comModule=new Communication(this);
	}

	public User getLoggedUser(){
		return this.loggedUser;
	}

	public AllDests getAllDests() {
		return this.allDests;
	}

	public Communication getComModule(){
		return this.comModule;
	}


	public void logOutLoggedUser(){
		ControlMessage ctrlMsg=comModule.createControlMessageWithLocalID(comModule.getListeningPort(), "bye");
		try {
			comModule.getManagerUDP().sendBroadcastedControlMessage(ctrlMsg);
		} catch (IOException e) {
			e.printStackTrace();
		}

		String pseudo="";
		Iterator<User> it=allDests.getListUsers().values().iterator();
		while(it.hasNext()){
			pseudo=it.next().getPseudo();
			comModule.getListeManagerTCP().get(pseudo.hashCode()).close();
		}

	}


	public void sendMessage (String srcUser, String destUser, String msg){
		if (allDests.getUser(destUser.hashCode()).getStatus()){
			comModule.sendTxtMessage(msg, destUser);
			allDests.getUser(destUser.hashCode()).getConv().addMessage(new Message(DataType.Text,msg,destUser,srcUser));
		}
		else {
			String str= "Utilisateur déconnecté, envoi impossible";
			allDests.getUser(destUser.hashCode()).getConv().addMessage(new Message(DataType.Text,str,destUser,srcUser));
			controller.notifyNewMessageFromModel(destUser,str);
		}
	}

	public void sendFile (String srcUser, String destUser, File file){ 
		if (allDests.getUser(destUser.hashCode()).getStatus()){
			comModule.sendFileMessage(file, destUser);
			allDests.getUser(destUser.hashCode()).getConv().addMessage(new Message(DataType.File,file.getName(),destUser,srcUser));
		}
		else {
			String str= "Utilisateur déconnecté, envoi impossible";
			allDests.getUser(destUser.hashCode()).getConv().addMessage(new Message(DataType.Text,str,destUser,srcUser));
			controller.notifyNewMessageFromModel(destUser,str);
		}
	}



	public int manageNewUser(String userName, InetAddress adr) {
		int id=0;
		if (allDests.checkAvailable(userName)){	//le nouvel utilisateur n'existait pas avant

			User newUser = new User(userName,adr,true);
			System.out.println("nouveau User : " + newUser.toString());
			allDests.addUser(newUser);
			id=userName.hashCode();
			controller.notifyNewUser(newUser.getPseudo());

		}
		else {															//le nouvel utilisateur existait déjà avant
			id=userName.hashCode();
			if (allDests.getUser(id).getStatus()){  //un utilisateur à ce nom est déjà connecté
				System.out.println("!!!!!!!Il existe déjà un utilisateur nommé "+userName+" !!!!!!!!!!");
				id=-1;
			}
			else{                                  //un utilisateur à ce nom n'est plus connecté
				allDests.getUser(id).setIP(adr);
				allDests.getUser(id).setStatus(true);
			}
		}
		return id;
	}


	public void manageTxtMessage(Message msg){		
		String srcPseudo= msg.getSrcPseudo();
		int id= getAllDests().getUserID(srcPseudo);
		getAllDests().getUser(id).getConv().addMessage(msg);
		System.out.println("srcPseudo : "+srcPseudo+" Message : "+ msg.getData());
		controller.notifyNewMessageFromModel(srcPseudo,msg.getData());

	}


	public void manageFileMessage(Message receivedMsg,String path) {
		String srcPseudo= receivedMsg.getSrcPseudo();
		Message msg= new Message(DataType.File,path,receivedMsg.getDestPseudo(),srcPseudo);
		int id= getAllDests().getUserID(srcPseudo);
		getAllDests().getUser(id).getConv().addMessage(msg);
		controller.notifyNewMessageFromModel(srcPseudo,msg.getData());

	}

}
