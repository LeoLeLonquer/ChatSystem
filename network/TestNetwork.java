package network;

import static org.junit.Assert.*;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import model.SystemState;

public class TestNetwork {
	//static SystemState sysState;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
//		System.out.println("Création de sysState");
//		String nom= "toto";
//		TestNetwork.sysState= new SystemState(nom);
//		System.out.println("Fin création de sysState");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	
//	@Test
//	public void testConnexionBonhomme() {
//		String orga= "toto";
//		SystemState sysState= new SystemState(orga);
//
//		try {
//			Thread.sleep(1000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//		
//		//ManagerUDP manageUDP=new ManagerUDP()
//		
//		String nom="bonhomme";
//
//		//ControleMessage ctrlMsg= new ControleMessage()
//		
//		//SystemState sysState2=new SystemState(nom,1404);
//		
//		try {
//			Thread.sleep(1000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//		System.out.println("*********Liste des utilisateurs connectés*******");
//		System.out.print(sysState.allDests.toString());
//		System.out.println("************************************************");
//		
//		Boolean resultToHave=false;
//		Boolean resultWeHave=sysState.allDests.checkAvailable(nom);
//		assertEquals(resultWeHave,resultToHave);
//		
//	}
	
	@Test
	public void testDeconnexionPerroquet() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testSendMessage() {
		String orga= "toto";
		SystemState sysState= new SystemState(orga);

		
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		
		sysState.comModule.sendTxtMessage("Bonjour", orga);
				
		String resultToHave="toto : Bonjour";
		String resultWeHave=sysState.allDests.getUser(orga.hashCode()).getConv().readLastMessage();
		assertEquals(resultWeHave,resultToHave);
		
	}
	
	@Test
	public void testSendFile() {
		fail("Not yet implemented");
	}
	
	
	

}
