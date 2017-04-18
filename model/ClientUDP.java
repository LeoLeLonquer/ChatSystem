package model;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class ClientUDP extends Thread {
	DatagramSocket clientSocket;
	byte[] receiveData;
	byte[] sendData;
	
	
	public ClientUDP() throws SocketException{
		clientSocket = new DatagramSocket(15530);
		clientSocket.setBroadcast(true);
		receiveData = new byte[1024];
		sendData = new byte[1024];
		start();
	}
	
	public void sendBroadcast(String str){
		InetAddress IPAddress = InetAddress.getByName("192.168.1.255");
		sendData = str.getBytes();
		DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 15530);
		clientSocket.send(sendPacket);
	}
	
	public  void sendString(InetAddress IPAddress,int port, String str){
		sendData = str.getBytes();
		DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
		clientSocket.send(sendPacket);
	}
	
	public void closeSocket(){
		clientSocket.close();
	}
	
	
	public void run(){
		while(true){
			DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
			clientSocket.receive(receivePacket);
			String sentence = new String( receivePacket.getData());

			if (sentence.equalsIgnoreCase("hello")){
				
				InetAddress adrNewCopaing=receivePacket.getAddress();
				int portNewCopaing = receivePacket.getPort(); 
				
				Communication.newCopaing(adrNewCopaing,portNewCopaing);
				
			}
			
			if (sentence.equalsIgnoreCase("socket_created")){
				Communication.newConnection(receivePacket.getAddress(),receivePacket.getPort() );
			}
					
	}
}
