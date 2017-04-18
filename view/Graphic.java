package view;

import java.awt.*;
import java.util.*;

import javax.swing.JButton;
import javax.swing.JLabel;
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
            GridBagConstraints c) {
JTextArea text = new JTextArea(name);
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

        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1.0;
//        makebutton("Button1", gridbag, c);
//        makebutton("Button2", gridbag, c);
//        makebutton("Button3", gridbag, c);
        c.gridwidth = GridBagConstraints.REMAINDER; //end row
        //label
        makeLabel("Conversation with ", gridbag, c);

        c.weightx = 0.0;                //reset to the default
//        makebutton("Button5", gridbag, c); //another row
//
//        c.gridwidth = GridBagConstraints.RELATIVE; //next-to-last in row
//        makebutton("Button6", gridbag, c);
//
//        c.gridwidth = GridBagConstraints.REMAINDER; //end row
//        makebutton("Button7", gridbag, c);

        c.gridwidth = 1;                //reset to the default
        c.gridheight = 1;
//        c.weighty = 1.0;
        makeLabel("List Users", gridbag, c);
        
        //text convo
        c.weighty = 1.0;
        c.gridwidth = GridBagConstraints.REMAINDER; 
        c.gridheight = 7; 
        makeTextArea("text convo", gridbag, c);
        
        c.gridwidth= 1; 
        c.gridheight = GridBagConstraints.REMAINDER; 
        c.weighty=1.0; 
        makebutton("List Users", gridbag, c); // to replace with an actual list lol 

        c.weightx = 1.0; 
        c.gridwidth = GridBagConstraints.RELATIVE; 
        c.gridheight= 2; 
        makeTextArea("msg to send", gridbag, c); 

        c.weighty = 0.0;                //reset to the default
        c.gridwidth = GridBagConstraints.REMAINDER; //end row
        c.gridheight = 1;               //reset to the default
        makebutton("Send", gridbag, c);

        setSize(300, 100);
    }

    public static void main(String args[]) {
        Frame f = new Frame("GridBag Layout Example");
        Graphic ex1 = new Graphic();

        ex1.init();

        f.add("Center", ex1);
        f.pack();
        f.setSize(f.getPreferredSize());
        f.show();
    }
}