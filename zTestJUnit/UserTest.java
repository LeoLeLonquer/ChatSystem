package zTestJUnit;

import static org.junit.Assert.*;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import model.User;
import network.Message;
import network.ToolsCom;
import network.Message.DataType;

public class UserTest {
	protected User us;


	@BeforeClass
	public static void setUpBeforeClass() throws Exception {

	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		us= new User("yololo",ToolsCom.getLocalHostLANAddress(),true);
	}

	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link model.User#GetPseudo()}.
	 */
	@Test
	public void testGetPseudo() {
		String resultToHave="yololo";
		String resultWeHave=us.getPseudo();
		assertEquals(resultWeHave,resultToHave);
	}

	/**
	 * Test method for {@link model.User#GetUserID()}.
	 */
	@Test
	public void testGetUserID() {

		String str="yololo";
		int resultToHave=str.hashCode();
		int resultWeHave=us.getUserID();
		assertEquals(resultWeHave,resultToHave);

	}

	/**
	 * Test method for {@link model.User#GetIP()}.
	 */
	@Test
	public void testGetIP() {
		InetAddress resultToHave = null;
		try {
			resultToHave = ToolsCom.getLocalHostLANAddress();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		InetAddress resultWeHave=us.getIP();
		assertEquals(resultWeHave,resultToHave);

	}


	/**
	 * Test method for {@link model.User#GetStatus()}.
	 */
	@Test
	public void testGetStatus() {
		boolean resultToHave=true;
		boolean resultWeHave=us.getStatus();
		assertEquals(resultWeHave,resultToHave);
	}

	/**
	 * Test method for {@link model.User#GetStatus()}.
	 */
	@Test
	public void testGetConv() {

		Message msg1= new Message(DataType.Text, "Hello!", "Bobby", "Johny"); 
		Message msg2= new Message(DataType.Text, "Yo!", "Johny", "Bobby"); 
		Message msg3= new Message(DataType.Text, "How are you?", "Bobby", "Johny"); 

		us.getConv().addMessage(msg1);
		us.getConv().addMessage(msg2);
		us.getConv().addMessage(msg3);

		String resultToHave="\nJohny : Hello!"+"\nBobby : Yo!"+"\nJohny : How are you?";
		String resultWeHave=us.getConv().readAllConv();
		assertEquals(resultWeHave,resultToHave);

	}


	/**
	 * Test method for {@link model.User#SetIP()}.
	 */
	@Test
	public void testSetIP() {

		us.setIP(InetAddress.getLoopbackAddress());
		InetAddress resultToHave=InetAddress.getLoopbackAddress();
		InetAddress resultWeHave=us.getIP();
		assertEquals(resultWeHave,resultToHave);

	}


	/**
	 * Test method for {@link model.User#SetStatus()}.
	 */
	@Test
	public void testSetStatus() {

		us.setStatus(false);
		boolean resultToHave=false;
		boolean resultWeHave=us.getStatus();
		assertEquals(resultWeHave,resultToHave);

	}



}
