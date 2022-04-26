package com.whale.nee.main;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public class Handler {
	
	private Main c;
	
	private List<Entity> entities = new ArrayList<Entity>();
	
	public Handler(Main c) {
		this.c = c;
	}
	
	public void update() {
		for (int i = 0; i < entities.size(); i++) if (entities.get(i) != null) entities.get(i).update();
	}
	
	public void draw(Graphics g) {
		for (int i = 0; i < entities.size(); i++) if (entities.get(i) != null) entities.get(i).draw(g);
	}
	
	public void add(Entity e) {
		entities.add(e);
	}
	
	public void remove(Entity e) {
		entities.remove(e);
	}
	
	public Main returnClass() {
		return c;
	}
}
