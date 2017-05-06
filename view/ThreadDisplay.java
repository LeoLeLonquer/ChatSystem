package view;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

import javax.swing.JTextArea;

import model.User;

public class ThreadDisplay extends Thread {
	
	private JTextArea textArea; 
	private String us;

	 public ThreadDisplay(String name, String us, JTextArea textArea){
		    super(name);
		    this.us = us; 

			this.textArea = textArea; 
//			this.elapsedSeconds = elapsedSeconds; 
		  }

		  public void run(){
			  String line; 
		    while (true){ 
		    	if ((line=textArea.getText())!= "" ){
					textArea.append( us + ": " + line + "\n");
					
					}  
		    }
		  }

}
