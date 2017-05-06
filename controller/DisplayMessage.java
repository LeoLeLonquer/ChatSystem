package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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
		
		// static method gets current user -> no need to instantiate (passer user en static) 

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
		yourMsgArea.addKeyListener(new KeyListener(){
		    @Override
		    public void keyPressed(KeyEvent e){
		    	if(e.getKeyCode() == KeyEvent.VK_ENTER){
		        	try {
						writer.write(yourMsgArea.getText() ) ;
						e.consume();
						yourMsgArea.setText("" );
						writer.flush(); 
					} catch (IOException e1) {
						System.out.println("ERROR: couldn't write in buffer.");
					}
		        }
		    }

		    @Override
		    public void keyTyped(KeyEvent e) {
		        
		    }

		    @Override
		    public void keyReleased(KeyEvent e) {
		    }
		});
		ThreadDisplay t = new ThreadDisplay("loadmessage", this.us, this.reader, this.writer, 
						  this.convoToLoadIn);
		t.start();
	}
	//recup old convos? buffer, or files? 
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==this.sendMsg){ 			
			try {

				writer.write(yourMsgArea.getText() + "\n") ;
				yourMsgArea.setText(""); 
				writer.flush(); 
				  
				} catch (IOException e1) {
					System.out.println("ERROR: couldn't write in buffer ");
				}
		}
		
	}
}
