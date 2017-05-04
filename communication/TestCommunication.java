package communication;

import java.io.File;
import java.net.InetAddress;
import java.util.concurrent.TimeUnit;

import model.SystemState;

public class TestCommunication extends Thread{
	public SystemState sysState;
	String nom;

	public TestCommunication(){
		System.out.println("Création de sysState");
		this.nom= "toto";
		this.sysState= new SystemState(nom);
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
				TimeUnit.SECONDS.sleep(7);
				System.out.println("*********Liste des utilisateurs connectés*******");
				System.out.print(this.sysState.allDests.toString());
				System.out.println("************************************************");

			} catch (InterruptedException e) {
				e.printStackTrace();
			}


			System.out.println("Envoi message TCP");
			sysState.comModule.sendTxtMessage("Coucou",this.nom,"yoyo");
			
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			System.out.println("tentative de fermeture");
			int id=this.sysState.allDests.getUserID("yoyo");
			this.sysState.comModule.closeManagerTCP(id);
			System.out.println("fin tentatice de fermeture");



//			while(true){
//				try {
//					TimeUnit.SECONDS.sleep(2);
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
//				System.out.println("Envoi message TCP");
//				sysState.comModule.sendTxtMessage("Coucou","toto","yoyo");
//			}
		//}
	}
}
