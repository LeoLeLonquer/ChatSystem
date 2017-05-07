package view;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import controller.Controller;
import model.User;

public class Graphic extends Thread{
	private Controller controller; 
	private String friendUs; 
    private Interface intf;
    private ConversationPane cp; 

		  public Graphic( String name,  String  current, String friend, ConversationPane cp, Interface intf) {
			  this.cp = cp; 
			  this.intf = intf; 
	        EventQueue.invokeLater(new Runnable() {
	            @Override
	            public void run() {
	            	ConversationPane cp; 
	                try {
	                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
	                }
	                JFrame frame = new JFrame("Chat System");
	                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);// only this frame will be closed 
	               // frame.setLayout(new BorderLayout());
	                frame.add(cp = new ConversationPane(intf, current, friend));
	 	                frame.add(new ConversationPane(intf, current, friend));

	               // frame.pack();
	                frame.setSize(1250, 750);
	                frame.setLocationRelativeTo(null);
	                frame.setVisible(true);
	            }
		  
		  
	        });
	    
	    }
		    public ConversationPane getConvoPane (){
				  return this.cp; 
			  }

}
