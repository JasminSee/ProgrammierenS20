package datastructures;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class FileExercise1 {

	public static void main(String[] args) {
		
//		Nehmt euch die Klasse Contact aus der Vorlesung und schreibt diese als Objekt weg und liest diese auch als Objekt wieder ein. 
//		Contact muss das Interface Serializable implementieren. Gebt den Namen und die Nummer aus. 
//		Fragt mit instanceof ab, ob das gelesene Objekt von Typ Contact ist, wenn ja castet es.
		
		File file = new File("05Vorlesung\\datastructures\\aufgabe1.txt");
		
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
		
		Contact a = new Contact(1234, "Max");
		
		try(FileOutputStream fis = new FileOutputStream(file);
			ObjectOutputStream oos = new ObjectOutputStream(fis)) {
			oos.writeObject(a);
			oos.flush();
			fis.close();
			oos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			FileInputStream fis = new FileInputStream(file);
			ObjectInputStream ois = new ObjectInputStream(fis);
			if (a instanceof Contact) {
				a = (Contact) ois.readObject();
				System.out.println(a.getName() + ": " + a.getNumber());
			}
			ois.close();
			fis.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException cnf) {
			cnf.printStackTrace();
		}

	}

}
