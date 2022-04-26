import java.awt.Color;
import java.awt.Graphics;

public class Water extends Tile {
	
	public Water() {
		block();
	}
	
	public void draw(Graphics g, int x, int y) {
		g.setColor(new Color(60, 90, 180));
		g.fillRect(x * Tile.SIZE, y * Tile.SIZE, Tile.SIZE, Tile.SIZE);
	}
}
