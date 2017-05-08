package view;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import controller.Controller;

public class Graphic {
	private String friendUs; 
	private Interface intf;
	private ConversationPane cp; 

	public Graphic( String  current, String friend, Interface intf) {
		this.friendUs=friend;
		this.intf = intf; 
		this.cp =new ConversationPane(this.intf, current, friend);
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
		}
		JFrame frame = new JFrame("Chat System");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);// only this frame will be closed 
		// frame.setLayout(new BorderLayout());
		frame.add(this.cp);

		// frame.pack();
		frame.setSize(750, 500);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
	}
	

	public ConversationPane getConvoPane (){
		return this.cp; 
	}

}
