package controller;

import view.Interface;

public class TestAddUser {
	private Interface itf; 

	public TestAddUser() {
	//	itf = new Interface(); 
	}
	
	public void aNewUserArrived(String newUser){
		this.itf.addNewUser(newUser);
	}
	
	public static void main(String args[]){
		TestAddUser tau = new TestAddUser(); 
		
				try {
					Thread.sleep(3000);
					
					tau.itf.addNewUser("Marie");
					
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		
	
}
