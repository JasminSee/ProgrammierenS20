package datastructures;

import java.util.ArrayList;
import java.util.Collections;

public class HighScoreMain {
	public static void main(String[] args) {
		
//		Schreibt eine Klasse Highscore mit dem Spielernamen und der Punktzahl als Instanzvariablen.
//		Erstellt eine ArrayList mit 5 Einträgen.
//		Zusätzlich soll die Klasse Highscore nun auch das Interface Comparable implementieren.
//		Sortiert nun die ArrayList und gebt die Bestenliste aus.
		
		ArrayList<HighScore> highscore = new ArrayList<HighScore>();
		highscore.add(new HighScore("Pika", 42));
		highscore.add(new HighScore("Chuck", 10000));
		highscore.add(new HighScore("Vegeta", 9001));
		highscore.add(new HighScore("Edmund W.", 42));
		highscore.add(new HighScore("Covid", 1337));
		highscore.add(new HighScore("Covid", 19));
		
		Collections.sort(highscore);
		
		for (HighScore score : highscore) {
			System.out.println(String.format("%20s:\t%d", score.getName(), score.getPoints()));
		}
	}
}
