package com.rook.mechanics;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class Ray {
	
	public static List<Point> cast(int startX, int startY, int endX, int endY, boolean cover) {
		List<Point> ray = new ArrayList<>();
		int error, previous;
		int x = startX, y = startY;
		int deltaX = Math.abs(endX - startX);
		int deltaY = Math.abs(endY - startY);
		int doubleDeltaX = deltaX * 2;
		int doubleDeltaY = deltaY * 2;
		int stepX = startX > endX ? -1 : 1;
		int stepY = startY > endY ? -1 : 1;
		ray.add(new Point(x, y));
		if(deltaX >= deltaY) {
			previous = deltaX;
			error = previous;
			for(int i = 0; i < deltaX; i++) {
				x += stepX;
				error += doubleDeltaY;
				if(error > doubleDeltaX) {
					y += stepY;
					error -= doubleDeltaX;
					if(cover) {
						if(error + previous < doubleDeltaX) {
							ray.add(new Point(x, y - stepY));
						} else if(error + previous > doubleDeltaX) {
							ray.add(new Point(x - stepX, y));
						}
					}
				}
				ray.add(new Point(x, y));
				previous = error;
			}
		} else {
			previous = deltaY;
			error = previous;
			for(int i = 0; i < deltaY; i++) {
				y += stepY;
				error += doubleDeltaX;
				if(error > doubleDeltaY) {
					x += stepX;
					error -= doubleDeltaY;
					if(cover) {
						if(error + previous < doubleDeltaY) {
							ray.add(new Point(x - stepX, y));
						} else if(error + previous > doubleDeltaY) {
							ray.add(new Point(x, y - stepY));
						}
					}
				}
				ray.add(new Point(x, y));
				previous = error;
			}
		}
		return ray;
	}
}
