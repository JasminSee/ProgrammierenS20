package ufogame;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import view.GameFrameWork;
import view.IKeyboardListener;
import view.ITickableListener;
import view.Message;

public class Game implements ITickableListener, IKeyboardListener{
	
	private ArrayList<Projectile> projectiles = new ArrayList<>();
	private ArrayList<Ufo> ufos = new ArrayList<>();
	private Ship ship;
	private int screenWidth = 900;
	private int screenHeight = 700;
	private GameFrameWork frameWork = new GameFrameWork();
	private int points = 0;
	private Message score;
	private Message win;
	private int shotCounter = 10;
	private Message maxShots;
	
	public void init() {
		//Hintergrund, Ufo und Spaceship einfuegen
		frameWork.setSize(screenWidth, screenHeight);
		frameWork.setBackground(new Background("01Vorlesung\\assets\\Background.jpg"));;
		
		ship = new Ship(screenWidth/2, 4 * screenHeight / 5, screenWidth / 10, screenWidth / 10, 
				"01Vorlesung\\assets\\Spaceship.png");
		frameWork.addGameObject(ship);
		
		Ufo ufo = new Ufo(-20, screenHeight / 5, screenWidth / 10, screenWidth / 10, 3, 
				"01Vorlesung\\assets\\Ufo.png");
		ufos.add(ufo);
		frameWork.addGameObject(ufo);
		
		//Punktestand einfuegen
		score = new Message("Score: " + points + "/5", screenWidth - 250, screenHeight - 130, 30, new Color(255,255,255));
		frameWork.addMessage(score);
		
		//Schuesse anzeigen
		maxShots = new Message("Shots: " + shotCounter + "/10", screenWidth - 250, screenHeight - 100, 20, new Color(255,255,255));
		frameWork.addMessage(maxShots);
		
		//Nachricht, wenn gewonnen
		win = new Message("", screenWidth / 3, screenHeight / 2, 80, new Color(255,255,255));
		frameWork.addMessage(win);
		
		//Anzahl der Ufos festlegen
		for(int i = 1; i < 10; i++) {
			ufos.add(new Ufo(ufos.get(i-1).getX() - 300, ufos.get(0).getY(), ufos.get(0).getWidth(), 
				ufos.get(0).getHeight(), ufos.get(0).getSpeed(), ufos.get(0).getImagePath()));
			frameWork.addGameObject(ufos.get(i));
		}
		
		frameWork.addTick(this);
		frameWork.addIKeyInput(this);
	}
	
	public void shoot() {
		Projectile projectile = new Projectile(ship.getX(), ship.getY(), 
				ship.getWidth()/2, ship.getWidth()/2, 7, "01Vorlesung\\assets\\Shot.png");
		projectiles.add(projectile);
		
		frameWork.addGameObject(projectile);
	}

	@Override
	public void tick(long elapsedTime) {
		//Bewegen der Ufos
		for(Ufo ufo : ufos) {
			ufo.move();
		}
		//Fliegt Ufo rechts aus dem Bild, fliegt naechstest links wieder rein
		if(ufos.size() > 0 && ufos.get(0).getX() > screenWidth) {
			frameWork.removeGameObject(ufos.get(0));
			ufos.remove(0);
			ufos.add(new Ufo(ufos.get(ufos.size() - 1).getX() - 300, ufos.get(0).getY(), ufos.get(0).getWidth(), 
					ufos.get(0).getHeight(), ufos.get(0).getSpeed(), ufos.get(0).getImagePath()));
			frameWork.addGameObject(ufos.get(ufos.size()-1));
		}  
		//Wenn 5 Ufos abgeschossen wurden, erscheint Nachricht, dass man gewonnen hat
		if (ufos.size() == 5) {
			frameWork.removeTick(this);
			win.setMessage("You won!");
		}

		//Bewegen des Projektiles
		for (Projectile projectile : projectiles) {
			projectile.move();
			
			//Punktestand aktualisieren
			if (checkCollision()) {
				score.setMessage("Score: " + points + "/5");
			}
		}
		//Sind alle Schuesse aufgebraucht, können keine Projektile mehr abgefeuert werden
		if (shotCounter == 0) {
			frameWork.removeIKeyboardInput(this);
		}
	}
	
	public boolean checkCollision() {
		//Entfernen des Projektiles und des Ufos, wenn kollidiert
		for (Ufo ufo : ufos) {
			for (Projectile projectile : projectiles) {
				if ((ufo.getX() < projectile.getX() + projectile.getWidth()) &&
					(ufo.getY() < projectile.getY() + projectile.getHeight()) &&
					(ufo.getX() + ufo.getWidth() > projectile.getX()) &&
					(ufo.getY() + ufo.getHeight() > projectile.getY())){
						frameWork.removeGameObject(ufo);
						frameWork.removeGameObject(projectile);
						ufos.remove(ufo);
						points += 1;
						return true;
				}
				//Entfernen des Projektiles aus FrameWork und Array, wenn nicht kollidiert und außerhalb des Bildschrimes
				if (projectile.getY() > screenHeight) {
					frameWork.removeGameObject(projectile);
					projectiles.remove(projectile);
					return false;
				}
			}
		}
		return false;
	}

	@Override
	public int[] getKeys() {
		int [] keys = {KeyEvent.VK_SPACE};
		return keys;
	}

	//Wird ein Projektil abgeschossen, wird es vom Counter abgezogen und Message mit den restlichen Schuessen wird aktualisiert
	@Override
	public void keyDown(int key) {
		shoot();
		shotCounter -= 1;
		maxShots.setMessage("Shots: " + shotCounter + "/10");
	}
}