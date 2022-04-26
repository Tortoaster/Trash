package com.whale.nee.life;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import com.whale.nee.main.Game;

public class Life {
	
	private Game game;
	
	private List<Creature> life = new ArrayList<Creature>();
	
	public Life(Game game) {
		this.game = game;
	}
	
	public void update() {
		for (int i = 0; i < life.size(); i++) if (life.get(i) != null) life.get(i).update();
	}
	
	public void draw(Graphics g) {
		for (int i = 0; i < life.size(); i++) if (life.get(i) != null) life.get(i).draw(g);
	}
	
	public void add(Creature c) {
		life.add(c);
	}
	
	public void remove(Creature c) {
		life.remove(c);
	}
}
