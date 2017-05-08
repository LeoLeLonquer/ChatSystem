package network;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class ManagerUDP extends Thread{
	
	private Communication comModule;
	private DatagramSocket datagramSocket;
	private byte[] receiveData;
	private byte[] sendData;
	boolean isOn;
	
	public ManagerUDP(Communication comModule) throws SocketException{
		this.comModule=comModule;
		datagramSocket = new DatagramSocket(comModule.getListeningPort());
		receiveData = new byte[1024];
		sendData = new byte[1024];
		datagramSocket.setBroadcast(true);
		isOn=true;
		start();
	}
	
	public ManagerUDP(Communication comModule,int listeningPort) throws SocketException{
		this.comModule=comModule;
		datagramSocket = new DatagramSocket(listeningPort);
		receiveData = new byte[1024];
		sendData = new byte[1024];
		datagramSocket.setBroadcast(true);
		isOn=true;
		start();
	}
	
	public void run(){
		while(isOn){
			System.out.println("Début de réception de packet UDP");
			
			DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
			try {
				datagramSocket.receive(receivePacket);
				System.out.println("Datagram UDP reçu");

			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println("Début gestion datagram UDP");
			ControlMessage ctrlMsg= ToolsCom.createControlMessageFromData(receiveData);
			
			comModule.manageCtrlMsg(ctrlMsg);	
			System.out.println("Fin gestion datagram UDP");
			
		}
	}
	
	
	public  void sendControlMessage(ControlMessage ctrlMsgToSend, InetAddress destAdr, int destPort) {
		sendData=ToolsCom.createDataArrayFromSerializedObject(ctrlMsgToSend);
		DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, destAdr, destPort);
		try {
			datagramSocket.send(sendPacket);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void sendBroadcastedControlMessage(ControlMessage ctrlMsgToSend) throws IOException{
//		if (!datagramSocket.getBroadcast()){ //TODO ceci ne marche pas par la magie du seigneur
//			datagramSocket.setBroadcast(true);
//		}
		InetAddress broadcastAddress = InetAddress.getByName("255.255.255.255");
		this.sendControlMessage(ctrlMsgToSend,broadcastAddress,comModule.getListeningPort());
		datagramSocket.setBroadcast(false);
	}
	
	public void closeSocket(){
		isOn=false;
		datagramSocket.close();		
	}
	

}
