package controller;

import view.Interface;

public class LaunchChatSystem extends Thread {
	
	public static void main(String[] args) {
		Controller controller=new Controller();
		Interface itf=new Interface(controller);
		controller.setInterface(itf);
	}

}
