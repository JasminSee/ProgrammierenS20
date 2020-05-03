package datastructures;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class AverageMain {

	public static void main(String[] args) {
		
//		Szenario: Ein Benutzer soll solange neue double Werte eingeben können, bis er „quit“eingibt. 
//		Aus diesen Werten soll anschließend der Mittelwert berechnet und ausgegeben werden.
//		Überlegt euch welche Datenstruktur die performanteste für den Fall ist und erstellt das entsprechende Programm.
		
		ArrayList<Double> values = new ArrayList<Double>();
		double a = 0;
		
		Scanner in = new Scanner(System.in);
		
		System.out.println("Geben Sie Werte ein und beenden Sie mit 'quit': ");
		
		try {
			while (true) {
				if (!in.hasNextDouble() && in.next().equals("quit")) {
					in.close();
					break;
				} else {
					values.add(in.nextDouble());
				} 
			}
			for (double i : values) {
				a += i;
			}
			
			System.out.println("Mittelwert: " + (a/values.size()));
			
		} catch (InputMismatchException e) {
			System.out.println("Ungültige Eingabe");
		}
	}
}
