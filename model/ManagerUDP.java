package model;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class ManagerUDP extends Thread{
	
	Communication comModule;
	DatagramSocket datagramSocket;
	byte[] receiveData;
	byte[] sendData;
	ControlMessage ctrlMsg;

	public ManagerUDP(Communication comModule) throws SocketException{
		this.comModule=comModule;
		datagramSocket = new DatagramSocket(15530);
		datagramSocket.setBroadcast(true);
		receiveData = new byte[1024];
		sendData = new byte[1024];
		ctrlMsg=null;
		this.start();
	}
	
	
	public void sendBroadcast(String str){
		InetAddress IPAddress = InetAddress.getByName("192.168.1.255");
		sendData = str.getBytes();
		DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 15530);
		datagramSocket.send(sendPacket);
	}
	
	public  void sendString(InetAddress IPAddress,int port, String str){
		sendData = str.getBytes();
		DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
		datagramSocket.send(sendPacket);
	}
	
	public  void sendControlMessage(ControlMessage ctrlMsgToSend, InetAddress destAdr, int destPort){
		sendData=ToolsCom.createDataArrayFromSerializedObject(ctrlMsgToSend);
		DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, destAdr, destPort);
		datagramSocket.send(sendPacket);
	}
	
	public void closeSocket(){
		datagramSocket.close();
	}
	
	
	public void run(){
		while(true){
			DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
			datagramSocket.receive(receivePacket);
			
			ctrlMsg= ToolsCom.createControlMessageFromData(receiveData);
			
			ctrlMsg.notify();	
	}

}
