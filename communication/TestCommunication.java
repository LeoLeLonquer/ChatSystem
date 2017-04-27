package communication;

import model.SystemState;

public class TestCommunication {
	public static void main(String[] args) {
		SystemState sysState= new SystemState("Jojo");
		
		Communication comModule = new Communication (sysState);
		
		
		
	}
}
