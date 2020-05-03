package datastructures;

import java.util.Collections;
import java.util.LinkedList;

public class ContactMain {

	public static void main(String[] args) {
		
//		Schreibt eine Klasse Contact mit einem Namen und der Handynummer als Instanzvariablen.
//		Erstellt eine ArrayList mit 5 Einträgen.
//		Zusätzlich soll die Klasse Contact nun auch das Interface Comparable implementieren.
//		Sortiert nun die ArrayList und gebt die Kontaktliste alphabetisch aus.
		
		LinkedList<Contact> contacts = new LinkedList<>();
		contacts.add(new Contact(198345, "Daniel"));
		contacts.add(new Contact(312312, "Kirsten"));
		contacts.add(new Contact(319876, "Zorro"));
		contacts.add(new Contact(123456789012L, "Chuck Norris"));
		contacts.add(new Contact(345678, "Bruce Lee"));
		contacts.add(new Contact(123345, "Bob Ross"));
		contacts.add(new Contact(917283, "Uwe Boll"));
		
		Collections.sort(contacts);
		
		for(Contact contact : contacts) {
			System.out.println(contact.getName() + " : " + contact.getNumber());
		}
	}
}