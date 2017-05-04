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

public class Communication {
	
	Controller controller;
	SystemState sysState;
	ManagerUDP ManagerUDP;
	HashMap<Integer,ManagerTCP> listeManagerTCP ;
	int listeningPort=1404;
	int cptSocket=1;
	
	public Communication(SystemState sysState){
		System.out.println("Démarrage comModule");
		 this.sysState=sysState;
		try {
			 this.ManagerUDP=new ManagerUDP(this);
			 this.listeManagerTCP = new HashMap<Integer,ManagerTCP>();
		} catch (SocketException e) {
			e.printStackTrace();
		}
		
		try {
			System.out.println("Envoi de hello");
			ControlMessage ctrlMsg= this.createControlMessageWithLocalID(this.listeningPort, "hello");
			this.ManagerUDP.sendBroadcastedControlMessage(ctrlMsg);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	
// ******************partie create***************************///
	private void createManagerTCP(int id,InetAddress adr, int port) {//on peut mettre adr à null et port à 0
	
		if (adr==null){ //si adr==null c'est seulement pour créer un nouveau serveur
			System.out.println("!!!!!!!!!!!!!Serveur créé!!!!!!!!!!!!!");
			int reservedPort= listeningPort+ cptSocket;
			ManagerTCP managerTCP = new ManagerTCP(this,reservedPort);
			if (listeManagerTCP.containsKey(id)){
				listeManagerTCP.remove(id);
			}
			listeManagerTCP.put(id, managerTCP);	
			cptSocket++;
		}
		
		else {														//sinon c'est pour créer un nouveau client
			System.out.println("!!!!!!!!!!!!!client créé!!!!!!!!!!!!!");
			ManagerTCP managerTCP = new ManagerTCP(this, adr,port);
			if (listeManagerTCP.containsKey(id)){
				listeManagerTCP.remove(id);
			}
			listeManagerTCP.put(id, managerTCP);	
			cptSocket++;
		}
	}

	
	public ControlMessage createControlMessageWithLocalID(int localPort, String str){
		String currentUser=this.sysState.getLoggedUser().getPseudo();
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

	public void closeDiscussion(int id){
		InetAddress adr=this.sysState.comModule.listeManagerTCP.get(id).clientSocks.getInetAddress();
		ControlMessage ctrlMsgToSend=this.sysState.comModule.createControlMessageWithLocalID(this.listeningPort, "bye");
		this.sysState.comModule.ManagerUDP.sendControlMessage(ctrlMsgToSend, adr, this.listeningPort);
		this.sysState.comModule.listeManagerTCP.get(id).close();
	}
	
	public void sendTxtMessage(String str,String srcPseudo, String destPseudo ){
		Message msg= new Message(DataType.Text, str, destPseudo,srcPseudo);
		int id= sysState.allDests.getUserID(destPseudo);
		this.listeManagerTCP.get(id).sendMessage(msg);
	}
	
	public void sendFileMessage(File file,String srcPseudo, String destPseudo){
		int id= sysState.allDests.getUserID(destPseudo);
		this.listeManagerTCP.get(id).sendFile(file,destPseudo,srcPseudo);
	}

	// ******************partie manage***************************///

	
	public void manageCtrlMsg(ControlMessage ctrlMsg) {
		int id =0;
		String order=ctrlMsg.getData();
		
		
		if (order.equalsIgnoreCase("hello")){ //on reçoit un message d'un explorateur
			
			System.out.println("Hello reçu");

			
			id=this.sysState.manageNewUser(ctrlMsg);// on crée un nouvel utilisateur s'il n'existe pas déjà sinon on l'jaoute si l'ancien utilisateur est déco
			//TODO faire le check de id=-1
			
			this.createManagerTCP(id,null,0);
			int localPort=listeManagerTCP.get(id).getPort();

			InetAddress destAdr= ctrlMsg.getUserAdresse();
			int destPort =ctrlMsg.getPort();

			ControlMessage ctrlMsgToSend = this.createControlMessageWithLocalID(localPort, "socket_created");
			this.ManagerUDP.sendControlMessage(ctrlMsgToSend, destAdr,destPort);
			
			System.out.println("Fin Hello");

		}

		//on a envoyé le Hello en prem's et on reçoit un datagram avec un port réservé
		else if (order.equalsIgnoreCase("socket_created")){
			
			System.out.println("socket_created reçu");

			
			if (sysState.allDests.checkAvailable(ctrlMsg.getUserName())){//l'utilisateur n'existe pas dans notre table allDests
				id= this.sysState.manageNewUser(ctrlMsg);
				this.createManagerTCP(id,ctrlMsg.getUserAdresse(),ctrlMsg.getPort());
			}
			else {															//l'utilisateur existe déjà dans notre table allDests
				id=ctrlMsg.getUserName().hashCode();
				if (!sysState.allDests.getUser(id).getIP().equals(ctrlMsg.getUserAdresse())){
					if (!sysState.allDests.getUser(id).getStatus()){//si l'utilisateur est déconnecté, mettre à jour
						
						sysState.allDests.getUser(id).setIP(ctrlMsg.getUserAdresse());
						sysState.allDests.getUser(id).setStatus(true);
						listeManagerTCP.get(id).setNewClientSocket(ctrlMsg.getUserAdresse(),ctrlMsg.getPort());
					}
					else {
						System.out.println("!!!!!!! Deux utilisateurs avec le même nom !!!!!!!!!!!");
					}
				}
			}	
			System.out.println("Fin socket_created");

		}
		else if (order.equalsIgnoreCase("bye")){//un utilisateur se déconnecte
			id=sysState.allDests.getUserID(ctrlMsg.getUserName());
			this.listeManagerTCP.get(id).close();
			this.sysState.allDests.getUser(id).setStatus(false);
		}
	}
	
	

	public void manageTxtMessage(Message msg){
		
		if (msg.getType()==Message.DataType.Text){
			String srcPseudo= msg.getSrcPseudo();
			int id= sysState.allDests.getUserID(srcPseudo);
			sysState.allDests.getUser(id).getConv().addMessage(msg);
			System.out.println("srcPseudo : "+srcPseudo+" Message : "+ msg.getData());
			//controller.notifyNewMessage(id);
			}
	}
		

	public void manageFileMessage(Message receivedMsg,String path) {
		
		if (receivedMsg.getType()==Message.DataType.File){
			String srcPseudo= receivedMsg.getSrcPseudo();
			Message msg= new Message(DataType.File,path,receivedMsg.getDestPseudo(),srcPseudo);
			
			int id= sysState.allDests.getUserID(srcPseudo);
			sysState.allDests.getUser(id).getConv().addMessage(msg);
			controller.notifyNewMessageFromModel(id);
			}		
	}
	


}
