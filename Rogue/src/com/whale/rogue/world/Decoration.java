package com.whale.rogue.world;

public class Decoration {
	
	private boolean special;
	
	public static final int NORMAL = 0;
	private int[] variations, doors;
	
	public Decoration(int[] variations, int[] doors, boolean special) {
		this.variations = variations;
		this.doors = doors;
		this.special = special;
	}
	
	public boolean isQualified(int variation, int door, boolean special) {
		if(this.special == special) {
			boolean found = false;
			for(int i = 0; i < doors.length; i++) {
				if(doors[i] == door) {
					found = true;
					break;
				}
			}
			if(found) {
				for(int i = 0; i < variations.length; i++) {
					if(variations[i] == variation) {
						return true;
					}
				}
				return false;
			} else return false;
		} else return false;
	}
}
