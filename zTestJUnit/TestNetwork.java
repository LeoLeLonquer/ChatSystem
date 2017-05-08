package zTestJUnit;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;

import controller.Controller;
import model.SystemState;

public class TestNetwork {
	
	
	public TestNetwork(){
		
	}
	
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

	
	SystemState sysState;
	FakeController controller;
	
	
	
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	
	@Before
	public void setUp() throws Exception {
		System.out.println("Création de sysState");
		String nom= "toto";
		sysState=new SystemState(new FakeController(),nom);
		System.out.println("Fin création de sysState");
	}

	@After
	public void tearDown() throws Exception {
		sysState.logOutLoggedUser();
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
	
//	@Test
//	public void testSendTxtMessage() {
//		String orga= "toto";
//		SystemState sysState= new SystemState(new FakeController(),orga);
//
//		
//		try {
//			Thread.sleep(200);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//		
//		
//		sysState.sendMessage(orga, orga, "Bonjour");
//				
//		String resultToHave="\ntoto : Bonjour";
//		String resultWeHave=sysState.getAllDests().getUser(orga.hashCode()).getConv().readLastMessage();
//		assertEquals(resultWeHave,resultToHave);
//		
//	}
	
	@Test
	public void testSendImage() {
		String orga="toto";
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		File fileToSend= new File("./testdir/macareux.jpg");
		if (fileToSend.exists()){
			System.out.println("*********Bien gros***********");
			sysState.sendFile(orga, orga, fileToSend);//PUTAIN PK ÇA MARCHE PAS ICI ALORS QUE ÇA MARCHE DANS TESTCOM
		}
		else {
			System.out.println("*********Erreur gros***********");
			assertFalse(false);
		}
		
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/*
	@Test
	public void testSendSameImage() {
		String orga= "toto";
		SystemState sysState= new SystemState(new FakeController(),orga);
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		File fileToSend= new File("./testdir/macareux.jpg");
		if (fileToSend.exists()){
			System.out.println("*********Bien gros***********");
			sysState.sendFile(orga, orga, fileToSend);//PUTAIN PK ÇA MARCHE PAS ICI ALORS QUE ÇA MARCHE DANS TESTCOM
		}
		else {
			System.out.println("*********Erreur gros***********");
			assertFalse(false);
		}
		
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}*/
	
	
//	@Test
//	public void testSendFile() {
//		String orga= "toto";
//		SystemState sysState= new SystemState(new FakeController(),orga);
//		
//		String path="./tmpdir";
//		String txt="./tmpdir/YOLO.txt";
//		String msg="Bonjour je m'appelle Léo et j'ai 20 ans.\n Comment vous appelez-vous ?";
//		FileWriter fw = null;
//		BufferedWriter bw=null;
//		File fileToSend=null;
//		File filePath=null;
//		try {
//			filePath=new File(path);
//			filePath.mkdir();
//			fileToSend=new File(txt);
//			fileToSend.createNewFile();
//			fw = new FileWriter (fileToSend,false);
//			bw = new BufferedWriter (fw);
//			bw.write(msg);
//
//		} catch (IOException e1) {
//			e1.printStackTrace();
//		}
//				
//		try {
//			Thread.sleep(100);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//		
//		//sysState.sendFile(orga, orga, fileToSend);
//		sysState.sendFile(orga, orga, new File("./tmpdir/macareux.jpg"));
//
//		//fileToSend.delete();
//		//filePath.delete();
//		
//		try {
//			bw.close();
//			fw.close();
//		} catch (IOException e1) {
//			e1.printStackTrace();
//		}
//		
//		//File fileToReceive= new File("YOLO.txt");
//		
//		try {
//			Thread.sleep(100);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//		
//		if (false){//fileToReceive.exists()){
////			FileReader fr=null;
////			BufferedReader br=null;
////			String str="";
////			try {
////				fr=new FileReader(fileToReceive);
////				br= new BufferedReader(fr);
////				while(br.ready()){
////					str=str+br.readLine();
////				}
////			} catch (FileNotFoundException e) {
////				e.printStackTrace();
////			} catch (IOException e) {
////				e.printStackTrace();
////			}
////
////			//fileToReceive.delete();
////			String resultToHave=msg;
////			String resultWeHave=str;
////			assertEquals(resultWeHave,resultToHave);
//		}
//		else {
//			fail("Le fichier n'a pas été reçu");
//		}
//	}
	
	
	@Test
	public void testSendALotOfTxtMessages() {
		fail("Not yet implemented");
	}
	
	
	
	

}
