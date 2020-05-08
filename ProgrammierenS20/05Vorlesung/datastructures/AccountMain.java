package datastructures;

import java.util.HashSet;
import java.util.Scanner;

public class AccountMain {

	public static void main(String[] args) {
		
//		Szenario: Ein Benutzer soll sich bei einen Messengerdienstanmelden. 
//		Dabei soll es nur jeden Benutzernamen und jede Handynummer einmal geben. 
//		Erstellt eine Klasse Account mit den oben genannten Variablen und überschreibt die equals-Methode 
//		(Ihr könnt die auch automatisch generieren, alt + shift + s).
//		Erstellt eine HashSetund füge einige Accounts hinzu. Nun lass den User einen Account anlegen.
		
		boolean valid = false;
		
		HashSet<Account> accounts = new HashSet<>();
		accounts.add(new Account("Anna", 123456));
		accounts.add(new Account("Max", 111111));
		accounts.add(new Account("Jane", 222222));
		accounts.add(new Account("Tim", 333333));
		accounts.add(new Account("Tom", 444444));
		accounts.add(new Account("Mary", 555555));
		accounts.add(new Account("Jenny", 666666));
		
		Scanner in = new Scanner(System.in);
		
		System.out.println("Geben Sie Ihren Benutzeramen ein: ");
		String name = in.next();
		System.out.println("Geben Sie Ihre Nummer ein: ");
		int number = in.nextInt();
		
		Account a = new Account(name, number);

		//TODO du kannst abfragen, ob dieses Objekt in der Datenstruktur vorhanden ist_
		//TODO mit: if(accounts.contains(a))
		//TODO Aber diese Abfrage brauchst du auch nur, wenn du eine entsprechende Ausgabe haben willst. 
		//Sonst kannst du immer sagen accounts.add(a), da eine HashSet eh jedes Element nur einmal enthalten kann
		for (Account i : accounts) {
			if (i.equals(a)) {
				valid = true;
				break;
			} else if (i.getName().equals(a.getName())) {
				valid = true;
				break;
			} else if (i.getNumber() == a.getNumber()) {
				valid = true;
				break;
			} else {
				valid = false;
			}
		}
		
		if (valid == false) {
			accounts.add(a);
			System.out.println("Du hast dich erfolgreich als " + name + " mit der Nummer " + number + " angemeldet!");
		} else {
			System.out.println("Benutzername oder Nummer bereits vorhanden!");
		}
			
		 in.close();
	}
}
