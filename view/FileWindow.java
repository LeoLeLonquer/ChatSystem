package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTextArea;

public class FileWindow implements ActionListener{

	private Interface itf; 
	private String friendPseudo;
	private JButton sendFiles; 
	private final JFileChooser jfc; 
	private JTextArea convo; 
	
	public FileWindow (Interface itf,String friendPseudo, JButton sendFile, JTextArea convo){ 
		this.itf=itf;
		this.friendPseudo= friendPseudo;
		jfc = new JFileChooser(); 
		this.sendFiles = sendFile; 
		this.convo = convo; 
		initComponents(); 
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()== this.sendFiles){ 
		     int returnVal = jfc.showOpenDialog(new FileChooser());

		        if (returnVal == JFileChooser.APPROVE_OPTION) {
		            File file = jfc.getSelectedFile();
		            this.convo.append("File sent: " + file.getName() + "\n");
		            itf.transferFileToController(itf.getCurrentUs(), this.friendPseudo, file); // to itf, who will transfer it to controller
		        } else {
		            this.convo.append("Operation cancelled by user. \n" ); // should be in log but pff 
		        }
		}
		
	}
	
	private void initComponents(){ 
		this.sendFiles.addActionListener(this);
	}

}
