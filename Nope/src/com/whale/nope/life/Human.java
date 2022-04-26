package com.whale.nope.life;

import java.awt.Color;
import java.awt.Graphics;
import com.whale.nope.main.Game;
import com.whale.nope.world.Tile;

public class Human extends Creature {
	
	public Human(int x, int y, Game game) {
		super(x, y, Tile.SIZE, Tile.SIZE, 8, game);
	}
	
	public void update() {
		super.update();
	}
	
	public void draw(Graphics g) {
		g.setColor(new Color(200, 150, 40));
		g.fillRect(x, y, width, height);
	}
}
