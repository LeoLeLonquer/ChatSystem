package controller;
import java.io.File;

import javax.swing.JButton;

import model.*; 
import view.*  ; 

// fait le lien entre la view et le controller? 

public class HandleMessage {
	
	private String text; 
	private File file; 
	private File log; 
	private User current; 
	private User dest; 
	
	public HandleMessage(){ 
		
	}
	
	public void sendMessage (User current, User destination, String text){
		
	}
	
	public void sendFile (User current, User destination, File file, JButton sendFile){ 
		
	}
	
	public void addMsgToLog (String text, File log){ // en m�me temps que le msg est load� dans la convo, il est ajout� dans le log
		
	}

}
