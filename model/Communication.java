package model;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.HashMap;

public class Communication extends Thread {
	ManagerUDP ManagerUDP;
	HashMap<Integer,ManagerTCP> listeClientTCP ;
	ControlMessage ctrlMsg;
	int cptSocket=1;
	
	public Communication(){
		try {
			 ManagerUDP=new ManagerUDP(this);
			 listeClientTCP = new HashMap<Integer,ManagerTCP>();
			 ctrlMsg=null;
		} catch (SocketException e) {
			e.printStackTrace();
		}
	}
	
	public void createNewTCPSocket(InetAddress adr, int port ){
		String str="socket_created";
		
		ManagerUDP.sendString(adr,port,str);
	}
	
	
	
	public void run(){
		
		while(true){
			
			try {
				ManagerUDP.ctrlMsg.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			ctrlMsg=ManagerUDP.ctrlMsg;
			
			String order=ctrlMsg.getData();
			
			if (order.equalsIgnoreCase("hello")){
				String currentUser=SystemState.loggedUser;
				String msg="socket_created";
				InetAddress localAdr;
				int reservedPort= 15530+ cptSocket;
				ControlMessage ctrlMsgToSend= new ControlMessage(currentUser,localAdr,reservedPort,msg);
				ManagerUDP.sendControlMessage(ctrlMsgToSend,ctrlMsg.getUserAdresse(),ctrlMsg.getPort());
				
				ManagerTCP managerTCP = new ManagerTCP(reservedPort);
				
				
				
				
			}
			
			if (order.equalsIgnoreCase("socket_created")){
				
			}
		}	
	}
}
