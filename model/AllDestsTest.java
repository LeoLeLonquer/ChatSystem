/**
 * 
 */
package model;

import static org.junit.Assert.*;

import java.net.Inet4Address;
import java.net.InetAddress;

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
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		AllDests.id = 0; 
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
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

//	/**
//	 * Test method for {@link model.AllDests#AllDests()}.
//	 */
//	@Test
//	public void testAllDests() {
//		fail("Not yet implemented");
//	}

	/**
	 * Test method for {@link model.AllDests#addUser(model.User)}.
	 */
	@Test
	public void testAddUser() {
		
	//	assertTrue(); 
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link model.AllDests#addGroup(model.Groupe)}.
	 */
	@Test
	public void testAddGroup() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link model.AllDests#removeUser(int)}.
	 */
	@Test
	public void testRemoveUser() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link model.AllDests#removeGroup(int)}.
	 */
	@Test
	public void testRemoveGroup() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link model.AllDests#searchUser(int)}.
	 */
	@Test
	public void testSearchUser() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link model.AllDests#searcGroup(int)}.
	 */
	@Test
	public void testSearcGroup() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link java.lang.Object#finalize()}.
	 */
	@Test
	public void testFinalize() {
		fail("Not yet implemented");
	}

}
