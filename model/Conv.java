package model;

import java.util.ArrayList;
import java.util.Iterator;

import network.Message;
import network.Message.DataType;



public class Conv {

	ArrayList<Message> listeMessage;


	public Conv(){
		listeMessage=new ArrayList<Message>(100);
	}

	public void addMessage(Message msg){
		listeMessage.add(msg);
	}

	public void deleteConv(){
		listeMessage.clear();
	}

	public String readAllConv(){
		String str="";
		Message msg;

		Iterator <Message> itr = listeMessage.iterator();

		while(itr.hasNext()){
			msg= itr.next();
			if (msg.getType()==DataType.Text){
				str=str+"\n-"+msg.getSrcPseudo()+msg.getData();
			}
			else if (msg.getType()==DataType.File){
				//TODO
			}

		}
		return str;
	}

	public String readLastMessage(){
		String str="";

		int lastIndex= listeMessage.lastIndexOf(listeMessage);
		Message msg= listeMessage.get(lastIndex);

		if (msg.getType()==DataType.Text){
			str=str+"\n-"+msg.getSrcPseudo()+" : "+msg.getData();
		}
		else if (msg.getType()==DataType.File){
			//TODO
		}

		return str;



	}

}
