package model;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.SocketException;

public class Communication {
	ClientUDP ManagerUDP;
	
	
	public Communication(){
		try {
			 ManagerUDP=new ClientUDP();
		} catch (SocketException e) {
			e.printStackTrace();
		}
	}
	
	public void newCopaing(InetAddress adr, int port ){
		String str="socket_created";
		
		ManagerUDP.sendString(adr,port,str);
	}
}
