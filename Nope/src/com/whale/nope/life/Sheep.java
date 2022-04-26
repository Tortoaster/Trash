package com.whale.nope.life;

import java.awt.Color;
import java.awt.Graphics;
import com.whale.nope.main.Game;
import com.whale.nope.world.Tile;

public class Sheep extends Mammal {
	
	public Sheep(int x, int y, Game game) {
		super(x, y, Tile.SIZE, Tile.SIZE, 4, game);
	}
	
	public void update() {
		super.update();
	}
	
	public void draw(Graphics g) {
		g.setColor(new Color(250, 250, 240));
		g.fillRect(x, y, width, height);
	}
}
