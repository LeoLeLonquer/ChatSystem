package communication;

import java.util.concurrent.TimeUnit;

import model.SystemState;

public class TestCommunication extends Thread{
	SystemState sysState;
	
	public TestCommunication(){
		System.out.println("Création de sysState");
		this.sysState= new SystemState("toto");
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
				TimeUnit.SECONDS.sleep(10);
				System.out.println("*********Liste des utilisateurs connectés*******");
				System.out.print(this.sysState.allDests.toString());
				System.out.println("************************************************");

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			while(true){
				try {
					TimeUnit.SECONDS.sleep(2);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("Envoi message TCP");
				sysState.comModule.sendTxtMessage("Coucou","toto","yoyo");
			}
		//}
	}
}
