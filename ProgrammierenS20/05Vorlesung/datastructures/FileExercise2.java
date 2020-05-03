package datastructures;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class FileExercise2 {

	public static void main(String[] args) {
		
//		Schreibt mit Hilfe des PrintWriters einen Text in eine Datei. Diesen Text sollt ihr mit Hilfe des Scanners wieder einlesen.
		
		File file = new File("05Vorlesung\\datastructures\\aufgabe2.txt");
		
		if (file.exists()) {
			System.out.println("Die Datei " + file.getName() + " existiert");
		} else {
			System.out.println("Die Datei " + file.getName() + " wird angelegt");
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println("das Programm lebt och");
		}
		
		try (FileWriter out = new FileWriter(file);
			PrintWriter writer = new PrintWriter(out)) {
				
			writer.println("Schreibt mit Hilfe des PrintWriters einen Text in eine Datei. Diesen Text sollt ihr mit Hilfe des Scanners wieder einlesen.");
			writer.close();
			out.close();		
					
		} catch(IOException e) {
			e.printStackTrace();
		}
			
		try {
			Scanner scan = new Scanner(file);
				
			while (scan.hasNextLine()) {
				String text = scan.nextLine() + "\n";
				System.out.println(text);
			}
			scan.close();
		} catch (FileNotFoundException e) {
				e.printStackTrace();
		}
	}
}