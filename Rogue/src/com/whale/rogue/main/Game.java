package com.whale.rogue.main;

import java.awt.Graphics;
import java.awt.event.MouseWheelEvent;

import com.whale.rogue.effects.Effects;
import com.whale.rogue.life.Life;
import com.whale.rogue.life.Player;
import com.whale.rogue.mech.Camera;
import com.whale.rogue.world.Tile;
import com.whale.rogue.world.World;

public class Game extends State {
	
	public final Effects effects;
	public final Life life;
	public final World world;
	
	private Player player;
	private Camera camera = new Camera();
	
	public Game() {
		effects = new Effects(this);
		life = new Life(this);
		world = new World(40, 30, 100, 5, 9, 3, this);
		player = new Player(world.getWidth() / 2 + 1, world.getHeight() / 2, life);
		life.add(player);
	}
	
	public void mouseWheelMoved(MouseWheelEvent e) {
		Tile.SIZE = Math.max(10, Math.min(80, Tile.SIZE - (int) (e.getWheelRotation() * Math.max(Tile.SIZE / 10.0, 1))));
	}
	
	public void update() {
		camera.update(player.getX() + 0.5, player.getY() + 0.5);
		for(int y = camera.getY(); y < camera.getY() + camera.getHeight(); y++) {
			world.update(camera.getX(), y, camera.getWidth(), 1);
			life.update(camera.getX(), y, camera.getWidth(), 1);
		}
		effects.update(camera.getX(), camera.getY(), camera.getWidth(), camera.getHeight());
	}
	
	public void draw(Graphics g) {
		g.translate((int) ((player.getX() + 0.5) * -Tile.SIZE + Window.WIDTH / 2), (int) ((player.getY() + 0.5) * -Tile.SIZE + Window.HEIGHT / 2));
		for(int y = camera.getY(); y < camera.getY() + camera.getHeight(); y++) {
			world.draw(camera.getX(), y, camera.getWidth(), 1, g);
			life.draw(camera.getX(), y, camera.getWidth(), 1, g);
		}
		effects.draw(camera.getX(), camera.getY(), camera.getWidth(), camera.getHeight(), g);
	}
}
