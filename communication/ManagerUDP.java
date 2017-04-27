package communication;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class ManagerUDP extends Thread{
	
	Communication comModule;
	DatagramSocket datagramSocket;
	byte[] receiveData;
	byte[] sendData;

	public ManagerUDP(Communication comModule) throws SocketException{
		this.comModule=comModule;
		datagramSocket = new DatagramSocket(comModule.listeningPort);
		receiveData = new byte[1024];
		sendData = new byte[1024];
		datagramSocket.setBroadcast(true);
		start();
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
		InetAddress broadcastAddress = InetAddress.getByName("255.255.255.255");
		this.sendControlMessage(ctrlMsgToSend,broadcastAddress,comModule.listeningPort);
		datagramSocket.setBroadcast(false);

	}
	
	public void closeSocket(){
		datagramSocket.close();
	}
	
	
	public void run(){
		while(true){
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
	
}
