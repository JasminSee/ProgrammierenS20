package datastructures;

import java.util.HashMap;
import java.util.Set;

public class ListMain {

	public static void main(String[] args) {
		
//		Ihr habt eine Einkaufsliste, die sowohl den Artikel(String) als auch die einzukaufende Menge(String) beinhaltet.
//		Überlegt euch welche Liste keyund welche valueist.
		
		HashMap<String, String> shopping = new HashMap<>();
		shopping.put("Tomaten", "3 Stueck");
		shopping.put("Eier", "6 Stueck");
		shopping.put("Brot", "1 Packung");
		shopping.put("Milch", "3 Packungen");
		shopping.put("Nudeln", "1 Packung");
		
		Set<String> article = shopping.keySet();
		for (String name : article) {
			System.out.println(name + ": " + shopping.get(name));
		}
	}
}
