import java.awt.Color;
import java.awt.Graphics;

public class Grass extends Tile {
	
	public void draw(Graphics g, int x, int y) {
		g.setColor(new Color(20, 160, 90));
		g.fillRect(x * Tile.SIZE, y * Tile.SIZE, Tile.SIZE, Tile.SIZE);
	}
}
