package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

import javax.swing.*;

import model.User;

public class DisplayMessage implements ActionListener {
	
	private JButton sendMsg; 
//	private JButton sendFile; 
	private JTextArea convoToLoadIn; 
	private String msg; 
	private JTextArea yourMsgArea; 
//	private File fileToSend; 
	private User us; 
//	private double elapsedSeconds ; 
	
	 private	BufferedReader reader; 
	 private	BufferedWriter writer; 
	
	public DisplayMessage(User us, JButton send, JTextArea yourArea, JTextArea convo, String msg){ 
		this.us = us; 
		this.sendMsg = send; 
		this.yourMsgArea= yourArea; 
		this.convoToLoadIn = convo ; 
		this.msg=msg; 
		

		try {
			File temp=new File("temp.txt"); //use streams instead??? or use temp as 'chat history'?
			FileWriter fw;
			fw = new FileWriter(temp);
			FileReader fr = new FileReader(temp);
			reader = new BufferedReader(fr);
			writer = new BufferedWriter(fw);
			
		} catch (IOException e) {
			System.out.println("ERROR: couldn't open File ");
		}
		
		this.sendMsg.addActionListener(this);
		ThreadDisplay t = new ThreadDisplay("loadmessage", this.us, this.reader, this.writer, 
						  this.convoToLoadIn);
		t.start();
	}
	//recup old convos? buffer, or files? 
	
	// faire en sorte qu'en tapant entrée, ça envoie le message 

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==this.sendMsg){ 			
			try {
//				  long tStart = System.currentTimeMillis();

				writer.write(yourMsgArea.getText() + "\n") ;
				yourMsgArea.setText(""); 
				writer.flush(); 
				
//				 long tEnd = System.currentTimeMillis();
//				  long tDelta = tEnd - tStart;
//				  double elapsedSeconds = tDelta / 1000.0;
				  
				} catch (IOException e1) {
					System.out.println("ERROR: couldn't write in buffer ");
				}
		}
		
	}
}
