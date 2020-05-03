package datastructures;

import java.util.HashSet;

public class EventMain {

	public static void main(String[] args) {
		
//		Erstelle eine Klasse Event, die einen Eventnamen, einen Ort und eine Startzeit hat. 
//		Ihr könnt als Startzeit einen String nehmen oder mit Calendar*arbeiten.
//		Nun sollt ihr auch hier wieder die equals()-Methode überschreiben. 
//		Fügt Events zur Set hinzu, fügt dabei auch gleiche Events hinzu.
		
		HashSet<Event> events = new HashSet<>();
		events.add(new Event("Hurricane Festival", "Scheeßel", "19.06.2020"));
		events.add(new Event("Rock am Ring", "Nürnberg", "05.06.2020"));
		events.add(new Event("Airbeat One", "Neustadt-Glewe", "08.07.2020"));
		events.add(new Event("Parookaville", "Weeze", "17.07.2020"));
		events.add(new Event("World Club Dome", "Frankfurt", "05.06.2020"));
		events.add(new Event("Wacken Open Air", "Wacken", "30.07.2020"));
		events.add(new Event("Parookaville", "Weeze", "17.07.2020"));
		
		for (Event i : events) {
			System.out.println(i.getName() + " in " + i.getPlace() + " am " + i.getDate());
		}
	}
}
