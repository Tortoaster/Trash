package main;

import java.awt.Point;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class Window extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	public static int width;
	public static int height;
	
	private GSM gsm = new GSM();
	
	private Listener listener = new Listener(gsm);
	
	private Loop loop = new Loop(gsm, this);
	
	private Screen screen;
	
	public Window(int width, int height, String title) {
		Window.width = width;
		Window.height = height;
		screen = new Screen(gsm);
		add(screen);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setSize(width + 6, height + 29);
		setLocationRelativeTo(null);
		setTitle(title);
		setVisible(true);
		addKeyListener(listener);
		addMouseListener(listener);
		addMouseMotionListener(listener);
		addMouseWheelListener(listener);
		try {
			setCursor(Toolkit.getDefaultToolkit().createCustomCursor(ImageIO.read(new File("res/cursor.png")), new Point(0, 0), "Cursor"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		new Thread(loop).start();
	}
}
