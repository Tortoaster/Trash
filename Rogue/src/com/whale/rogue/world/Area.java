package com.whale.rogue.world;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.whale.rogue.life.Chest;
import com.whale.rogue.life.Door;
import com.whale.rogue.life.LockedDoor;
import com.whale.rogue.life.Torch;

public class Area {
	
	private final int x, y, width, height;
	
	private List<Area> doors = new ArrayList<Area>();
	
	private Random random = new Random();
	
	private final World world;
	
	public Area(int x, int y, int width, int height, World world) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.world = world;
	}
	
	public void appendDoors(List<Area> areas) {
		for(Area a: areas) {
			if(a.getX() >= x - 1 && a.getX() <= x + width && a.getY() >= y - 1 && a.getY() <= y + height) doors.add(a);
		}
	}
	
	public void decorate(Tile[][] map, int variation, boolean special) {
		List<Integer> choices = new ArrayList<Integer>();
		for(int i = 0; i < decorations.length; i++) {
			if(decorations[i].isQualified(variation, doors.size(), special)) {
				choices.add(i);
			}
		}
		if(choices.isEmpty() && special == true) {
			special = false;
			for(int i = 0; i < decorations.length; i++) {
				if(decorations[i].isQualified(variation, doors.size(), special)) {
					choices.add(i);
				}
			}
		}
		switch(choices.size() > 0 ? choices.get((int) (random.nextDouble() * choices.size())) : 100) {
		case 0: // Normal
			for(Area a: doors) world.game.life.add(new Door(a.getX(), a.getY(), world.game.life));
			break;
		case 1: // Torchlit
			world.game.life.add(new Torch(x + 1, y + 1, world.game.life));
			world.game.life.add(new Torch(x + width - 2, y + 1, world.game.life));
			world.game.life.add(new Torch(x + 1, y + height - 2, world.game.life));
			world.game.life.add(new Torch(x + width - 2, y + height - 2, world.game.life));
			for(Area a: doors) world.game.life.add(new Door(a.getX(), a.getY(), world.game.life));
			break;
		case 2: // Broken floor
			for(int i = 0; i < (width - 2) * (height - 2) / 2; i++) {
				int col = x + (int) (Math.random() * (width - 2)) + 1;
				int row = y + (int) (Math.random() * (height - 2)) + 1;
				map[col][row] = new Void(col, row, world);
			}
			for(Area a: doors) world.game.life.add(new Door(a.getX(), a.getY(), world.game.life));
			break;
		case 3: // Carpeted
			for(int y = this.y + 1; y < this.y + height - 1; y++) for(int x = this.x + 1; x < this.x + width - 1; x++) {
				map[x][y] = new Carpet(x, y, world);
			}
			for(Area a: doors) world.game.life.add(new Door(a.getX(), a.getY(), world.game.life));
			break;
		case 4: // Secret chamber
			world.game.life.add(new Chest(x + width / 2, y + height / 2, world.game.life));
			for(Area a: doors) world.game.life.add(new LockedDoor(a.getX(), a.getY(), world.game.life));
			break;
		default:
			//for(Area a: doors) world.game.life.add(new Door(a.getX(), a.getY(), world.game.life));
		}
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
	
	private static final Decoration[] decorations = new Decoration[] {
		new Decoration(new int[] {Decoration.NORMAL}, new int[] {1, 2, 3, 4}, false), // Normal
		new Decoration(new int[] {Decoration.NORMAL}, new int[] {1, 2, 3, 4}, false), // Torchlit
		new Decoration(new int[] {Decoration.NORMAL}, new int[] {1, 2, 3, 4}, false), // Broken floor
		new Decoration(new int[] {Decoration.NORMAL}, new int[] {1, 2, 3, 4}, false), // Carpeted
		new Decoration(new int[] {Decoration.NORMAL}, new int[] {1}, true), // Secret chamber
	};
}
