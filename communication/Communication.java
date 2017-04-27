package communication;

import controller.Controller;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.HashMap;

import communication.Message.DataType;
import model.SystemState;
import model.User;

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
			System.out.println("Démarrage comModule");
			try {
				System.out.println("Envoi de hello");
				ControlMessage ctrlMsg= this.createControlMessageWithLocalID(15530, "hello");
				this.ManagerUDP.sendBroadcastedControlMessage(ctrlMsg);
				System.out.println("Fin envoi hello");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		
		while(true){

		
		}	
	}
	
// ******************partie create***************************///
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
			
			User newUser = new User(ctrlMsg.getUserName(),sysState.getSommetID(),ctrlMsg.getUserAdresse(),true);
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
	
	private ControlMessage createControlMessageWithLocalID(int localPort, String str){
		String currentUser=sysState.getLoggedUser().getPseudo();
		InetAddress localAdr = null;
		try {
			localAdr = ToolsCom.getLocalHostLANAddress();
		} catch (UnknownHostException e1) {
			e1.printStackTrace();
		}
		ControlMessage ctrlMsgToSend= new ControlMessage(currentUser,localAdr,localPort,str);
		
		return ctrlMsgToSend;
	}

// ******************partie send***************************///

	private void sendTxtMessage(String str, String destPseudo, String srcPseudo){
		Message msg= new Message(DataType.Text, str, destPseudo,srcPseudo);
		int id= sysState.allDests.searchUserIDByPseudo(destPseudo);
		this.listeManagerTCP.get(id).sendMessage(msg);
	}
	
	private void sendFileMessage(File file, String destPseudo, String srcPseudo){
		int id= sysState.allDests.searchUserIDByPseudo(destPseudo);
		this.listeManagerTCP.get(id).sendFile(file,destPseudo,srcPseudo);
	}

	// ******************partie manage***************************///

	
	public void manageCtrlMsg(ControlMessage ctrlMsg) {
		int id =0;
		String order=ctrlMsg.getData();
		
		if (order.equalsIgnoreCase("hello")){
			System.out.println("Hello reçu");
			System.out.println("Début création User et socketTCP");

			id=this.createNewUser(ctrlMsg);
			//TODO faire le check de id=-1
			this.createManagerTCP(id,null,0);
			int localPort=listeManagerTCP.get(id).getPort();
			
			System.out.println("Fin création User et socketTCP");

			InetAddress destAdr= ctrlMsg.getUserAdresse();
			int destPort =ctrlMsg.getPort();
			
			System.out.println("Début envoi ctrlMsg avec port");

			ControlMessage ctrlMsgToSend = this.createControlMessageWithLocalID(localPort, "socket_created");
			this.ManagerUDP.sendControlMessage(ctrlMsgToSend, destAdr,destPort);

			System.out.println("Fin envoi ctrlMsg avec port");

		}

		
		else if (order.equalsIgnoreCase("socket_created")){
			
			System.out.println("socket_created reçu");

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
			System.out.println("Fin socket_created");

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
