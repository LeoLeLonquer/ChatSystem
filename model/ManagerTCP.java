package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ManagerTCP extends Thread{
	ServerSocket serverSocks;
	Socket newClient; //List <Socket> newClients
	
	public ManagerTCP(int port){
		try {
			serverSocks= new ServerSocket(port);
		} catch (IOException e) {
			System.out.println("Erreur lors de la création du serveur");
			e.printStackTrace();
		}
	}
	
	public void run(){
		try {
			newClient = serverSocks.accept();
			BufferedWriter out = new BufferedWriter(new OutputStreamWriter(newClient.getOutputStream(),"UTF-8"));
			BufferedReader in = new BufferedReader(new InputStreamReader(newClient.getInputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	
	}
}
