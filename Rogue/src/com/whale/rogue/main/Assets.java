package com.whale.rogue.main;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.whale.rogue.life.Chest;
import com.whale.rogue.life.Door;
import com.whale.rogue.life.LockedDoor;
import com.whale.rogue.life.Player;
import com.whale.rogue.life.Torch;
import com.whale.rogue.mech.Animation;
import com.whale.rogue.mech.Animator;

public class Assets {
	
	public static BufferedImage[] TERRAIN;
	public static Animator[] ENTITIES = new Animator[4];
	public static Animator[] CREATURES = new Animator[1];
	
	public static void load() {
		BufferedImage[] entities = null;
		BufferedImage[] creatures = null;
		try {
			BufferedImage tilesetTerrain = ImageIO.read(new File("res/tileset_terrain.png"));
			TERRAIN = cut(tilesetTerrain);
			entities = cut(ImageIO.read(new File("res/tileset_entities.png")));
			creatures = cut(ImageIO.read(new File("res/tileset_creatures.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		ENTITIES[Door.ID] = new Animator(new Animation[] {new Animation(animate(entities, 0, 1), 0.1)});
		ENTITIES[Torch.ID] = new Animator(new Animation[] {new Animation(animate(entities, 12, 12), 1)});
		ENTITIES[Chest.ID] = new Animator(new Animation[] {new Animation(animate(entities, 13, 13), 1)});
		ENTITIES[LockedDoor.ID] = new Animator(new Animation[] {new Animation(animate(entities, 4, 5), 0.1)});
		CREATURES[Player.ID] = new Animator(new Animation[] {new Animation(animate(creatures, 0, 1), 1), new Animation(animate(creatures, 12, 17), 2), new Animation(animate(creatures, 24, 31), 3)});
	}
	
	private static BufferedImage[] cut(BufferedImage image) {
		int size = image.getWidth();
		BufferedImage[] parsed = new BufferedImage[size];
		size = (int) Math.sqrt(size);
		for(int y = 0; y < size; y++) for(int x = 0; x < size; x++) {
			parsed[y * size + x] = image.getSubimage(x * size, y * size, size, size);
		}
		return parsed;
	}
	
	private static BufferedImage[] animate(BufferedImage[] tileset, int first, int last) {
		BufferedImage[] frames = new BufferedImage[last - first + 1];
		for(int i = first; i <= last; i++) {
			frames[i - first] = tileset[i];
		}
		return frames;
	}
}
