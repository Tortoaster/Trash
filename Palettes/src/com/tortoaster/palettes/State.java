package com.tortoaster.palettes;

import java.awt.Graphics;

public interface State {
	
	void update();
	
	void draw(Graphics g);
}
