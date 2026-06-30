package com.whale.rogue.world;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.whale.rogue.main.Game;

public class World {
	
	private final int width, height, predef = 2, wall = 0, placeholder = 1, empty = 2;
	private int min, max;
	
	private Random random = new Random();
	
	private Tile[][] map;
	
	public final Game game;
	
	public World(int width, int height, int tries, int min, int max, int special, Game game) {
		if(width % 2 == 0) width++;
		if(height % 2 == 0) height++;
		this.width = width;
		this.height = height;
		this.game = game;
		this.min = min;
		this.max = max;
		map = generate(width, height, tries, Decoration.NORMAL, special);
	}
	
	public void update(int row, int col, int width, int height) {
		for(int y = col; y < col + height; y++) for (int x = row; x < row + width; x++) {
			if(x >= 0 && y >= 0 && x < getWidth() && y < getHeight()) {
				map[x][y].update();
			}
		}
	}
	
	public void draw(int row, int col, int width, int height, Graphics g) {
		for(int y = col; y < col + height; y++) for (int x = row; x < row + width; x++) {
			if(x >= 0 && y >= 0 && x < getWidth() && y < getHeight()) {
				map[x][y].draw(g);
			}
		}
	}
	
	public Tile[][] generate(int width, int height, int tries, int variation, int special) {
		Tile[][] map = new Tile[width][height];
		int[][] tiles = new int[width][height];
		List<Area> rooms = makeRooms(tiles, tries);
		carveTunnels(tiles);
		List<Area> doors = placeDoors(tiles);
		removeEnds(tiles);
		removeExcess(tiles);
		placeTiles(tiles, map);
		cleanUp(tiles, doors);
		int amount = 0;
		for(Area a: rooms) {
			a.appendDoors(doors);
			if(amount < special && random.nextInt(5) == 0) {
				a.decorate(map, variation, true);
				amount++;
			} else {
				a.decorate(map, variation, false);
			}
		}
		return map;
	}
	
	public void placeTiles(int[][] tiles, Tile[][] map) {
		for(int y = 0; y < map[0].length; y++) for(int x = 0; x < map.length; x++) {
			if(tiles[x][y] > predef) {
				map[x][y] = new Floor(x, y, this);
			} else {
				switch(tiles[x][y]) {
				case empty: map[x][y] = new Void(x, y, this);
					break;
				case wall: map[x][y] = new Wall(x, y, this);
					break;
				}
			}
		}
	}
	
	public List<Area> makeRooms(int[][] tiles, int tries) {
		List<Area> rooms = new ArrayList<Area>();
		for(int i = 0; i < tries; i++) {
			int rand = randomID();
			int width = oddNumber(min, max);
			int height = oddNumber(min, max);
			int col = oddNumber(0, tiles.length - width - 1);
			int row = oddNumber(0, tiles[0].length - height - 1);
			boolean allowed = true;
			for(int y = row; y < row + height; y++) for(int x = col; x < col + width; x++) {
				if(tiles[x][y] > predef) {
					allowed = false;
					break;
				}
			}
			if(allowed) {
				for(int y = row; y < row + height; y++) for(int x = col; x < col + width; x++) {
					tiles[x][y] = rand;
				}
				rooms.add(new Area(col, row, width, height, this));
			}
		}
		return rooms;
	}
	
