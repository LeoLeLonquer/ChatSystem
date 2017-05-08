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

	private static String nom;
	private static SystemState sysState;

	public TestNetwork(){

	}

	public static class FakeController extends Controller{

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

	public static class FakeSystemState {
		static SystemState sysState;
		static Controller controller;

		static void initSysState(String nom){
			controller= new FakeController();
			sysState=new SystemState(controller,nom);
		}

		static SystemState getSysState(){
			return sysState;
		}
	}



	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		nom="toto";
		System.out.println("Création de sysState");
		FakeSystemState.initSysState("toto");
		sysState=FakeSystemState.getSysState();
		System.out.println("Fin création de sysState");

	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
//		File fileToSend=new File("YOLO.txt");
//		if (fileToSend.exists()){
//			fileToSend.delete();
//		}
//		fileToSend=new File("macareux.jpg");
//		if (fileToSend.exists()){
//			fileToSend.delete();
//		}
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
	public void testSendTxtMessage() {		
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		sysState.sendMessage(nom, nom, "Bonjour");

		String resultToHave="\ntoto : Bonjour";
		String resultWeHave=sysState.getAllDests().getUser(nom.hashCode()).getConv().readLastMessage();
		assertEquals(resultWeHave,resultToHave);

	}

	@Test
	public void testSendFile() {

		File fileToSend=new File(System.getProperty("user.dir")+"/testdir/304.jpg");
		
		if (fileToSend.exists()){
			sysState.sendFile(nom, nom, fileToSend);
		}
		else {
			System.out.println("il existe pas");
			assertFalse(false);
		}

		sysState.sendFile(nom, nom, fileToSend);

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		String path2=System.getProperty("user.dir") + "/304.jpg";
		File fileToReceive= new File(path2);

		if(!fileToReceive.exists()){
			System.out.println("Yololo");
		}
		System.out.println(path2);

		System.out.println("fileToReceive : "+fileToReceive.length()+" FileToSend : "+fileToSend.length());
		assertEquals(fileToReceive.length(),fileToSend.length());
	}

/*
	@Test
	public void testSendImage() { //ce test fonctionne
		
		File fileToSend= new File(System.getProperty("user.dir")+"/testdir/macareux.jpg");
		if (fileToSend.exists()){
			sysState.sendFile(nom, nom, fileToSend);
		}
		else {
			assertFalse(false);
		}

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		String path2=System.getProperty("user.dir") + "/macareux.jpg";
		
		System.out.println(path2);
		File file=new File(path2);
		
		if(!file.exists()){
			System.out.println("Yololo");
		}
		
		assertEquals(fileToSend.length(),file.length());

	}*/

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



	@Test
	public void testSendALotOfTxtMessages() {
		fail("Not yet implemented");
	}





}
