package view;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedWriter;
import java.net.Inet4Address;
import java.net.UnknownHostException;

import javax.swing.*;

import model.User;; 

public class PressEnter implements KeyListener {
	private User us; 
	private JTextArea text; 
	private BufferedWriter bw; 
	private LoginWindow lw ; 
	
	public PressEnter(User us, JTextArea theArea, BufferedWriter bw){ 
		
	}
	
	public PressEnter (User us, JTextArea theArea, LoginWindow lw){ 
		this.us = us; 
		this.text = theArea; 
		this.text.addKeyListener(this);
		this.lw = lw ; 
	}

	@Override
	public void keyPressed(KeyEvent e) {
		Inet4Address ip;
    	
		try {
	        if(e.getKeyCode() == KeyEvent.VK_ENTER){
			ip = (Inet4Address) Inet4Address.getLocalHost();
			
			
			
			this.us = new User("default", 0, ip, false); 
			this.us.setPseudo(text.getText());
			this.us.setStatus(true);
			
			ListUsers checkList = new ListUsers(this.us);
			this.lw.setVisible(false);
	        }
		} catch (UnknownHostException e1) {
			System.out.println("ERROR: Unknown Host");
		}		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