	public void carveTunnels(int[][] tiles) {
		boolean[][] full = new boolean[tiles.length][tiles[0].length];
		boolean busy = true;
		while(busy) {
			int row = 0;
			int col = 0;
			boolean searching = true;
			while(searching) {
				row = oddNumber(0, tiles.length - 1);
				col = oddNumber(0, tiles[0].length - 1);
				if(tiles[row][col] <= predef) searching = false;
			}
			int rand = randomID();
			tiles[row][col] = rand;
			boolean carving = true;
			while(carving) {
				List<Integer> walls = new ArrayList<Integer>();
				if(col > 2 && tiles[row][col - 2] <= predef) walls.add(0);
				if(row < tiles.length - 2 && tiles[row + 2][col] <= predef) walls.add(1);
				if(col < tiles[0].length - 2 && tiles[row][col + 2] <= predef) walls.add(2);
				if(row > 2 && tiles[row - 2][col] <= predef) walls.add(3);
				if(walls.size() > 0) {
					int dir = walls.get((int) (Math.random() * walls.size()));
					switch(dir) {
					case 0:
						tiles[row][col - 1] = rand;
						col -= 2;
						tiles[row][col] = rand;
						break;
					case 1:
						tiles[row + 1][col] = rand;
						row += 2;
						tiles[row][col] = rand;
						break;
					case 2:
						tiles[row][col + 1] = rand;
						col += 2;
						tiles[row][col] = rand;
						break;
					case 3:
						tiles[row - 1][col] = rand;
						row -= 2;
						tiles[row][col] = rand;
					}
				} else {
					full[row][col] = true;
					List<Integer> dirs = new ArrayList<Integer>();
					if(col > 2 && tiles[row][col - 1] > predef && !full[row][col - 2]) dirs.add(0);
					if(row < tiles.length - 2 && tiles[row + 1][col] > predef && !full[row + 2][col]) dirs.add(1);
					if(col < tiles[0].length - 2 && tiles[row][col + 1] > predef && !full[row][col + 2]) dirs.add(2);
					if(row > 2 && tiles[row - 1][col] > predef && !full[row - 2][col]) dirs.add(3);
					if(dirs.size() > 0) {
						int dir = dirs.get((int) (Math.random() * dirs.size()));
						switch(dir) {
						case 0: col -= 2;
							break;
						case 1: row += 2;
							break;
						case 2: col += 2;
							break;
						case 3: row -= 2;
						}
					} else {
						carving = false;
					}
				}
			}
			boolean done = true;
			for(int y = 1; y < tiles[0].length; y += 2) for(int x = 1; x < tiles.length; x += 2) {
				if(tiles[x][y] <= predef) {
					done = false;
					break;
				}
			}
			if(done) busy = false;
		}
	}
	
	public void placeHolders(int[][] tiles) {
		for(int y = 0; y < tiles[0].length; y++) for(int x = 0; x < tiles.length; x++) {
			if(tiles[x][y] == wall) {
				List<Integer> areas = new ArrayList<Integer>();
				for(int a = 0; a < 360; a += 90) {
					int col = x + (int) Math.cos(Math.toRadians(a));
					int row = y + (int) Math.sin(Math.toRadians(a));
					if(col >= 0 && row >= 0 && col < tiles.length && row < tiles[0].length) {
						int area = tiles[col][row];
						if(area > predef) {
							if(!areas.contains(area)) {
								areas.add(area);
							}
						}
					}
				}
				if(areas.size() > 1) {
					tiles[x][y] = placeholder;
				}
			}
		}
	}
	
