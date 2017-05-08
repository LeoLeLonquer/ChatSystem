package zTestJUnit;

import java.io.File;
import java.net.InetAddress;
import java.util.concurrent.TimeUnit;

import controller.Controller;
import model.SystemState;

public class TestCommunication extends Thread{

	public class FakeController extends Controller{

		public FakeController(){
			super();
		}

		@Override
		public void notifyNewMessageFromModel(String pseudo, String msg) {
		}

		@Override
		public void notifyNewUser(String pseudo){
		}

		@Override
		public void notifyLogoutUser(String pseudo){
		}
	}

	public SystemState sysState;
	String nom;

	public TestCommunication(){
		System.out.println("Création de sysState");
		this.nom= "toto";
		this.sysState= new SystemState(new FakeController(),nom);
		System.out.println("Fin création de sysState");
		start();
	}

	public static void main(String[] args) {
		TestCommunication test=new TestCommunication();
	}

	public void run(){
		System.out.println("start");
		//while(true){
			try {
				TimeUnit.SECONDS.sleep(1);
				System.out.println("*********Liste des utilisateurs connectés*******");
				System.out.print(this.sysState.getAllDests().toString());
				System.out.println("************************************************");
				TimeUnit.SECONDS.sleep(1);
			
				sysState.sendMessage("toto","toto","Coucou");
				sysState.sendFile("toto","toto",new File("./testdir/304.jpg"));

				//sysState.logOutLoggedUser();

//				System.out.println("Envoi message TCP");
//				sysState.getComModule().sendTxtMessage("Coucou","toto");
//				sysState.getComModule().sendTxtMessage("Coucou","Perroquet");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		

		//TEST ENVOI DE MESSAGE

		//	System.out.println("Envoi message TCP");
		//  sysState.comModule.sendTxtMessage("Coucou","toto");

			
//		System.out.println("Envoi message TCP");
		
//		sysState.sendMessage("toto","toto","Coucou");
		//Erreur quand on envoie deux 
																		//fichiers trop lourds à la suite

//		sysState.sendMessage("toto","toto","Coucou");
		sysState.sendFile("toto","toto",new File("./testdir/304.jpg"));
	


		//			
		//			
		//			try {
		//				Thread.sleep(1000);
		//			} catch (InterruptedException e) {
		//				e.printStackTrace();
		//			}
		//			

		//TEST DE FERMETURE DE SOCKET
		//			System.out.println("tentative de fermeture");
		//			int id=this.sysState.allDests.getUserID("yoyo");
		//			this.sysState.comModule.closeManagerTCP(id);
		//			System.out.println("fin tentatice de fermeture");


		//}
	}
}
