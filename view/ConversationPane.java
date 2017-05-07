package view;
import controller.*;
import model.User;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
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

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class ConversationPane extends JPanel implements ActionListener{
	private String currentUser; 
	private String friendPseudo; 
	private JButton sendMsg; 
	private JButton sendFile; 
	private JTextArea msgArea; 
	private JTextArea convo; 
	private Interface intf; 
	
	 public ConversationPane(Interface intf, String current, String friendPseudo) {
		 this.currentUser = current; 
		 this.friendPseudo = friendPseudo; 
		 this.intf = intf; 
		 
		 GridBagLayout gb = new GridBagLayout(); 
         GridBagConstraints c = new GridBagConstraints(); 
         setLayout(gb);
 //        JPanel pane1 = createLeftPane();
         JPanel pane2 = createRightPane();

//         c.weightx = 1;
//         c.weighty= 1; 
//         c.fill= GridBagConstraints.BOTH; 
//         gb.setConstraints(pane1, c);
//         add(pane1);
         
         c.weightx = 3;
         c.weighty= 1; 
         c.gridwidth = 3; 
         c.fill= GridBagConstraints.BOTH; 
         gb.setConstraints(pane2, c);
         add(pane2);
     }
	
	private void DisplayMessage(String currentUser, String friendUser, JButton send, 
				JTextArea yourArea, JTextArea convo){ 

		send.addActionListener(this);
		yourArea.addKeyListener(new KeyListener(){
		    @Override
		    public void keyPressed(KeyEvent e){
		    	if(e.getKeyCode() == KeyEvent.VK_ENTER){
		        	String msg = yourArea.getText(); 
		        	if (msg!= null)
		        		convo.append(currentUser + ": " + msg + "\n");
		        	intf.transferMsgToController(currentUser, friendUser, msg); // msg transferred to controller 
					e.consume();
					yourArea.setText("" );
		        }
		    }

		    @Override
		    public void keyTyped(KeyEvent e) {		    
		    }

		    @Override
		    public void keyReleased(KeyEvent e) {
		    }
		});
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==this.sendMsg){ 			
			String msg = this.msgArea.getText(); 
        	if (msg!= null)
        		this.convo.append(currentUser + ": " + msg + "\n");
        	intf.transferMsgToController(this.currentUser, this.friendPseudo, msg); // msg transferred to controller 

				this.msgArea.setText("");
		}
		
	}

     protected JPanel createRightPane() {

         JPanel panel = new JPanel(new BorderLayout());
         panel.setBorder(new EmptyBorder(10, 10, 10, 10));
         panel.setBackground(Color.PINK);

         JPanel content = new JPanel(new GridBagLayout());
         content.setOpaque(false);
         
         /// YOUR MESSAGE
         JPanel yourMsgPane = new JPanel();
         GridBagLayout gbMessage = new GridBagLayout(); 
         GridBagConstraints c = new GridBagConstraints(); 
         yourMsgPane.setLayout(gbMessage); 
         yourMsgPane.setBackground(Color.YELLOW);
         
         c.weightx = 5;
         c.weighty= 1; 
         c.gridheight= 2; 
         c.fill= GridBagConstraints.BOTH; 
         JTextArea text = new JTextArea("");
         text.setEditable(true);
         this.msgArea = text; 

         JScrollPane jsp = new JScrollPane(text); // because otherwise, the textarea extends and erases the send button
         gbMessage.setConstraints(jsp, c);
         yourMsgPane.add(jsp);

         c.weightx = 1;
         c.weighty = 0.5; 
         c.gridheight= 1; 
         c.gridwidth= GridBagConstraints.REMAINDER; 
         c.fill= GridBagConstraints.BOTH; 
         
         JButton send = new JButton("Send");  // add send button
         this.sendMsg = send; 
         gbMessage.setConstraints(send, c);
         yourMsgPane.add(send); 

         JButton senfFiles = new JButton("Send Files"); // add send files button 
         this.sendFile = senfFiles; 
         gbMessage.setConstraints(senfFiles, c);
         yourMsgPane.add(senfFiles); 
         
   //////////////////////////////////////////////////////

         //TOP PANE
         JScrollPane top ;// pane that contains the textarea
         JTextArea convo = new JTextArea();
         convo.setFont(new Font("Sans Serif", Font.PLAIN, 12));
         convo.setEditable(false);
        this.convo = convo;  
         
         top = new JScrollPane(convo); // because otherwise, the textarea extends and erases the send button
         top.setBorder(new LineBorder(Color.GRAY, 7));

         JPanel bottom = new JPanel(new GridLayout(1, 0));
         bottom.add(yourMsgPane);
         bottom.add(new JScrollPane(yourMsgPane));

         GridBagConstraints gbc = new GridBagConstraints();
         gbc.gridwidth = GridBagConstraints.REMAINDER;
         gbc.weighty = 10;
         gbc.weightx = 1;
         gbc.fill = GridBagConstraints.BOTH;
         gbc.gridheight = 3; 
         content.add(top, gbc);
         
         gbc.gridwidth = GridBagConstraints.REMAINDER;
         gbc.weighty = 1;
         gbc.weightx = 1;
         gbc.fill = GridBagConstraints.BOTH;
         content.add(bottom, gbc);
         
         panel.add(content);
         
         JLabel panelTitle = new JLabel("Chatting with " + this.friendPseudo); 
         panelTitle.setForeground(Color.WHITE);
         panelTitle.setFont(new Font("SansSerif", Font.BOLD, 18));
         panel.add(panelTitle, BorderLayout.NORTH);
         

       DisplayMessage(this.currentUser, this.friendPseudo, send, text, convo); 
       FileWindow fwin = new FileWindow (senfFiles, convo);
       
         ////////////////////////////////////

         return  panel; 
     }

     protected JPanel createLeftPane() {
		 GridBagLayout gb = new GridBagLayout(); 
		 GridBagConstraints c = new GridBagConstraints(); 
		 
         JPanel panel = new JPanel(gb);
         panel.setBackground(Color.LIGHT_GRAY);
         
         JLabel listUsLabel = new JLabel ("Connected Users"); 
         listUsLabel.setForeground(Color.WHITE);
         listUsLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
         
         c.anchor = GridBagConstraints.NORTH; 
         c.gridwidth = GridBagConstraints.REMAINDER; 
         c.weighty = 1; 
         c.weightx = 0; 

         panel.add(listUsLabel, c);
         
         c.weighty = 15; 
         c.weightx = 1; 
         c.gridwidth = GridBagConstraints.REMAINDER; 
         c.fill = GridBagConstraints.BOTH; 
         
         JPanel listUs = new JPanel(new GridLayout(0, 1, 0, 3)); 
         for (int i = 0; i < 40; i++ ) { 
        	 JButton button = new JButton ("User " + i); 
        	 listUs.add(button);
         }
         
         JPanel container = new JPanel(new FlowLayout(FlowLayout.CENTER, 2, 2));
         container.setBackground(Color.CYAN);
         container.setSize(listUs.getSize());
         
         container.add(listUs);
         JScrollPane scrollPane = new JScrollPane(container);
         
         panel.add(scrollPane, c);

         return panel;

     }

 }
