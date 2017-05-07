package view;

import javax.swing.JButton;

import controller.Controller;

public class Interface {
	
	private Graphic g; 
	private ListUsers lu; 
	private LoginWindow lw; 
	private String currentUs;
	private String friendUs; 
	private Controller controller; 
	
	public Interface(){
		this.controller = new Controller(); 
		this.lw = new LoginWindow(this, "Chat System!"); 		
		this.currentUs = lw.getCurrentUs(); 
	}
	
	public void launchListUsers(){
			this.lu=new ListUsers(this.currentUs, this.controller.testListUsersEmpty());
	}
	
	public Graphic getGraphic() {
		return this.g; 
	}
	
	public ListUsers getListUsers(){
		return this.lu;
	}
	
	public LoginWindow getLoginWindow(){
		return this.lw; 
	}
	
	public String getCurrentUs(){ 
		return this.currentUs ; 
	}
	
	
	public void setCurrentUs(String s){ 
		this.currentUs=s; 
	}
	
	public String getFriendUs(){
		return this.friendUs; 
	}
	
	public Controller getController (){
		return this.controller; 
	}
	
	public void addNewUser (String pseudoNew){
		JButton j = new JButton (pseudoNew); 
		j.addActionListener(this.lu);
		this.lu.getListButtons().add(j); // gotta add it bc the actionperformed method in ListUsers checks the list 
		this.lu.add(j);
		this.lu.revalidate();
		this.lu.repaint();
	}
	
	public void transferMsgToController(String source, String dest, String msg){
		this.controller.sendMessage(source, dest, msg);
	}
	
	public void transferFileToController(){
		
	}
	
	public void receiveMsg(String friendUser, String message){
		// display msg: append in graphic's convopane 
	}
	
	public void notifyLogout(){ // view tells controller that currentUser just logged out 
		// mini window
		//signal to controller 
	}

	public void removeUser(String pseudo) {
		
	}

}
