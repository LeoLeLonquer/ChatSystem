/**
 * 
 */
package zTestJUnit;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import model.AllDests;
import model.Groupe;
import model.User;

//import static org.junit.Assert.*;



import java.net.Inet4Address;
import java.net.InetAddress;
import java.util.HashMap;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author onina
 *
 */
public class AllDestsTest {
	Inet4Address IP1; 
	Inet4Address IP2; 
	Inet4Address IP3; 
	User u1; 
	User u2; 
	User u3; 
	Groupe g1; 
	Groupe g2; 

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		AllDests alldests = new AllDests(); 
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		IP1 = (Inet4Address) InetAddress.getLocalHost(); 
		IP2 = (Inet4Address) InetAddress.getByName("192.168.11.2"); 
		IP3 = (Inet4Address) InetAddress.getByName("192.168.11.3"); 

		u1 = new User("a", IP1, true); 
		u2 = new User("b", IP2, true); 
		u3 = new User("c", IP3, false); 

		g1 = new Groupe ("g1", 4); 
		g2 = new Groupe ("g2", 5); 
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}


	/**
	 * Test method for {@link model.AllDests#addUser(model.User)}.
	 */
	@Test
	public void testAddUser() {
		AllDests alldests = new AllDests();

		HashMap<Integer, User> resultToHave = new HashMap<Integer,User>(); 
		resultToHave.put(1, u1); 
		resultToHave.put(2, u2); 
		resultToHave.put(3, u3); 
		alldests.addUser(u1); 
		alldests.addUser(u2); 
		alldests.addUser(u3); 

		System.out.println("Test addUser: " ); 
		System.out.println("Result to have: " + resultToHave.toString()); 
		System.out.println("Result we have: " + alldests.getListUsers().toString()); 
		System.out.println("");

		assertEquals(alldests.getListUsers().toString(), resultToHave.toString()); 
	}

	/**
	 * Test method for {@link model.AllDests#addGroup(model.Groupe)}.
	 */
	@Test
	public void testAddGroup() {
		AllDests alldests = new AllDests();

		HashMap<Integer, Groupe> resultToHave = new HashMap<Integer,Groupe>(); 
		resultToHave.put(4, g1); 
		resultToHave.put(5, g2); 
		alldests.addGroup(g1); 
		alldests.addGroup(g2); 

		System.out.println("Test addGroup: " ); 
		System.out.println("Result to have: " + resultToHave.toString()); 
		System.out.println("Result we have: " + alldests.getListGroups().toString()); 
		System.out.println("");


		assertEquals(alldests.getListGroups().toString(),resultToHave.toString()); 
	}

	/**
	 * Test method for {@link model.AllDests#removeUser(int)}.
	 */
	@Test
	public void testRemoveUser() {
		AllDests alldests = new AllDests();

		HashMap<Integer, User> resultToHave = new HashMap<Integer,User>(); 
		resultToHave.put(2, u2); 
		resultToHave.put(3, u3); 
		alldests.addUser(u1); 
		alldests.addUser(u2); 
		alldests.addUser(u3); 
		alldests.removeUser(1);  //remove u1

		System.out.println("Test removeUser: " ); 
		System.out.println("Result to have: " + resultToHave.toString()); 
		System.out.println("Result we have: " + alldests.getListUsers().toString()); 
		System.out.println("");

		assertEquals(alldests.getListUsers(),resultToHave); 
	}

	/**
	 * Test method for {@link model.AllDests#removeGroup(int)}.
	 */
	@Test
	public void testRemoveGroup() {
		AllDests alldests = new AllDests();

		HashMap<Integer, Groupe> resultToHave = new HashMap<Integer,Groupe>(); 
		resultToHave.put(g2.hashCode(), g2); 
		alldests.addGroup(g1); 
		alldests.addGroup(g2); 
		alldests.removeGroup(g1.hashCode()); // remove g1 

		System.out.println("Test removeGroup: " ); 
		System.out.println("Result to have: " + resultToHave.toString()); 
		System.out.println("Result we have: " + alldests.getListGroups().toString()); 
		System.out.println("");

		assertEquals(alldests.getListGroups(),resultToHave); 	
	}

	/**
	 * Test method for {@link model.AllDests#searchUser(int)}.
	 */
	@Test
	public void testSearchUser() {
		AllDests alldests = new AllDests();

		alldests.addUser(u1); 
		alldests.addUser(u2); 
		alldests.addUser(u3); 
		User resultToHave = u2; 
		User resultWeHave = alldests.getUser(u2.getUserID());

		System.out.println("Test searchUser: " ); 
		System.out.println("Result to have: " + resultToHave.toString() ); 
		System.out.println("Result we have: " + resultWeHave.toString()); 
		System.out.println("");

		assertEquals(resultWeHave,resultToHave); 	
	}

	/**
	 * Test method for {@link model.AllDests#searcGroup(int)}.
	 */
	@Test
	public void testSearchGroup() {
		AllDests alldests = new AllDests();

		alldests.addGroup(g1); 
		alldests.addGroup(g2); 
		Groupe resultToHave = g1; 
		Groupe resultWeHave = alldests.searchGroup(4);

		System.out.println("Test searcGroup: " ); 
		System.out.println("Result to have: " + resultToHave.toString()); 
		System.out.println("Result we have: " + resultWeHave.toString()); 
		System.out.println("");

		assertEquals(resultWeHave,resultToHave); 
	}
}
