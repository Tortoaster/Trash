package com.whale.nope.main;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.File;
import com.whale.nope.audio.Sound;
import com.whale.nope.effects.Effects;
import com.whale.nope.life.Creature;
import com.whale.nope.life.Human;
import com.whale.nope.life.Life;
import com.whale.nope.misc.Background;
import com.whale.nope.world.Dynamite;
import com.whale.nope.world.Tile;
import com.whale.nope.world.World;

public class Game extends State {
	
	private boolean left, right, jump;
	
	private final double tolerance = 30;
	private int xOffset, yOffset, x, y;
	
	private Creature player = new Human(0, 0, this);
	
	private Effects effects = new Effects(this);
	
	private Life life = new Life(this);
	
	private Background cloud = new Background(this);
	
	private World world = new World(400, 200, this);
	
	public Game() {
		xOffset = player.getX() - 960 + player.getWidth() / 2;
		yOffset = player.getY() - 540 + player.getHeight() / 2;
		Sound.playSound(new File("res/Start.wav"), 10);
	}
	
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_ESCAPE: System.exit(0);
		break;
		case KeyEvent.VK_SPACE: player.setX(xOffset + x - player.getWidth() / 2);
		player.setY(yOffset + y - player.getHeight() / 2);
		break;
		case KeyEvent.VK_W: jump = true;
		break;
		case KeyEvent.VK_A: left = true;
		break;
		case KeyEvent.VK_D: right = true;
		}
	}
	
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_W: jump = false;
		break;
		case KeyEvent.VK_A: left = false;
		break;
		case KeyEvent.VK_D: right = false;
		}
	}
	
	public void mouseDragged(MouseEvent e) {
		x = e.getX();
		y = e.getY();
	}
	
	public void mouseMoved(MouseEvent e) {
		x = e.getX();
		y = e.getY();
	}
	
	public void mouseClicked(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1) {
			world.getTile((xOffset + e.getX()) / Tile.SIZE, (yOffset + e.getY()) / Tile.SIZE).onHit((xOffset + e.getX()) / Tile.SIZE, (yOffset + e.getY()) / Tile.SIZE);
		} else if (e.getButton() == MouseEvent.BUTTON3) world.setTile((xOffset + e.getX()) / Tile.SIZE, (yOffset + e.getY()) / Tile.SIZE, new Dynamite(world));
	}
	
	public void update() {
		if (left) player.setXV(Math.max(-player.getSpeed(), player.getXV() - 1)); else if (right) player.setXV(Math.min(player.getSpeed(), player.getXV() + 1)); else if (player.getXV() < 0) player.setXV(Math.min(0, player.getXV() + 1)); else player.setXV(Math.max(0, player.getXV() - 1));
		if (jump) player.jump();
		player.update();
		xOffset += Math.max(-Math.abs((player.getX() - Screen.WIDTH / 2 + player.getWidth() / 2) - xOffset) / tolerance, Math.min(Math.abs((player.getX() - Screen.WIDTH / 2 + player.getWidth() / 2) - xOffset) / tolerance, (player.getX() - Screen.WIDTH / 2 + player.getWidth() / 2) - xOffset));
		yOffset = (int) Math.max(0, Math.min(World.HEIGHT * Tile.SIZE - Screen.HEIGHT, yOffset + Math.max(-Math.abs((player.getY() - Screen.HEIGHT / 2 + player.getHeight() / 2) - yOffset) / tolerance, Math.min(Math.abs((player.getY() - Screen.HEIGHT / 2 + player.getHeight() / 2) - yOffset) / tolerance, (player.getY() - Screen.HEIGHT / 2 + player.getHeight() / 2) - yOffset))));
		effects.update();
		life.update();
		cloud.update();
		world.update();
	}
	
	public void draw(Graphics g) {
		cloud.draw(g);
		g.translate(-getXOffset(), -getYOffset());
		world.draw(g);
		effects.draw(g);
		life.draw(g);
		player.draw(g);
	}
	
	public void setXOffset(int offset) {
		xOffset = offset;
	}
	
	public void setYOffset(int offset) {
		yOffset = offset;
	}
	
	public int getXOffset() {
		return xOffset;
	}
	
	public int getYOffset() {
		return yOffset;
	}
	
	public Creature getPlayer() {
		return player;
	}
	
	public Effects getEffects() {
		return effects;
	}
	
	public World getWorld() {
		return world;
	}
}
