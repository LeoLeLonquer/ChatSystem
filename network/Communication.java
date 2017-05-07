package network;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.HashMap;

import network.Message.DataType;

import model.SystemState;

public class Communication {
	
	private SystemState sysState;
	private ManagerUDP ManagerUDP;
	private HashMap<Integer,ManagerTCP> listeManagerTCP ;
	private int listeningPort;
	private int cptSocket=1;
	private String perroquet="Perroquet";
	
	public Communication(SystemState sysState){
		System.out.println("Démarrage comModule");
		this.sysState=sysState;
		this.listeningPort=15530;
		try {
			ManagerUDP= new ManagerUDP(this);
		} catch (SocketException e) {
			e.printStackTrace();
		}
		listeManagerTCP=new HashMap<Integer,ManagerTCP>();
		
		try {
			System.out.println("Envoi de hello");
			ControlMessage ctrlMsg= this.createControlMessageWithLocalID(this.listeningPort, "hello");
			this.getManagerUDP().sendBroadcastedControlMessage(ctrlMsg);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	
	public int getListeningPort(){
		return this.listeningPort;
	}
	
	public ManagerUDP getManagerUDP() {
		return ManagerUDP;
	}

	
	public HashMap<Integer,ManagerTCP> getListeManagerTCP() {
		return listeManagerTCP;
	}



	// ******************partie create***************************///
	public void createManagerTCP(int id,InetAddress adr, int port) {//on peut mettre adr à null et port à 0
	
		if (adr==null){ //si adr==null c'est seulement pour créer un nouveau serveur
			System.out.println("!!!!!!!!!!!!!Serveur créé!!!!!!!!!!!!!");
			int reservedPort= listeningPort+ cptSocket;
			ManagerTCP managerTCP = new ManagerTCP(this,reservedPort);
			if (getListeManagerTCP().containsKey(id)){
				getListeManagerTCP().remove(id);
			}
			getListeManagerTCP().put(id, managerTCP);	
			cptSocket++;
		}
		
		else {														//sinon c'est pour créer un nouveau client
			System.out.println("!!!!!!!!!!!!!client créé!!!!!!!!!!!!!");
			ManagerTCP managerTCP = new ManagerTCP(this, adr,port);
			if (getListeManagerTCP().containsKey(id)){
				getListeManagerTCP().remove(id);
			}
			getListeManagerTCP().put(id, managerTCP);	
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

	public void closeManagerTCP(int id){
		System.out.println("Début de demande de fermeture de la Socket TCP "+ id);
		InetAddress adr=this.getListeManagerTCP().get(id).getClientSocks().getInetAddress();
		ControlMessage ctrlMsgToSend=createControlMessageWithLocalID(this.listeningPort, "bye");
		this.getManagerUDP().sendControlMessage(ctrlMsgToSend, adr, this.listeningPort);
		System.out.println("Fin de fermeture de la Socket TCP "+ id);
		this.getListeManagerTCP().get(id).close();
	}
	
	public void sendTxtMessage(String str, String destPseudo ){
		String srcPseudo=sysState.getLoggedUser().getPseudo();  //TODO gestion du perroquet pas très belle
		if (destPseudo.equals(sysState.getLoggedUser().getPseudo())){
			destPseudo=perroquet;
		}
		else if(destPseudo.equals(perroquet)){
			srcPseudo=perroquet;
		}
		Message msg= new Message(DataType.Text, str, destPseudo,srcPseudo);
		int id= sysState.getAllDests().getUserID(destPseudo);
		this.getListeManagerTCP().get(id).sendMessage(msg);
	}
	
	public void sendFileMessage(File file, String destPseudo){
		String srcPseudo=sysState.getLoggedUser().getPseudo();  //TODO gestion du perroquet pas très belle
		if (destPseudo.equals(sysState.getLoggedUser().getPseudo())){
			destPseudo=perroquet;
		}
		else if(destPseudo.equals(perroquet)){
			srcPseudo=perroquet;
		}
		int id= sysState.getAllDests().getUserID(destPseudo);
		this.getListeManagerTCP().get(id).sendFile(file,destPseudo,srcPseudo);
	}

	// ******************partie manage***************************///

	
	public void manageCtrlMsg(ControlMessage ctrlMsg) {
		int id =0;
		String order=ctrlMsg.getData();
		
		
		if (order.equalsIgnoreCase("hello")){ //on reçoit un message d'un explorateur
			
			System.out.println("Hello reçu");

			id=this.sysState.manageNewUser(ctrlMsg.getUserName(),ctrlMsg.getUserAdresse());// on crée un nouvel utilisateur s'il n'existe pas déjà sinon on l'jaoute si l'ancien utilisateur est déco
			//TODO faire le check de id=-1
			
			this.createManagerTCP(id,null,0);
			int localPort=getListeManagerTCP().get(id).getLocalServerPort();

			InetAddress destAdr= ctrlMsg.getUserAdresse();
			int destPort =ctrlMsg.getPort();

			ControlMessage ctrlMsgToSend = this.createControlMessageWithLocalID(localPort, "socket_created");
			this.getManagerUDP().sendControlMessage(ctrlMsgToSend, destAdr,destPort);
			
			System.out.println("Fin Hello");

		}

		//on a envoyé le Hello en prem's et on reçoit un datagram avec un port réservé
		else if (order.equalsIgnoreCase("socket_created")){
			
			System.out.println("socket_created reçu");

			if (sysState.getAllDests().checkAvailable(ctrlMsg.getUserName())){//l'utilisateur n'existe pas dans notre table AllDests
				id= this.sysState.manageNewUser(ctrlMsg.getUserName(),ctrlMsg.getUserAdresse());
				this.createManagerTCP(id,ctrlMsg.getUserAdresse(),ctrlMsg.getPort());
			}
			else {															//l'utilisateur existe déjà dans notre table allDests
				id=ctrlMsg.getUserName().hashCode();
				if (!sysState.getAllDests().getUser(id).getIP().equals(ctrlMsg.getUserAdresse())){
					if (!sysState.getAllDests().getUser(id).getStatus()){//si l'utilisateur est déconnecté, mettre à jour
						
						sysState.getAllDests().getUser(id).setIP(ctrlMsg.getUserAdresse());
						sysState.getAllDests().getUser(id).setStatus(true);
						getListeManagerTCP().get(id).setNewClientSocket(ctrlMsg.getUserAdresse(),ctrlMsg.getPort());
					}
					else {
						System.out.println("!!!!!!! Deux utilisateurs avec le même nom !!!!!!!!!!!");
					}
				}
				else { //TODO gestion du perroquet pas très belle
					if (ctrlMsg.getUserName().equals(sysState.getLoggedUser().getPseudo())){//on reçoit un message de nous-mêmes
						System.out.println("Création Perroquet");
						id= this.sysState.manageNewUser(perroquet,ctrlMsg.getUserAdresse());
						this.createManagerTCP(id,ctrlMsg.getUserAdresse(),ctrlMsg.getPort());
					}
				}
			}	
			System.out.println("Fin socket_created");

		}
		else if (order.equalsIgnoreCase("bye")){//un utilisateur se déconnecte
			System.out.println("bye reçu ");
			id=sysState.getAllDests().getUserID(ctrlMsg.getUserName());
			this.sysState.getAllDests().getUser(id).setStatus(false);
			this.getListeManagerTCP().get(id).close();
			System.out.println("fin bye");

		}
	}
	
	

	public void manageTxtMessage(Message msg){
		sysState.manageTxtMessage(msg);
	}
		

	public void manageFileMessage(Message receivedMsg,String path) {
		sysState.manageFileMessage(receivedMsg, path);
	}


	public int getPtOfUser(int idLoggedUser) {
		
		return getListeManagerTCP().get(idLoggedUser).getLocalServerPort() ;
	}
	


}
