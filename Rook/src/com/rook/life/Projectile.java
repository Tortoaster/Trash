package com.rook.life;

import java.util.ArrayList;
import java.util.List;

import com.rook.modifier.Enchantment;
import com.rook.world.World;

public abstract class Projectile extends Entity {
	
	public static final int TYPE_ARROW = 0;
	
	protected int type;
	
	protected Entity origin, target;
	
	protected List<Entity> hit = new ArrayList<>();
	protected List<Enchantment> effects = new ArrayList<>();
	
	public Projectile(double x, double y, double velX, double velY, double weight, int width, int height, int type, int team, Entity target, Entity origin, World world) {
		super(x, y, weight, width, height, team, world);
		this.velX = velX;
		this.velY = velY;
		this.type = type;
		this.target = target;
		this.origin = origin;
	}
	
	public void update() {
		super.update();
		for(int i = 0; i < effects.size(); i++) {
			effects.get(i).update();
		}
	}
	
	public void onContact(Entity e) {
		for(int i = 0; i < effects.size(); i++) {
			effects.get(i).onImpact(e);
		}
		getEntityHandler().removeProjectile(this);
	}
	
	public void addModifier(Enchantment mod) {
		for(int i = 0; i < effects.size(); i++) {
			Enchantment effect = effects.get(i);
			if(effect.getType() == mod.getType()) {
				effect.addDuration(mod.getDuration());
			}
		}
		effects.add(mod);
	}
	
	public void removeModifier(Enchantment mod) {
		if(effects.contains(mod)) {
			effects.remove(mod);
		}
	}
}
