import java.awt.Graphics;

public abstract class Tile {
	
	public static int SIZE = 64;
	
	private boolean blocked = false;
	
	public void update() {
		
	}
	
	public void onTouch(Player p) {
		
	}
	
	public void block() {
		blocked = true;
	}
	
	public boolean isBlocked() {
		return blocked;
	}
	
	public void draw(Graphics g, int x, int y) {
		
	}
}
