package communication;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import communication.Message.DataType;



public class ManagerTCP extends Thread{
	ServerSocket serverSocks;
	Socket clientSocks; 
	int port ;
	Communication comModule;

	private ObjectOutputStream writer;
	private ObjectInputStream reader;

	int type;

	public ManagerTCP(Communication comModule, int port){ //permet de créer un serveurTCP qui créera ensuite un clientTCP
		this.comModule=comModule;
		this.port=port;
		try {
			this.type=1;
			serverSocks= new ServerSocket(port);
		} catch (IOException e) {
			System.out.println("Erreur lors de la création du serveur");
			e.printStackTrace();
		}
		start();
	}


	public ManagerTCP(Communication comModule, InetAddress IP, int port) { //permet de créer un clientTCP
		this.comModule=comModule;
		this.port=port;
		this.type=2;
		try {
			clientSocks= new Socket(IP,port);
		} catch (IOException e) {
			e.printStackTrace();
		}
		start();
	}

	public void run(){
		try {
			if (type==1)
				clientSocks = serverSocks.accept();
			writer = new ObjectOutputStream(clientSocks.getOutputStream());
			reader = new ObjectInputStream(clientSocks.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}

		while(true){
			try {
				Message receivedMsg= (Message) reader.readObject();

				if (receivedMsg.getType()==DataType.Text){
					comModule.manageTxtMessage(receivedMsg);
				}
				else if (receivedMsg.getType()==DataType.File){

					//le premier message contient le nom du fichier

					Message msgWithFileLength = (Message) reader.readObject();
					int length = Integer.parseInt(msgWithFileLength.getData());

					String path=System.getProperty("user.dir") + "/" + receivedMsg.getData();
					OutputStream receivedFile = new FileOutputStream(path);
					InputStream input = clientSocks.getInputStream();

					byte[] bytes = new byte[16*1024];
					int cptSize = 0;
					int count = 0;
					while (cptSize < length && (count = input.read(bytes)) > 0) {
						receivedFile.write(bytes, 0, count);
						cptSize += count;
					}
					receivedFile.close();

					comModule.manageFileMessage(receivedMsg,path);

				}

			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}


		}
	}


	public void setNewSocket(InetAddress IP, int port) {
		try {
			this.port=port;
			this.clientSocks= new Socket(IP,port);
			writer = new ObjectOutputStream(clientSocks.getOutputStream());
			reader = new ObjectInputStream(clientSocks.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	public void sendMessage(Message msg){
		try {
			writer.writeObject(msg);
			System.out.println("Message envoyé !");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void sendTxt(String str, String destPseudo,String srcPseudo){
		Message msg= new Message(DataType.Text, str, destPseudo,srcPseudo);
		try {
			writer.writeObject(msg);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void sendFile(File file ,String destPseudo,String srcPseudo){
		try {
			Message msgWithName = new Message(DataType.File, file.getName(),destPseudo, srcPseudo);
			this.sendMessage(msgWithName);

			Message msgWithFileLength = new Message(DataType.File, file.length() + "", destPseudo, srcPseudo);
			this.sendMessage(msgWithFileLength);

			byte[] bytes = new byte[16 * 1024];
			InputStream in = new FileInputStream(file);
			OutputStream out = clientSocks.getOutputStream();

			int cpt;
			while ((cpt = in.read(bytes)) > 0) {
				out.write(bytes, 0, cpt);
			}

			out.flush();
			in.close();

		}catch (Exception e) {
			e.printStackTrace();
		}
	}


	public int getPort() {
		return this.port;
	}

}
