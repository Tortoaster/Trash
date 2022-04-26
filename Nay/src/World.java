import java.awt.Graphics;
import java.util.Random;

public class World {
	
	public Tile[][] map;
	
	private Random r = new Random();
	
	public World(int width, int height) {
		map = new Tile[width][height];
		generate();
	}
	
	public void update(int r, int c) {
		for (int y = Math.max(0, c - Class.HEIGHT / Tile.SIZE - 1); y < Math.min(map[0].length, c + Class.HEIGHT / Tile.SIZE + 2); y++) for (int x = Math.max(0, r - Class.WIDTH / Tile.SIZE - 1); x < Math.min(map.length, r + Class.WIDTH / Tile.SIZE + 2); x++) map[x][y].update();
	}
	
	public void generate() {
		int[][] height = new int[map.length][map[0].length];
		for (int y = 0; y < map[0].length; y++) for (int x = 0; x < map.length; x++) height[x][y] = r.nextInt(r.nextInt(200) + 1) + 1;
		int[][] average = new int[map.length][map[0].length];
		for (int i = 0; i < 15; i++) {
			for (int y = 0; y < map[0].length; y++) for (int x = 0; x < map.length; x++) {
				int value = 0;
				int neighbours = 8;
				for (int y2 = y - 1; y2 <= y + 1; y2++) for (int x2 = x - 1; x2 <= x + 1; x2++) if (x2 >= 0 && x2 < map.length && y2 >= 0 && y2 < map[0].length) {
					if (x2 != x || y2 != y) value += height[x2][y2];
				} else neighbours--;
				average[x][y] = value / neighbours;
			}
			for (int y = 0; y < map[0].length; y++) for (int x = 0; x < map.length; x++) height[x][y] = average[x][y];
		}
		for (int y = 0; y < map[0].length; y++) for (int x = 0; x < map.length; x++) if (height[x][y] < 45) map[x][y] = new Water(); else if (height[x][y] < 47) map[x][y] = new Sand(); else if (height[x][y] > 50) {
			if (r.nextInt(10) == 0) map[x][y] = new Tree(); else if (r.nextInt(100) == 0) map[x][y] = new Spikes(); else if (r.nextInt(1000) == 0) map[x][y] = new Altar(); else map[x][y] = new Grass();
		} else map[x][y] = new Grass();
		loop: for (int i = 0; i < Math.sqrt(map.length * map[0].length); i++) {
			int r = this.r.nextInt(map.length);
			int c = this.r.nextInt(map[0].length);
			int width = this.r.nextInt(6) + 5;
			int length = this.r.nextInt(6) + 5;
			for (int y = c; y <= c + length; y++) for (int x = r; x <= r + width; x++) if (x < 0 || x >= map.length || y < 0 || y >= map[0].length || map[x][y].isBlocked() && !(map[x][y] instanceof Wall || map[x][y] instanceof Tree)) continue loop;
			boolean door = false;
			int walls = 0;
			for (int y = c; y < c + length; y++) for (int x = r; x < r + width; x++) if ((x == r || x == r + width - 1 || y == c || y == c + length - 1) && !(map[x][y] instanceof Floor)) if (door || x == r && y == c || x == r + width - 1 && y == c || x == r && y == c + length - 1 || x == r + width - 1 && y == c + length - 1 || this.r.nextInt(2 * (width + length)) > walls) {
				map[x][y] = new Wall();
				walls++;
			} else {
				map[x][y] = new Door();
				door = true;
			} else map[x][y] = new Floor();
		}
	}
	
	public void draw(Graphics g, Player p) {
		for (int y = Math.max(0, p.y - Class.HEIGHT / Tile.SIZE - 1); y < Math.min(map[0].length, p.y + Class.HEIGHT / Tile.SIZE + 2); y++) for (int x = Math.max(0, p.x - Class.WIDTH / Tile.SIZE - 1); x < Math.min(map.length, p.x + Class.WIDTH / Tile.SIZE + 2); x++) {
			map[x][y].draw(g, x, y);
			if (p.y == y) p.draw(g);
		}
	}
}
