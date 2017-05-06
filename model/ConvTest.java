package model;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import network.Message;
import network.Message.DataType;

public class ConvTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		Conv conv=new Conv();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		Conv conv=new Conv();
	}

	@After
	public void tearDown() throws Exception {
	}


	/**
	 * Test method for {@link model.AllConv#GetLastMessage()}.
	 */
	@Test
	public void testReadLastMessage() {
		Conv conv=new Conv();

		Message msg1= new Message(DataType.Text, "Hello!", "Bobby", "Johny"); 
		Message msg2= new Message(DataType.Text, "Yo!", "Johny", "Bobby"); 
		Message msg3= new Message(DataType.Text, "How are you?", "Bobby", "Johny"); 

		conv.addMessage(msg1);
		conv.addMessage(msg2);
		conv.addMessage(msg3);

		Message resultToHave=msg3;
		Message resultWeHave=conv.getLastMessage();

		System.out.println("Test searchReadLastMessage: " ); 
		System.out.println("Result to have: " + resultToHave.toString() ); 
		System.out.println("Result we have: " + resultWeHave.toString()); 
		System.out.println("");

		assertEquals(resultWeHave,resultToHave);
		}
	
	
	/**
	 * Test method for {@link model.AllConv#ReadMessage(Message)}.
	 */
	@Test
	public void testReadMessage() {
		Conv conv=new Conv();

		Message msg1= new Message(DataType.Text, "Hello!", "Bobby", "Johny"); 
		
		String resultToHave="\nJohny : Hello!";
		String resultWeHave=conv.readMessage(msg1);

		System.out.println("Test searchReadLastMessage: " ); 
		System.out.println("Result to have: " + resultToHave.toString() ); 
		System.out.println("Result we have: " + resultWeHave.toString()); 
		System.out.println("");

		assertEquals(resultWeHave,resultToHave); 	
	}
	
	/**
	 * Test method for {@link model.AllConv#readAllConv()}.
	 */
	@Test
	public void testreadAllConv() {
		Conv conv=new Conv();

		Message msg1= new Message(DataType.Text, "Hello!", "Bobby", "Johny"); 
		Message msg2= new Message(DataType.Text, "Yo!", "Johny", "Bobby"); 
		Message msg3= new Message(DataType.Text, "How are you?", "Bobby", "Johny"); 
		
		conv.addMessage(msg1);
		conv.addMessage(msg2);
		conv.addMessage(msg3);
		
		String resultToHave="\nJohny : Hello!"+"\nBobby : Yo!"+"\nJohny : How are you?";
		String resultWeHave=conv.readAllConv();

		System.out.println("Test searchReadLastMessage: " ); 
		System.out.println("Result to have: " + resultToHave.toString() ); 
		System.out.println("Result we have: " + resultWeHave.toString()); 
		System.out.println("");

		assertEquals(resultWeHave,resultToHave); 	
	}

}

