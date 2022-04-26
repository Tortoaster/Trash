import java.awt.Graphics;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class World {
	
	public static final int TILESIZE = 16;
	
	private int width, height;
	
	private Tile[][] terrain;
	private List<Entity> entities = new ArrayList<>();
	
	private Game game;
	
	public World(int width, int height, Game game) {
		this.width = width;
		this.height = height;
		this.game = game;
		terrain = generateMap(width, height);
	}
	
	public World(String path, Game game) {
		this.game = game;
		terrain = openMapFromFile(path);
		width = terrain.length;
		height = terrain[0].length;
		spawnWave(1);
	}
	
	public void update() {
		for(int i = 0; i < entities.size(); i++) {
			Entity e = entities.get(i);
			if(e != null) {
				e.update();
			}
		}
	}
	
	public void draw(Graphics g) {
		for(int y = 0; y < height; y++) {
			for(int x = 0; x < width; x++) {
				switch(terrain[x][y]) {
					case GRASS:
						g.setColor(Colors.GRASS);
						g.fillRect(x * TILESIZE, y * TILESIZE, TILESIZE, TILESIZE);
						break;
					case PATH:
						g.setColor(Colors.PATH);
						g.fillRect(x * TILESIZE, y * TILESIZE, TILESIZE, TILESIZE);
						break;
					case WATER:
						g.setColor(Colors.WATER);
						g.fillRect(x * TILESIZE, y * TILESIZE, TILESIZE, TILESIZE);
						break;
					default:
				}
			}
		}
		// Sort entities by y.
		for(int i = 0; i < entities.size(); i++) {
			Entity e = entities.get(i);
			if(e != null) {
				e.draw(g);
			}
		}
	}
	
	public void spawnWave(int level) {
		entities.add(new Licker(2, 9, this));
	}
	
	private Tile[][] generateMap(int width, int height) {
		Tile[][] map = new Tile[width][height];
		for(int y = 0; y < height; y++) {
			for(int x = 0; x < width; x++) {
				if(x == 0 || y == 0 || x == width - 1 || y == height - 1) {
					map[x][y] = Tile.WATER;
				} else {
					map[x][y] = Tile.GRASS;
				}
			}
		}
		return map;
	}
	
	private Tile[][] openMapFromFile(String path) {
		String content = readFile(path);
		
		String[] rows = content.split("\n");
		
		int width = rows[0].split(" ").length;
		int height = rows.length;
		
		Tile[][] map = new Tile[width][height];
		
		for(int y = 0; y < rows.length; y++) {
			String[] values = rows[y].split(" ");
			for(int x = 0; x < values.length; x++) {
				switch(values[x].charAt(0)) {
					case 'G':
						map[x][y] = Tile.GRASS;
						break;
					case 'P':
						map[x][y] = Tile.PATH;
						break;
					case 'W':
						map[x][y] = Tile.WATER;
						break;
					default: map[x][y] = Tile.GRASS;
				}
			}
		}
		
		return map;
	}
	
	public String readFile(String path) {
		String content = "";
		try {
			content = new String(Files.readAllBytes(Paths.get(path)));
		} catch(IOException e) {
			e.printStackTrace();
		}
		return content;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public List<Entity> getEntities() {
		return entities;
	}
	
	public Game getGame() {
		return game;
	}
}
