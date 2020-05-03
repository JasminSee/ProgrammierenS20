package datastructures;

import java.util.ArrayList;
import java.util.Scanner;

public class AverageMain {

	public static void main(String[] args) {
		
//		Szenario: Ein Benutzer soll solange neue double Werte eingeben k�nnen, bis er �quit�eingibt. 
//		Aus diesen Werten soll anschlie�end der Mittelwert berechnet und ausgegeben werden.
//		�berlegt euch welche Datenstruktur die performanteste f�r den Fall ist und erstellt das entsprechende Programm.
		
		ArrayList<Double> values = new ArrayList<>();
		double a = 0;
		
		Scanner in = new Scanner(System.in);
		
		System.out.println("Geben Sie Werte ein und beenden Sie mit 'quit': ");
		
		while (in.hasNextDouble()) {
			values.add(in.nextDouble());
			if ((in.next()).equals("quit")) {
				in.close();
			}
		} 
		
		in.close();
		
		for (double i : values) {
			a += i;
		}
		
		System.out.println("Mittelwert: " + (a/values.size()));
	}
}
