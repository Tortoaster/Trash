package com.whale.stoff.main;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.Random;
import com.whale.stoff.effect.Effect;
import com.whale.stoff.entity.Entity;
import com.whale.stoff.entity.Projectile;
import com.whale.stoff.entity.Torch;

public class Game extends State {
	
	private Effect effects = new Effect(this);
	
	private Entity entities = new Entity(this);
	
	private Random random = new Random();
	
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_SPACE:
			entities.add(new Torch(random.nextInt(1000), random.nextInt(800), entities));
		}
	}
	
	public void mouseReleased(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1) entities.add(new Projectile(500, 400, 4.2, Math.toDegrees(Math.atan2(e.getY() - 400, e.getX() - 500)), Projectile.Type.FIREBALL, entities)); else if (e.getButton() == MouseEvent.BUTTON2) entities.add(new Projectile(500, 400, 3.2, Math.toDegrees(Math.atan2(e.getY() - 400, e.getX() - 500)), Projectile.Type.BUTTERFLY, entities)); else if (e.getButton() == MouseEvent.BUTTON3) entities.add(new Projectile(500, 400, 2.4, Math.toDegrees(Math.atan2(e.getY() - 400, e.getX() - 500)), Projectile.Type.ICEBALL, entities));
	}
	
	public void update() {
		effects.update();
		entities.update();
	}
	
	public void draw(Graphics g) {
		effects.draw(g);
		entities.update();
	}
	
	public Effect getEffects() {
		return effects;
	}
	
	public Entity getEntities() {
		return entities;
	}
}