	public List<Area> placeDoors(int[][] tiles) {
		placeHolders(tiles);
		List<Area> result = new ArrayList<Area>();
		boolean[][] connected = new boolean[tiles.length][tiles[0].length];
		connected[oddNumber(0, tiles.length - 1)][oddNumber(0, tiles[0].length - 1)] = true;
		boolean busy = true;
		while(busy) {
			boolean changed = false;
			for(int y = 0; y < tiles[0].length; y++) for(int x = 0; x < tiles.length; x++) {
				if(connected[x][y]) {
					for(int a = 0; a < 360; a += 90) {
						int col = x + (int) Math.cos(Math.toRadians(a));
						int row = y + (int) Math.sin(Math.toRadians(a));
						if(col >= 0 && row >= 0 && col < tiles.length && row < tiles[0].length) {
							if(!connected[col][row]) {
								if(tiles[col][row] > predef) {
									connected[col][row] = true;
									changed = true;
								}
							}
						}
					}
				}
			}
			if(!changed) {
				List<Area> doors = new ArrayList<Area>();
				for(int y = 0; y < tiles[0].length; y++) for(int x = 0; x < tiles.length; x++) {
					if(connected[x][y]) {
						for(int a = 0; a < 360; a += 90) {
							int col = x + (int) Math.cos(Math.toRadians(a));
							int row = y + (int) Math.sin(Math.toRadians(a));
							if(col >= 0 && row >= 0 && col < tiles.length && row < tiles[0].length) {
								if(tiles[col][row] == placeholder) {
									doors.add(new Area(col, row, 1, 1, this));
								}
							}
						}
					}
				}
				if(doors.size() > 0) {
					Area area = doors.get((int) (Math.random() * doors.size()));
					for(int a = 0; a < 360; a += 90) {
						int hor = (int) Math.cos(Math.toRadians(a));
						int vert = (int) Math.sin(Math.toRadians(a));
						tiles[area.getX()][area.getY()] = randomID();
						Area door = new Area(area.getX(), area.getY(), 1, 1, this);
						boolean valid = true;
						for(Area r: result) {
							if(r.getX() == door.getX() && r.getY() == door.getY()) {
								valid = false;
								break;
							}
						}
						if(valid) result.add(door);
						for(int i = 1; i <= max; i++) {
							int col = area.getX() + hor * i;
							int row = area.getY() + vert * i;
							if(col >= 0 && row >= 0 && col < tiles.length && row < tiles[0].length) {
								if(tiles[col][row] == placeholder) {
									tiles[col][row] = wall;
								} else if(tiles[col][row] > predef) break;
							}
						}
					}
				} else busy = false;
			}
			boolean done = true;
			for(int y = 0; y < tiles[0].length; y++) for(int x = 0; x < tiles.length; x++) {
				if((tiles[x][y] > predef) && !connected[x][y]) done = false;
			}
			if(done) {
				for(int y = 0; y < tiles[0].length; y++) for(int x = 0; x < tiles.length; x++) {
					if(tiles[x][y] == placeholder) tiles[x][y] = wall;
				}
			}
			if(done) busy = false;
		}
		return result;
	}
	
	public void removeEnds(int[][] tiles) {
		boolean busy = true;
		while(busy) {
			boolean changed = false;
			for(int y = 0; y < tiles[0].length; y++) for(int x = 0; x < tiles.length; x++) {
				if(tiles[x][y] > predef) {
					int num = 0;
					for(int a = 0; a < 360; a += 90) {
						int col = x + (int) Math.cos(Math.toRadians(a));
						int row = y + (int) Math.sin(Math.toRadians(a));
						if(col >= 0 && row >= 0 && col < tiles.length && row < tiles[0].length) {
							if(tiles[col][row] > predef) {
								num++;
							}
						}
					}
					if(num <= 1) {
						tiles[x][y] = wall;
						changed = true;
					}
				}
			}
			if(!changed) busy = false;
		}
	}
	
	public void removeExcess(int[][] tiles) {
		for(int row = 0; row < tiles[0].length; row++) for(int col = 0; col < tiles.length; col++) {
			if(tiles[col][row] == wall) {
				int num = 0;
				for(int y = row - 1; y <= row + 1; y++) for(int x = col - 1; x <= col + 1; x++) {
					if(x == -1 || y == -1 || x == tiles.length || y == tiles[0].length || tiles[x][y] == wall || tiles[x][y] == empty || (x == col && y == row)) {
						num++;
					}
				}
				if(num == 9) tiles[col][row] = empty;
			}
		}
	}
	
	public void cleanUp(int[][] tiles, List<Area> doors) {
		for(int i = 0; i < doors.size(); i++) {
			Area door = doors.get(i);
			if(tiles[door.getX()][door.getY()] <= predef) {
				doors.remove(door);
			}
		}
	}
	
	public int oddNumber(int min, int max) {
		if (max % 2 == 0) max--;
		if (min % 2 == 0) min++;
		return min + 2 * (int)(Math.random() * ((max - min) / 2 + 1));
	}
	
	public int randomID() {
		return (int) (Math.random() * Integer.MAX_VALUE - predef) + predef + 1;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
}
