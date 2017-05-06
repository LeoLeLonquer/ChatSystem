package controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

import javax.swing.JTextArea;

import model.User;

public class ThreadDisplay extends Thread {
	

	private BufferedReader reader; 
	private BufferedWriter writer; 
	private JTextArea textArea; 
	private User us;
	private double elapsedSeconds; 

	 public ThreadDisplay(String name, User us, BufferedReader reader, BufferedWriter writer, JTextArea textArea){
		    super(name);
		    this.us = us; 
			this.reader = reader; 
			this.writer = writer; 
			this.textArea = textArea; 
//			this.elapsedSeconds = elapsedSeconds; 
		  }

		  public void run(){
			  String line; 
		    while (true){ 
		    	// ce que le thread fait : readline pour receive? 
		    	try {
		    		if ((line=reader.readLine())!= null ){  // readLine reads the line... then throws it away !!! so keep it before testing:!!:
		    			textArea.append( us.getPseudo(us) + ": " + line + "\n");
						
						}  
		    		/* append text only if the same user's been talking?? possible to use a var that saves
		    		 * the user the thread was launched with in memory... then checks as long as thread runs that
		    		 * it's this user... and if it's the same then just append line		    		 * 
		    		 */
		    	} catch (IOException e1) {
					System.out.println("Error: couldn't read content"); 
				} 
		    }
		  }

}
