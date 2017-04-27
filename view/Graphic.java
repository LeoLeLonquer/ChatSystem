package view;

import java.awt.*;
import javax.swing.UIManager.*;

import java.util.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import java.applet.Applet;

public class Graphic extends Applet {
	
	private JLabel sendLabel;
	private JLabel convoLabel; 
	private JLabel listUserLabel; 
	// create a class that manages the ihm for lists of users? 
	// for loop: for all connected users, create a button that links to the user? opens a new window? 

	 // text areas
	private JTextArea msgSend;
	private JTextArea convo; 

	private JButton send;

    protected void makebutton(String name,
                              GridBagLayout gridbag,
                              GridBagConstraints c) {
        Button button = new Button(name);
        gridbag.setConstraints(button, c);
        add(button);
    }
    
    protected void makeTextArea(String name,
            GridBagLayout gridbag,
            GridBagConstraints c, boolean editable) {
JTextArea text = new JTextArea(name);
text.setEditable(editable);
gridbag.setConstraints(text, c);
add(text);
}
    
    protected void makeLabel(String name,
            GridBagLayout gridbag,
            GridBagConstraints c) {
JLabel label = new JLabel(name);
gridbag.setConstraints(label, c);
add(label);
}


    public void init() {
        GridBagLayout gridbag = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();

        setFont(new Font("SansSerif", Font.PLAIN, 25));
        setLayout(gridbag);

//        c.fill = GridBagConstraints.BOTH;
//        c.weightx = 1.0;
//        makebutton("Button1", gridbag, c);
//        makebutton("Button2", gridbag, c);
//        makebutton("Button3", gridbag, c);
//        c.gridwidth = GridBagConstraints.REMAINDER; //end row
        
        //label to say who ure talking to 
        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;        
        c.weightx = 1;
        makeLabel("Conversation with ", gridbag, c);
        
        // the list of users, in a list of scrollable buttons 
        //TODO: make it scrollable lol 
 //       GridLayout gr = new GridLayout(0, 1); // gridlayout panel pour la liste des users(si on met 0 pour le nb de lignes, 
        //il en fait autant que nécessaire
        ListUsers listUsers = new ListUsers(); 
        c.gridx = 0;
        c.gridy = 1;
       // c.fill = GridBagConstraints.BOTH;
        c.weightx = 0;
        c.weighty = 8; 
//        c.gridwidth= 1; 
        gridbag.setConstraints(listUsers, c);
        gridbag.addLayoutComponent(listUsers, c);

        
        //text convo
        c.weighty = 7;
        c.weightx = 7; 
        c.fill = GridBagConstraints.BOTH ; 
        c.gridx = 1;
        c.gridy = 1;
        makeTextArea("text convo", gridbag, c, false);
        
        // text area to write your msg
        c.weighty=0.8;
        c.weightx= 0.7; 
        c.fill = GridBagConstraints.BOTH; 
        c.gridx = 1;
        c.gridy = 2;
        makeTextArea("msg to send", gridbag, c, true); 

        // button to send your message
        c.ipady = 0;       //reset to default
        c.weighty = 1.0;   //request any extra vertical space
        c.anchor = GridBagConstraints.PAGE_END; //bottom of space
        c.insets = new Insets(10,0,0,0);  //top padding
        c.gridx = 2;       //aligned with button 2
        c.gridwidth = 1;   
        c.gridheight=1; 
        c.gridy = 2;       //third row
        c.weighty = 0.1;                //reset to the default
        makebutton("Send", gridbag, c);

        setSize(300, 100);
    }

    public static void main(String args[]) {
        Frame f = new Frame("ChatSystem");
        Graphic ex1 = new Graphic();

        ex1.init();

        f.add("Center", ex1);
        f.pack();
        f.setSize(f.getPreferredSize());
        f.show();
    }
}