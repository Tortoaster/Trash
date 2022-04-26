package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;
import life.Activity;
import life.Creature;
import life.Human;

public class Menu extends State {
	
	private Random r = new Random();
	
	private Creature human = new Human(r.nextInt(1012 / Screen.scale), r.nextInt(720 / Screen.scale), new Color(200, 140, 110), "Rick", false, null);
	private Creature[] noob = new Human[5];
	
	private BufferedImage bg;
	
	public Menu(GSM gsm) {
		super(gsm);
		try {
			bg = ImageIO.read(new File("res/bg.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		for (int i = 0; i < noob.length; i++) {
			int c = r.nextInt(128) + 128;
			noob[i] = new Human(r.nextInt(1012 / Screen.scale), r.nextInt(720 / Screen.scale), new Color(c, c / 2 + r.nextInt(c / 3 + 1), c / 2), "" + (i + 1),true, noob);
		}
	}
	
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_A: human.setXV(-1);
		if (human.getActivity() != Activity.FLYING) human.setActivity(Activity.WALKING);
		break;
		case KeyEvent.VK_D: human.setXV(1);
		if (human.getActivity() != Activity.FLYING) human.setActivity(Activity.WALKING);
		break;
		case KeyEvent.VK_Q: human.setXV(-2);
		if (human.getActivity() != Activity.FLYING) human.setActivity(Activity.WALKING);
		break;
		case KeyEvent.VK_E: human.setXV(2);
		if (human.getActivity() != Activity.FLYING) human.setActivity(Activity.WALKING);
		break;
		case KeyEvent.VK_W: if (human.getActivity() == Activity.FLYING) human.setYV(-1);
		break;
		case KeyEvent.VK_S: if (human.getActivity() == Activity.FLYING) human.setYV(1);
		break;
		case KeyEvent.VK_SPACE: if (human.getY() >= Screen.height - 12) {
			human.setYV(-8);
			human.setActivity(Activity.JUMPING);
		}
		break;
		case KeyEvent.VK_M: if (human.getActivity() == Activity.DANCING) human.setActivity(Activity.STANDING); else human.setActivity(Activity.DANCING);
		break;
		case KeyEvent.VK_N: if (human.getActivity() == Activity.WAVING) human.setActivity(Activity.STANDING); else human.setActivity(Activity.WAVING);
		break;
		case KeyEvent.VK_B: if (human.getActivity() == Activity.WAVING2) human.setActivity(Activity.STANDING); else human.setActivity(Activity.WAVING2);
		break;
		case KeyEvent.VK_V: if (human.getActivity() == Activity.MILKING) human.setActivity(Activity.STANDING); else human.setActivity(Activity.MILKING);
		break;
		case KeyEvent.VK_C: if (human.getActivity() == Activity.STYLING) human.setActivity(Activity.STANDING); else human.setActivity(Activity.STYLING);
		break;
		case KeyEvent.VK_X: if (human.getActivity() == Activity.PUSHING) human.setActivity(Activity.STANDING); else human.setActivity(Activity.PUSHING);
		break;
		case KeyEvent.VK_SHIFT: if (human.getActivity() == Activity.FLYING) human.setActivity(Activity.JUMPING); else human.setActivity(Activity.FLYING);
		break;
		case KeyEvent.VK_Z: for (int i = 0; i < noob.length; i++) noob[i].switchAI();
		}
	}
	
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_A: human.setXV(0);
		if (human.getActivity() != Activity.FLYING) human.setActivity(Activity.STANDING);
		break;
		case KeyEvent.VK_D: human.setXV(0);
		if (human.getActivity() != Activity.FLYING) human.setActivity(Activity.STANDING);
		break;
		case KeyEvent.VK_Q: human.setXV(0);
		if (human.getActivity() != Activity.FLYING) human.setActivity(Activity.STANDING);
		break;
		case KeyEvent.VK_E: human.setXV(0);
		if (human.getActivity() != Activity.FLYING) human.setActivity(Activity.STANDING);
		break;
		case KeyEvent.VK_W: human.setYV(0);
		break;
		case KeyEvent.VK_S: human.setYV(0);
		}
	}
	
	public void update() {
		human.update();
		for (int i = 0; i < noob.length; i++) noob[i].update();
	}
	
	public void draw(Graphics g) {
		g.drawImage(bg, 0, 0, Screen.width, Screen.height, null);
		for (int i = 0; i < noob.length; i++) noob[i].draw(g);
		human.draw(g);
	}
}
