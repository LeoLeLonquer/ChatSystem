package model;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ToolsCom {

	
	public static ControlMessage createControlMessageFromData(byte[] receivedData){
        try {
            ByteArrayInputStream in = new ByteArrayInputStream(receivedData);
            ObjectInputStream is = new ObjectInputStream(in);
            return (ControlMessage) is.readObject();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
	}
	
	public static Message createMessageFromData(byte[] receivedData){
        try {
            ByteArrayInputStream in = new ByteArrayInputStream(receivedData);
            ObjectInputStream is = new ObjectInputStream(in);
            return (Message) is.readObject();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
	}
	
    public static byte[] createDataArrayFromSerializedObject(Object obj) {
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            ObjectOutputStream os = new ObjectOutputStream(outputStream);
            os.writeObject(obj);
            byte[] data = outputStream.toByteArray();
            return data;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
