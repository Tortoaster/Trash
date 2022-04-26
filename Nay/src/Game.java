import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseWheelEvent;
import java.util.Random;

public class Game extends State {
	
	private World world = new World(250, 250);
	
	private Player player;
	
	private Random r = new Random();
	
	public Game(Class c) {
		this.c = c;
		while (player == null) {
			int x = r.nextInt(world.map.length);
			int y = r.nextInt(world.map[0].length);
			if (!world.map[x][y].isBlocked()) player = new Player(x, y, world);
		}
	}
	
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_W: if (player.y > 0) if (world.map[player.x][player.y - 1].isBlocked()) world.map[player.x][player.y - 1].onTouch(player); else player.y--;
		break;
		case KeyEvent.VK_A: if (player.x > 0) if (world.map[player.x - 1][player.y].isBlocked()) world.map[player.x - 1][player.y].onTouch(player); else player.x--;
		break;
		case KeyEvent.VK_S: if (player.y < world.map[0].length - 1) if (world.map[player.x][player.y + 1].isBlocked()) world.map[player.x][player.y + 1].onTouch(player); else player.y++;
		break;
		case KeyEvent.VK_D: if (player.x < world.map.length - 1) if (world.map[player.x + 1][player.y].isBlocked()) world.map[player.x + 1][player.y].onTouch(player); else player.x++;
		}
	}
	
	public void mouseWheelMoved(MouseWheelEvent e) {
		Tile.SIZE = Math.max(16, Math.min(128, Tile.SIZE - e.getWheelRotation() * 8));
	}
	
	public void update() {
		world.update(player.x, player.y);
		player.update();
	}
	
	public void draw(Graphics g) {
		g.fillRect(0, 0, Class.WIDTH, Class.HEIGHT);
		g.translate(Math.max(-world.map.length * Tile.SIZE + Class.WIDTH, Math.min(0, -player.x * Tile.SIZE + Class.WIDTH / 2 - Tile.SIZE / 2)), Math.max(-world.map[0].length * Tile.SIZE + Class.HEIGHT, Math.min(0, -player.y * Tile.SIZE + Class.HEIGHT / 2 - Tile.SIZE / 2)));
		world.draw(g, player);
	}
}