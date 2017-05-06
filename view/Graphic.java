package view;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import controller.Controller;
import model.User;

public class Graphic {
	private Controller controller; 
	
//	private User current;
	
//	 public static void main(String[] args) {
//	        new Graphic();
//	    }

	    public Graphic(Controller controller, String  current, String friend) {
    //        this.current = current; 
	    	this.controller = controller ; 

	        EventQueue.invokeLater(new Runnable() {
	            @Override
	            public void run() {
	                try {
	                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
	                }
	                JFrame frame = new JFrame("Chat System");
	                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	                frame.setLayout(new BorderLayout());
	                frame.add(new ConversationPane(controller, current, friend));
	               // frame.pack();
	                frame.setSize(1250, 750);
	                frame.setLocationRelativeTo(null);
	                frame.setVisible(true);
	            }
	        });
	    }

}
