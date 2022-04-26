package com.rook.state;

import java.awt.Graphics;
import java.awt.event.KeyEvent;

import com.rook.life.Creature;
import com.rook.world.Tile;
import com.rook.world.World;

public class Game extends State {
	
	public static final int SCALE = 4;
	
	private World world;
	
	private Creature player;
	
	public Game() {
		world = new World(Tile.SIZE * 16, 50, 28, 3, 4, 13123034);
		player = world.getPlayer();
	}
	
	public void keyPressed(KeyEvent e) {
		switch(e.getKeyCode()) {
		case KeyEvent.VK_W:
			player.up();
			break;
		case KeyEvent.VK_A:
			player.left();
			break;
		case KeyEvent.VK_S:
			player.down();
			break;
		case KeyEvent.VK_D:
			player.right();
			break;
		}
	}
	
	public void keyReleased(KeyEvent e) {
		int code = e.getKeyCode();
		if(code == KeyEvent.VK_A || code == KeyEvent.VK_D) {
			player.stopX();
		} else if(code == KeyEvent.VK_W || code == KeyEvent.VK_S) {
			player.stopY();
		}
	}
	
	public void update() {
		world.update();
	}
	
	public void draw(Graphics g) {
		world.draw(g);
	}
}
