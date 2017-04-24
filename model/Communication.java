package model;

import controller.Controller;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.HashMap;

import model.Message.DataType;

public class Communication extends Thread {
	
	Controller controller;
	SystemState sysState;
	ManagerUDP ManagerUDP;
	HashMap<Integer,ManagerTCP> listeManagerTCP ;
	int listeningPort=15530;
	int cptSocket=1;
	
	public Communication(SystemState sysState){
		try {
			 this.sysState=sysState;
			 this.ManagerUDP=new ManagerUDP(this);
			 this.listeManagerTCP = new HashMap<Integer,ManagerTCP>();
		} catch (SocketException e) {
			e.printStackTrace();
		}
		start();
	}
	
	
	
	public void run(){
		
			try {
				this.ManagerUDP.sendBroadcast("hello");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		
		while(true){

		
		}	
	}
	
	private void sendTxtMessage(String str, String destPseudo, String srcPseudo){
		Message msg= new Message(DataType.Text, str, destPseudo,srcPseudo);
		int id= sysState.allDests.searchUserIDByPseudo(destPseudo);
		this.listeManagerTCP.get(id).sendMessage(msg);
	}
	
	private void sendFileMessage(File file, String destPseudo, String srcPseudo){
		int id= sysState.allDests.searchUserIDByPseudo(destPseudo);
		this.listeManagerTCP.get(id).sendFile(file,destPseudo,srcPseudo);
	}

	private void sendControlMessage(int localPort,InetAddress adr, int port , String str) {
		String currentUser=SystemState.loggedUser.getPseudo();
		String msg=str;
		InetAddress localAdr = null;
		try {
			localAdr = ToolsCom.getLocalHostLANAddress();
		} catch (UnknownHostException e1) {
			e1.printStackTrace();
		}
		
		ControlMessage ctrlMsgToSend= new ControlMessage(currentUser,localAdr,localPort,msg);
		try {
			ManagerUDP.sendControlMessage(ctrlMsgToSend,adr,port);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	private void createManagerTCP(int id,InetAddress adr, int port) {//on peut mettre adr à null et port à 0
	
		if (adr==null){
			int reservedPort= listeningPort+ cptSocket;
			ManagerTCP managerTCP = new ManagerTCP(this,reservedPort);
			if (listeManagerTCP.containsKey(id)){
				listeManagerTCP.remove(id);
			}
			listeManagerTCP.put(id, managerTCP);	
			cptSocket++;
		}
		
		else {
			ManagerTCP managerTCP = new ManagerTCP(this, adr,port);
			if (listeManagerTCP.containsKey(id)){
				listeManagerTCP.remove(id);
			}
			listeManagerTCP.put(id, managerTCP);	
			cptSocket++;
		}
	}



	private int createNewUser(ControlMessage ctrlMsg) {
		int id=0;
		if (sysState.allDests.checkAvailable(ctrlMsg.getUserName())){
			
			User newUser = new User(ctrlMsg.getUserName(),sysState.sommetID,ctrlMsg.getUserAdresse(),true);
			sysState.allDests.addUser(newUser);
			id=sysState.allDests.searchUserIDByPseudo(ctrlMsg.getUserName());
			
		}
		else {
			id=sysState.allDests.searchUserIDByPseudo(ctrlMsg.getUserName());
			
			sysState.allDests.getUser(id).setIP(ctrlMsg.getUserAdresse());
			sysState.allDests.getUser(id).setStatus(true);
		}
		return id;
	}


	public void manageCtrlMsg(ControlMessage ctrlMsg) {
		int id =0;
		String order=ctrlMsg.getData();
		
		if (order.equalsIgnoreCase("hello")){			
			id=this.createNewUser(ctrlMsg);
			
			this.createManagerTCP(id,null,0);
			int localPort=listeManagerTCP.get(id).getPort();
			
			InetAddress adr= ctrlMsg.getUserAdresse();
			int port =ctrlMsg.getPort();
			this.sendControlMessage(localPort, adr,port,"socket_created");
		}

		
		else if (order.equalsIgnoreCase("socket_created")){
			
			if (sysState.allDests.checkAvailable(ctrlMsg.getUserName())){
				id= this.createNewUser(ctrlMsg);
				this.createManagerTCP(id,ctrlMsg.getUserAdresse(),ctrlMsg.getPort());
			}
			else {
				id=sysState.allDests.searchUserIDByPseudo(ctrlMsg.getUserName());
				sysState.allDests.getUser(id).setIP(ctrlMsg.getUserAdresse());
				sysState.allDests.getUser(id).setStatus(true);
				listeManagerTCP.get(id).setNewSocket(ctrlMsg.getUserAdresse(),ctrlMsg.getPort());
			}
			
		}
	}
	
	

	public void manageTxtMessage(Message msg){
		
		if (msg.getType()==Message.DataType.Text){
			String srcPseudo= msg.getSrcPseudo();
			int id= sysState.allDests.searchUserIDByPseudo(srcPseudo);
			sysState.allDests.getUser(id).getConv().addMessage(msg);
			controller.notifyNewMessage(id);
			}
	}
		

	public void manageFileMessage(Message receivedMsg,String path) {
		
		if (receivedMsg.getType()==Message.DataType.File){
			String srcPseudo= receivedMsg.getSrcPseudo();
			Message msg= new Message(DataType.File,path,receivedMsg.getDestPseudo(),srcPseudo);
			
			int id= sysState.allDests.searchUserIDByPseudo(srcPseudo);
			sysState.allDests.getUser(id).getConv().addMessage(msg);
			controller.notifyNewMessage(id);
			}		
	}


}
