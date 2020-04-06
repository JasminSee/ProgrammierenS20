package ufogame;

import view.IGameObject;

public class Ufo implements IGameObject{

	private int x;
	private int y;
	private int width;
	private int height;
	private int speed;
	private String sprite;
	private boolean isCollided;
	
	public Ufo(int x, int y, int width, int height, int speed, String sprite) {
		super();
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.speed = speed;
		this.sprite = sprite;
	}

	public void move() {
		x = x + speed;
	}
	
	public boolean isCollided() {
		return isCollided;
	}

	public void setCollided(boolean isCollided) {
		this.isCollided = isCollided;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public int getSpeed() {
		return speed;
	}

	public String getImagePath() {
		return sprite;
	}	
}