import java.awt.Color;
import java.awt.Graphics;

public class Sand extends Tile {
	
	public void draw(Graphics g, int x, int y) {
		g.setColor(new Color(180, 180, 110));
		g.fillRect(x * Tile.SIZE, y * Tile.SIZE, Tile.SIZE, Tile.SIZE);
	}
}
