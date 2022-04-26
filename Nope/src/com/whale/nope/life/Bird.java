package com.whale.nope.life;

import java.awt.Color;
import java.awt.Graphics;

import com.whale.nope.main.Game;
import com.whale.nope.world.Tile;

public class Bird extends Avian {
	
	public Bird(int x, int y, Game game) {
		super(x, y, Tile.SIZE / 2, Tile.SIZE, 8, game);
	}
	
	public void draw(Graphics g) {
		if (flying) {
			g.setColor(new Color(200, 180, 90));
			g.fillRect(x, y + width / 2, height, width);
			g.setColor(new Color(180, 160, 70));
			g.fillRect(x + height / 4, y - width / 8 + width / 2, height / 2, width / 4);
		} else {
			g.setColor(new Color(180, 160, 70));
			g.fillRect(x - width / 2, y + height / 4, width * 2, height / 2);
			g.setColor(new Color(200, 180, 90));
			g.fillRect(x, y, width, height);
		}
	}
}
