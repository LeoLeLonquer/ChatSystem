package controller;

import java.io.File;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;

import javax.swing.JButton;

import model.*;
import view.ListUsers;
import view.LoginWindow;

public class Controller {

	SystemState sysState;
	//Graphics interface;
	private AllDests ad;
	private ArrayList<User> ListUsersForView;


	Controller(){

	}


	public static void main(String[] args) {
		Controller controller = new Controller();
		 LoginWindow win = new LoginWindow(controller, "Hello! ");
		 }

	//***************interface model-Controller************************

	public void notifyNewMessageFromModel(int id) {

	}



	//**************************************************************

	//***************interface view-Controller************************

	public void sendMessage (String current, String destination, String text){

	}

	public void sendFile (String current, String destination, File file){

	}

	public void addMsgToLog (String text, File log){ // en m�me temps que le msg est load� dans la convo, il est ajout� dans le log

	}

	public User getCurrentUser(){
		return this.sysState.getLoggedUser();
	}


	 public void initUs (String pseudo){
			SystemState sys = new SystemState(pseudo);
	 }

	public void addConnectedUser (User newUs, ListUsers lu){

	}

	public void updateListUsers(ListUsers lu){

	}

	public ArrayList<User> getListPseudos (){
		return this.ListUsersForView;
	}

	public ArrayList<User> testListUsersView(){
		Inet4Address IP1;
		Inet4Address IP2;
		Inet4Address IP3;
		User u1;
		User u2;
		User u3;
		ArrayList<User> al = new ArrayList<User>();

		try {
			IP1 = (Inet4Address) InetAddress.getLocalHost();
			IP2 = (Inet4Address) InetAddress.getByName("192.168.11.2");
			IP3 = (Inet4Address) InetAddress.getByName("192.168.11.3");


			u1 = new User("Does", IP1, true);
			u2 = new User("It", IP2, true);
			u3 = new User("Work?", IP3, false);

			al.add(u1);
			al.add(u2);
			al.add(u3);

		} catch (UnknownHostException e) {
			System.out.println("ERROR: Unkown Host ");
		}
		return al;
	}


//	public void askConv(int id){
//
//	}




}
