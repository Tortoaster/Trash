package life;

import java.awt.Graphics;

public abstract class Creature {
	
	protected int x;
	protected int y;
	protected int xV;
	protected int yV;
	protected int AI = 0;
	
	protected Activity activity;
	
	public Creature(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void update() {
		
	}
	
	public void draw(Graphics g) {
		
	}
	
	public void setXV(int x) {
		xV = x;
	}
	
	public void setYV(int y) {
		yV = y;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setActivity(Activity a) {
		activity = a;
	}
	
	public Activity getActivity() {
		return activity;
	}
	
	public void switchAI() {
		if (AI == 0) AI = 1; else AI = 0;
	}
}
