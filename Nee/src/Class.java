import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.Graphics;
import java.awt.Image;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Class extends JPanel implements MouseListener, MouseMotionListener, MouseWheelListener, KeyListener, Runnable {
	
	private static final long serialVersionUID = 1L;
	
	Image[][] Image = new Image[32][32];
	Image[][] Mobs = new Image[8][8];
	Image[] Cloud = new Image[2];
	Image Background = new ImageIcon("res/Background.png").getImage();
	Image Night = new ImageIcon("res/Night.png").getImage();
	double xC[] = new double[256];
	int[][][] Map = new int[16][16][2];
	int[][] Mob = new int[16][16];
	int[][] adj = new int[16][16];
	int fps = 8;
	int sel = 1;
	int Time = 0;
	int x;
	int y;
	int xV;
	int yV;
	int xM;
	int yM;
	boolean Darkness = false;
	boolean Dark;
	
	public Class() {
		JFrame frame = new JFrame("Nee 1.0");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1024, 1024);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		this.setFocusable(true);
		frame.add(this);
		for (int i = 0; i < 2; i++) {
			xC[i] = 32 + 32 * i;
		}
		for (int i = 0; i < 2; i++) {
			Cloud[i] = new ImageIcon("res/Cloud" + (i + 1) + ".png").getImage();
		}
		for (int y = 0; y < 32; y++) {
			for (int x = 0; x < 32; x++) {
				Image[x][y] = new ImageIcon("res/Tileset.png").getImage();
				Image[x][y] = createImage(new FilteredImageSource(Image[x][y].getSource(), new CropImageFilter(x * 8, y * 8, 8, 8)));
			}
		}
		for (int y = 0; y < 8; y++) {
			for (int x = 0; x < 8; x++) {
				Mobs[x][y] = new ImageIcon("res/Mobs.png").getImage();
				Mobs[x][y] = createImage(new FilteredImageSource(Mobs[x][y].getSource(), new CropImageFilter(x * 8, y * 8, 8, 8)));
			}
		}
		for (int y = 0; y < 16; y++) {
			for (int x = 0; x < 16; x++) {
				if (y >= 4) {
					if (Map[x][y - 1][1] > 0) {
						if (new Random().nextInt(32) == 0) {
							if (new Random().nextBoolean()) {
								Map[x][y][0] = 2;
								Map[x][y][1] = 3;
							} else {
								Map[x][y][0] = 3;
								Map[x][y][1] = 2;
							}
						} else {
							Map[x][y][0] = 2;
							Map[x][y][1] = 2;
						}
					} else if (new Random().nextInt(8) == 0) {
						Map[x][y][0] = 1;
						Map[x][y][1] = 1;
					} else if (x > 0 && Map[x - 1][y - 1][1] > 0 || x == 0 && Map[15][y - 1][1] > 0 || x < 15 && Map[x + 1][y - 1][1] > 0 || x == 15 && Map[0][y - 1][1] > 0) {
						if (Map[x][y - 1][1] > 0) {
							if (new Random().nextInt(32) == 0) {
								if (new Random().nextBoolean()) {
									Map[x][y][0] = 2;
									Map[x][y][1] = 3;
								} else {
									Map[x][y][0] = 3;
									Map[x][y][1] = 2;
								}
							} else {
								Map[x][y][0] = 2;
								Map[x][y][1] = 2;
							}
						} else {
							Map[x][y][0] = 1;
							Map[x][y][1] = 1;
						}
					} else {
						Map[x][y][0] = 0;
						Map[x][y][1] = 0;
					}
				}
				Mob[x][y] = -1;
			}
		}
		x = new Random().nextInt(16);
		y = 1;
		addMouseListener(this);
		addMouseMotionListener(this);
		addKeyListener(this);
		addMouseWheelListener(this);
		new Thread(this).start();
	}
	
	public static void main(String[] args) {
		new Class();
	}
	
	public void mouseClicked(MouseEvent e) {
		if (Mob[xM / 64][yM / 64] > -1 && Math.abs(x - xM / 64) < 4 && Math.abs(y - yM / 64) < 4) {
			Mob[xM / 64][yM / 64] = -1;
		} else if (e.getButton() == MouseEvent.BUTTON1 && Math.abs(x - xM / 64) < 4 && Math.abs(y - yM / 64) < 4) {
			for (int i = 1; i > -1; i--) {
				if (Map[xM / 64][yM / 64][i] > 0) {
					Map[xM / 64][yM / 64][i] = 0;
					break;
				}
			}
		} else if (e.getButton() == MouseEvent.BUTTON3 && Math.abs(x - xM / 64) < 4 && Math.abs(y - yM / 64) < 4) {
			for (int i = 0; i < 2; i++) {
				if (Map[xM / 64][yM / 64][i] == 0) {
					Map[xM / 64][yM / 64][i] = sel;
					break;
				}
			}
		}
	}
	
	public void mouseEntered(MouseEvent e) {
		
	}
	
	public void mouseExited(MouseEvent e) {
		
	}
	
	public void mousePressed(MouseEvent e) {
		
	}
	
	public void mouseReleased(MouseEvent e) {
		
	}
	
	public void mouseDragged(MouseEvent e) {
		xM = e.getX();
		yM = e.getY();
	}
	
	public void mouseMoved(MouseEvent e) {
		xM = e.getX();
		yM = e.getY();
	}
	
	public void mouseWheelMoved(MouseWheelEvent e) {
		sel += e.getWheelRotation();
	}
	
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_W && Map[x][y + 1][1] > 0 || e.getKeyCode() == KeyEvent.VK_W && Map[x][y][0] == 8) {
			yV = -1;
		}
		if (e.getKeyCode() == KeyEvent.VK_D) {
			xV = 1;
		}
		if (e.getKeyCode() == KeyEvent.VK_S) {
			yV = 1;
		}
		if (e.getKeyCode() == KeyEvent.VK_A) {
			xV = -1;
		}
	}
	
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_W) {
			yV = 0;
		}
		if (e.getKeyCode() == KeyEvent.VK_D) {
			xV = 0;
		}
		if (e.getKeyCode() == KeyEvent.VK_S) {
			yV = 0;
		}
		if (e.getKeyCode() == KeyEvent.VK_A) {
			xV = 0;
		}
	}
	
	public void keyTyped(KeyEvent e) {
		
	}
	
	public void run() {
		while (true) {
			Darkness = true;
			for (int y = 15; y > -1; y--) {
				for (int x = 0; x < 16; x++) {
					for (int i = 0; i < 2; i++) {
						if (y < 15 && Map[x][y][i] == 7 && Map [x][y + 1][i] == 0) {
							Map[x][y][i] = 0;
							Map[x][y + 1][i] = 7;
						} else if (y == 15 && Map[x][y][i] == 7) {
							Map[x][y][i] = 0;
						}
					}
					if (Mob[x][y] > -1 && new Random().nextBoolean()) {
						if (y > 0 && y < 15 && Map[x][y - 1][1] == 0 && Map[x][y + 1][1] > 0 && new Random().nextBoolean()) {
							Mob[x][y - 1] = Mob [x][y];
							Mob[x][y] = -1;
						}
						if (new Random().nextBoolean()) {
							if (x < 15 && Map[x + 1][y][1] == 0 && Mob[x + 1][y] == -1 && new Random().nextBoolean()) {
								Mob[x + 1][y] = Mob [x][y];
								Mob[x][y] = -1;
							} else if (x == 15 && Map[0][y][1] == 0 && Mob[0][y] == -1 && new Random().nextBoolean()) {
								Mob[0][y] = Mob [x][y];
								Mob[x][y] = -1;
							}
							if (x > 0 && Map[x - 1][y][1] == 0 && Mob[x - 1][y] == -1 && new Random().nextBoolean()) {
								Mob[x - 1][y] = Mob [x][y];
								Mob[x][y] = -1;
							} else if (x == 0 && Map[15][y][1] == 0 && Mob[15][y] == -1 && new Random().nextBoolean()) {
								Mob[15][y] = Mob [x][y];
								Mob[x][y] = -1;
							}
						}
					}
					if (Mob[x][y] > -1 && y < 15 && Map[x][y + 1][1] == 0 && Mob[x][y + 1] == -1) {
						Mob[x][y + 1] = Mob[x][y];
						Mob[x][y] = -1;
					} else if (y == 15 && Mob[x][y] > -1) {
						Mob[x][y] = -1;
					}
					if (y < 15 && Map[x][y][1] == 0 && Map[x][y + 1][1] == 1 && new Random().nextInt(1024) == 0) {
						Mob[x][y] = new Random().nextInt(3);
					} else if (Mob[x][y] == 2 && new Random().nextInt(128) == 0) {
						Mob[x][y] = new Random().nextInt(3) + 3;
					} else if (Mob[x][y] > -1 && new Random().nextInt(1024) == 0) {
						Mob[x][y] = -1;
					}
					adj[x][y] = -1;
					if (Map[x][y][0] == 0 && y <= 8) {
						adj[x][y] = Time / 100;
						Darkness = false;
					} else if (Map[x][y][0] == 9 || Map[x][y][1] == 9) {
						adj[x][y] = 0;
						Darkness = false;
					}
				}
			}
			for (int y = 0; y < 16; y++) {
				for (int x = 0; x < 16; x++) {
					for (int i = 0; i < 32; i++) {
						if (adj[x][y] == i) {
							if (y > 0 && adj[x][y - 1] > i + 1 || y > 0 && adj[x][y - 1] < 0) {
								adj[x][y - 1] = i + 1;
							}
							if (x < 15 && adj[x + 1][y] > i + 1 || x < 15 && adj[x + 1][y] < 0) {
								adj[x + 1][y] = i + 1;
							}
							if (y < 15 && adj[x][y + 1] > i + 1 || y < 15 && adj[x][y + 1] < 0) {
								adj[x][y + 1] = i + 1;
							}
							if (x > 0 && adj[x - 1][y] > i + 1 || x > 0 && adj[x - 1][y] < 0) {
								adj[x - 1][y] = i + 1;
							}
						}
					}
				}
			}
			for (int y = 15; y > -1; y--) {
				for (int x = 15; x > -1; x--) {
					for (int i = 0; i < 32; i++) {
						if (adj[x][y] == i) {
							if (y > 0 && adj[x][y - 1] > i + 1 || y > 0 && adj[x][y - 1] < 0) {
								adj[x][y - 1] = i + 1;
							}
							if (x < 15 && adj[x + 1][y] > i + 1 || x < 15 && adj[x + 1][y] < 0) {
								adj[x + 1][y] = i + 1;
							}
							if (y < 15 && adj[x][y + 1] > i + 1 || y < 15 && adj[x][y + 1] < 0) {
								adj[x][y + 1] = i + 1;
							}
							if (x > 0 && adj[x - 1][y] > i + 1 || x > 0 && adj[x - 1][y] < 0) {
								adj[x - 1][y] = i + 1;
							}
						}
					}
				}
			}
			if (sel < 1) {
				sel = 10;
			} else if (sel > 10) {
				sel = 1;
			}
			if (x + xV < 0) {
				if (Map[15][y][1] > 0) {
					xV = 0;
				} else {
					x = 16;
				}
			} else if (x + xV > 15) {
				if (Map[0][y][1] > 0) {
					xV = 0;
				} else {
					x = -1;
				}
			}
			if (y + yV < 1) {
				yV = 0;
			} else if (y + yV > 15) {
				x = new Random().nextInt(16);
				y = 1;
			}
			if (Map[x + xV][y][1] == 0 && Map[x + xV][y - 1][1] == 0) {
				x += xV;
			}
			if (Map[x][y + yV][1] == 0 && Map[x][y + yV - 1][1] == 0) {
				y += yV;
			}
			if (yV < 1 && Map[x][y][0] != 8) {
				yV++;
			}
			for (double i = 0; i < 2; i++) {
				xC[(int) i] += .25 + i / 2;
				if (xC[(int) i] > 128) {
					xC[(int) i] = -32;
				}
			}
			if (Time == 850) {
				Dark = true;
			} else if (Time == 50) {
				Dark = false;
			}
			if (Dark) {
				Time--;
			} else {
				Time++;
			}
			repaint();
			try {
				Thread.sleep(1000 / fps);
			} catch (Exception e) {
				
			}
		}
	}
	
	public void paint(Graphics g) {
		g.drawImage(Background, 0, 0, 1024, 1024, null);
		for (int i = 0; i < 2; i++) {
			g.drawImage(Cloud[i], (int) xC[i] * 8, (4 + 4 * i) * 8, 256, 128, null);
		}
		for (int y = 0; y < 16; y++) {
			for (int x = 0; x < 16; x++) {
				g.drawImage(Image[Map[x][y][0]][0], x * 64, y * 64, 64, 64, null);
				if (Map[x][y][0] > 0 && Map[x][y][0] != 9 && Map[x][y][0] != 10) {
					g.drawImage(Image[1][1], x * 64, y * 64, 64, 64, null);
				}
				g.drawImage(Image[Map[x][y][1]][0], x * 64, y * 64, 64, 64, null);
			}
		}
		g.drawImage(Image[0][1], x * 64, (y - 1) * 64, 64, 64, null);
		g.drawImage(Image[0][2], x * 64, y * 64, 64, 64, null);
		for (int y = 0; y < 16; y++) {
			for (int x = 0; x < 16; x++) {
				if (Mob[x][y] > -1) {
					g.drawImage(Mobs[Mob[x][y]][0], x * 64, y * 64, 64, 64, null);
				}
				for (int i = 0; i < adj[x][y]; i++) {
					g.drawImage(Image[2][1], x * 64, y * 64, 64, 64, null);
				}
			}
		}
		g.drawImage(Image[sel][0], xM, yM, 32, 32, null);
		if (Darkness) {
			g.drawImage(Night, 0, 0, 1024, 1024, null);
		}
	}
}
