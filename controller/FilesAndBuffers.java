package controller ; 

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class FilesAndBuffers {
	
	//   * Fichiers : lire / écrire dans un fichier en utilisant les OutputStream et consorts.
	private BufferedWriter writer ; 
	private BufferedReader reader; 
	
	public FilesAndBuffers() { //(BufferedWriter writer , BufferedReader reader){ 
//		this.reader = reader; 
//		this.writer = writer; 
	}
	
	public void writeInFile (String toWrite, File file) throws IOException {
		try {
			FileWriter fw = new FileWriter(file);
			BufferedWriter write = new BufferedWriter(fw); 
			write.write(toWrite);		
			write.flush(); 

		} catch (IOException e) {
			System.out.println("ERROR: Failed to create FileWriter"); 
		} 
	}
	
	public void readFromFile(File file)
	{
		FileReader fr;
		try {
			fr = new FileReader(file);
			BufferedReader read = new BufferedReader(fr); 
			try {
				System.out.println(read.readLine());
			} catch (IOException e) {
				System.out.println("ERROR: Failed to read from file "); 
			}
		} catch (FileNotFoundException e) {
			System.out.println("ERROR: Failed to create FileReader "); 
		}
	}
	
	public static void main(String[] args) {
		
		FilesAndBuffers test = new FilesAndBuffers(); 
		File f = new File("writeIn.txt");
		// note: the file will be created by itself. It's found in the project folder. 
		try {
			test.writeInFile("WESH CA MARCHE???", f) ;
			System.out.println("done!"); 
		} catch (IOException e) {
			System.out.println("ERROR: couldn't write in file"); 
		} 
		test.readFromFile(f); 
	}
		

}
