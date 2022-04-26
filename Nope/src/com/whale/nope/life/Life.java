package com.whale.nope.life;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.whale.nope.main.Game;
import com.whale.nope.main.Screen;
import com.whale.nope.world.Tile;
import com.whale.nope.world.World;

public class Life {
	
	private List<Creature> creatures = new ArrayList<Creature>();
	
	private Game game;
	
	private Random random = new Random();
	
	public Life(Game game) {
		this.game = game;
	}
	
	public void update() {
		for (int i = 0; i < creatures.size(); i++) creatures.get(i).update();
		if (creatures.size() < 20) {
			switch (random.nextInt(2)) {
			case 0: add(new Sheep(random.nextInt(World.WIDTH * Tile.SIZE), 0, game));
			break;
			case 1: add(new Bird(random.nextInt(World.WIDTH * Tile.SIZE), (random.nextInt(World.HEIGHT / 4) + World.HEIGHT / 2) * Tile.SIZE, game));
			}
		}
	}
	
	public void draw(Graphics g) {
		for (int i = 0; i < creatures.size(); i++) if (creatures.get(i).getX() >= game.getXOffset() - 10 && creatures.get(i).getX() < game.getXOffset() + Screen.WIDTH + 10 && creatures.get(i).getY() >= game.getYOffset() - 10 && creatures.get(i).getY() < game.getYOffset() + Screen.HEIGHT + 10) creatures.get(i).draw(g);
	}
	
	public void add(Creature c) {
		creatures.add(c);
	}
	
	public void remove(Creature c) {
		creatures.remove(c);
	}
}
