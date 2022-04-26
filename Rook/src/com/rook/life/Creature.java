package com.rook.life;

import java.util.ArrayList;
import java.util.List;

import com.rook.Window;
import com.rook.modifier.Buff;
import com.rook.world.Tile;
import com.rook.world.World;

public abstract class Creature extends Entity {
	
	protected boolean intelligent = true, invincible, visible;
	
	protected double speed, jumpHeight;
	
	public static final int VIEW_RANGE = Tile.SIZE * 10, FOLLOW_RANGE = Tile.SIZE * 2;
	public static final int ACTION_PASSIVE = 0, ACTION_ATTACKING = 1, ACTION_FOLLOWING = 2, ACTION_FLEEING = 3;
	public static final int ANIMAL = 0, HUMANOID = 1, FLYING = 2, CRAWLER = 3;
	public static final int HUMAN = 0, SKELETON = 1;
	
	protected int jumps, maxJumps = 1, attackRange = Tile.SIZE / 2, str, acc, mag, def, dex, vit, action, type, id;
	
	protected Entity target;
	
	protected List<Creature> noticed = new ArrayList<>();
	protected List<Creature> companions = new ArrayList<>();
	protected List<Projectile> projectiles = new ArrayList<>();
	protected List<Buff> buffs = new ArrayList<>();
	
	public Creature(double x, double y, double weight, double speed, double jumpHeight, int width, int height, int str, int acc, int mag, int def, int dex, int vit, int team, int type, int id, World world) {
		super(x, y, weight, width, height, team, world);
		this.speed = speed;
		this.jumpHeight = jumpHeight;
		this.str = str;
		this.acc = str;
		this.mag = str;
		this.def = str;
		this.dex = str;
		this.vit = str;
		this.type = type;
		this.id = id;
	}
	
	public void collide(int side) {
		if(side == Tile.SIDE_TOP) {
			jumps = maxJumps;
		} else if(side == Tile.SIDE_LEFT || side == Tile.SIDE_RIGHT) {
			jump();
		}
	}
	
	public void notice() {
		List<Creature> near = world.getLife().getNearCreatures(getX(), getY(), VIEW_RANGE);
		for(int i = 0; i < near.size(); i++) {
			Creature c = near.get(i);
			if(c.isVisible() && world.canSee((int) ((x + width / 2) / Tile.SIZE), (int) ((y + width / 2) / Tile.SIZE), (int) ((c.getX() + c.getWidth() / 2) / Tile.SIZE), (int) ((c.getY() + c.getHeight() / 2) / Tile.SIZE)) && !noticed.contains(c)) {
				noticed.add(c);
			}
		}
	}
	
	public void forget(Creature c) {
		if(companions.contains(c)) companions.remove(c);
		if(noticed.contains(c)) noticed.remove(c);
		if(target == c) target = null;
	}
	
	public void target() {
		//maybe follow creatures of their own team if there are no predators.
		int index = -1, distance = -1;
		for(int i = 0; i < noticed.size(); i++) {
			Creature c = noticed.get(i);
			if(!c.isInvincible() && c.getTeam() != getTeam()) {
				int d = (int) Math.abs(c.getX() - getX());
				if(distance == -1 || d < distance) {
					distance = d;
					index = i;
				}
			}
		}
		if(index == -1) {
			action = ACTION_PASSIVE;
		} else {
			target = noticed.get(index);
			action = ACTION_ATTACKING;
		}
	}
	
	public void up() {
		spdY = -speed;
	}
	
	public void down() {
		spdY = speed;
	}
	
	public void left() {
		spdX = -speed;
	}
	
	public void right() {
		spdX = speed;
	}
	
	public void stopX() {
		spdX = 0;
	}
	
	public void stopY() {
		spdY = 0;
	}
	
	public void jump() {
		if(jumps > 0) {
			jumps--;
			addVerticalAcceleration(-world.getGravity() - Window.RATE * Tile.SIZE * Math.sqrt(jumpHeight) * 4);
		}
	}
	
	public void idle() {
		left();
	}
	
	public void attack() {
		follow(attackRange);
		double distance = Math.abs(x - target.getX());
		if(distance > attackRange) {
			up();
		} else {
			stopY();
		}
		//spawn projectile(s)
	}
	
	public void follow(int distance) {
		if(x - target.getX() < -distance) {
			right();
		} else if(x - target.getX() > distance) {
			left();
		} else {
			stopX();
		}
	}
	
	public void flee(int distance) {
		if(x - target.getX() > -distance) {
			left();
		} else if(x - target.getX() < distance) {
			right();
		} else {
			stopX();
		}
	}
	
	public void update() {
		super.update();
		if(spdY < 0) {
			jump();
		}
		notice();
		if(intelligent) {
			if(target == null) {
				target();
			}
			switch(action) {
			case ACTION_PASSIVE:
				idle();
				break;
			case ACTION_ATTACKING:
				attack();
				break;
			case ACTION_FOLLOWING:
				follow(FOLLOW_RANGE);
				break;
			case ACTION_FLEEING:
				flee(VIEW_RANGE);
				break;
			}
		}
	}
	
	public void inflict(Buff b) {
		for(int i = 0; i < buffs.size(); i++) {
			Buff buff = buffs.get(i);
			if(buff.getType() == b.getType()) {
				buff.addDuration(b.getDuration());
			}
		}
		buffs.add(b);
	}
	
	public void cure(Buff b) {
		if(buffs.contains(b)) {
			buffs.remove(b);
		}
	}
	
	public boolean isInvincible() {
		return invincible;
	}
}
