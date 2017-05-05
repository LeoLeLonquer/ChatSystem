package network;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import network.Message.DataType;




public class ManagerTCP extends Thread{
	ServerSocket serverSocks;
	public Socket clientSocks; 
	int port ;
	boolean connected;
	Communication comModule;

	private ObjectOutputStream writer;
	private ObjectInputStream reader;

	int type;

	public ManagerTCP(Communication comModule, int port){ //permet de créer un serveurTCP qui créera ensuite un clientTCP
		this.comModule=comModule;
		this.port=port;
		this.connected=false;
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
			this.serverSocks=null;
			this.clientSocks= new Socket(IP,port);
			this.connected=true;
			writer = new ObjectOutputStream(this.clientSocks.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		start();
	}

	public void run(){
		try {
			if (type==1 ){
				clientSocks = serverSocks.accept();
				this.connected=true;
				writer = new ObjectOutputStream(clientSocks.getOutputStream());
				reader = new ObjectInputStream(clientSocks.getInputStream());
			}
			else if (type==2){
				int flag=0;
				while(flag==0){
					if(this.clientSocks.getInputStream().available()>0){
						reader = new ObjectInputStream(this.clientSocks.getInputStream());
						flag=1;
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}


		while(connected){
			try {
				
				//TODO ici on mettait un reader.available>0 pour vérifier si truc à lire et pouvoir facilement fermer la socket 
				 //mais en fait ça empêche de lire tout message
				Message receivedMsg= (Message) reader.readObject(); 

				if (receivedMsg.getType()==DataType.Text){
					comModule.manageTxtMessage(receivedMsg);
				}
				else if (receivedMsg.getType()==DataType.File){
					// TODO demande-t-on à l'utilisateur s'il veut télécharger le fichier ?



					Message msgWithFileLength = (Message) reader.readObject();
					int length = Integer.parseInt(msgWithFileLength.getData());//le deuxième message contient la taille totale du fichier

					//le message est enregistré dans le dossier courant de l'utilisateur
					String path=System.getProperty("user.dir") + "/" + receivedMsg.getData();//le premier message contient le nom du fichier

					OutputStream receivedFile = new FileOutputStream(path);//fichier buffer 
					InputStream input = clientSocks.getInputStream();//entrée des fichiers 

					byte[] bytes = new byte[16*1024];
					int fileSize = 0;
					int count = input.read(bytes); //lecture du paquet TCP et enregistré dans bytes
					while (fileSize < length && count > 0) {
						receivedFile.write(bytes, 0, count);
						fileSize += count;
						count=input.read(bytes);
					}
					receivedFile.close();

					comModule.manageFileMessage(receivedMsg,path);
				}
			} catch (NumberFormatException | ClassNotFoundException
					| IOException e) {
				e.printStackTrace();
			}
		}
	}


	public void setNewClientSocket(InetAddress IP, int port) {
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
			writer.writeObject((Message)msg);
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
			//le premier message contient le nom du fichier
			Message msgWithName = new Message(DataType.File, file.getName(),destPseudo, srcPseudo);
			this.sendMessage(msgWithName);

			//le deuxième message contient la taille totale du fichier
			Message msgWithFileLength = new Message(DataType.File, file.length() + "", destPseudo, srcPseudo);
			this.sendMessage(msgWithFileLength);

			byte[] bytes = new byte[16 * 1024];//16*1024 = taille max des paquets TCP
			InputStream in = new FileInputStream(file);
			OutputStream out = clientSocks.getOutputStream();

			int cpt;
			cpt = in.read(bytes); //lecture du fichier et enregistrement dans bytes

			while (cpt > 0) {
				out.write(bytes, 0, cpt);
				cpt = in.read(bytes);
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

	public void close(){
		try {
			this.connected=false;
			reader.close();
			writer.close();
			this.clientSocks.close();
			if (this.type==1){
				this.serverSocks.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.interrupt();
	}

}