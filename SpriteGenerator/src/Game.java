import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Game implements State {
	
	private static final int WIDTH = 10, HEIGHT = 10, SIZE = 8, SCALE = 8, MARGIN = 2, COLORS = 3, FRAMES = 4;
	
	private BufferedImage[][][] sprites;
	
	private final Window window;
	
	public Game(Window window) {
		this.window = window;
		
		sprites = new BufferedImage[WIDTH][HEIGHT][FRAMES];
		
		Random random = new Random();
		
		for(int r = 0; r < HEIGHT; r++) {
			for(int c = 0; c < WIDTH; c++) {
				Color[] colors = new Color[COLORS];
				
				for(int i = 0; i < COLORS; i++) {
					colors[i] = new Color(random.nextFloat(), random.nextFloat(), random.nextFloat());
				}
				
				BufferedImage sprite = new BufferedImage(SIZE, SIZE, BufferedImage.TYPE_INT_RGB);
				Graphics g = sprite.getGraphics();
				
				for(int y = 0; y < SIZE; y++) {
					for(int x = 0; x < SIZE / 2; x++) {
						g.setColor(colors[random.nextInt(COLORS)]);
						
						int neighbors = 0;
						
						if(x > 0 && sprite.getRGB(x - 1, y) == 0) neighbors++;
						if(y > 0 && sprite.getRGB(x, y - 1) == 0) neighbors++;
						if(x > 0 && y > 0 && sprite.getRGB(x - 1, y - 1) == 0) neighbors++;
						
						if(random.nextInt(COLORS - neighbors - 1) == 0) g.setColor(Color.black);
						
						g.drawLine(x, y, SIZE - x - 1, y);
					}
				}
				
				sprites[c][r][0] = sprite;
				
				for(int i = 1; i <= FRAMES / 2; i++) {
					sprite = new BufferedImage(sprite.getColorModel(), sprite.copyData(null), sprite.getColorModel().isAlphaPremultiplied(), null);
					g = sprite.getGraphics();
					
					for(int y = 0; y < SIZE; y++) {
						if(random.nextInt(HEIGHT / 2) == 0) {
							for(int x = 0; x < SIZE / 2; x++) {
								g.setColor(colors[random.nextInt(COLORS)]);
								
								int neighbors = 0;
								
								if(x > 0 && sprite.getRGB(x - 1, y) == 0) neighbors++;
								if(y > 0 && sprite.getRGB(x, y - 1) == 0) neighbors++;
								if(x > 0 && y > 0 && sprite.getRGB(x - 1, y - 1) == 0) neighbors++;
								
								if(random.nextInt(COLORS - neighbors - 1) == 0) g.setColor(Color.black);
								
								g.drawLine(x, y, SIZE - x - 1, y);
							}
						}
					}
					
					sprites[c][r][i] = sprite;
				}
				
				for(int i = FRAMES / 2 + 1; i < FRAMES; i++) {
					sprites[c][r][i] = sprites[c][r][FRAMES - i];
				}
			}
		}
	}
	
	@Override
	public void update() {
		
	}
	
	@Override
	public void draw(Graphics g) {
		g.setColor(Color.black);
		g.fillRect(0, 0, window.getScreenWidth(), window.getScreenHeight());
		for(int y = 0; y < HEIGHT; y++) {
			for(int x = 0; x < WIDTH; x++) {
				g.drawImage(sprites[x][y][(int) (System.currentTimeMillis() / 250 % FRAMES)], x * (SIZE + MARGIN) * SCALE + MARGIN * SCALE / 2, y * (SIZE + MARGIN) * SCALE + MARGIN * SCALE / 2 + (int) (MARGIN * SCALE * Math.sin(System.currentTimeMillis() / 250.0 + x + y) / 4), SIZE * SCALE, SIZE * SCALE, null);
			}
		}
	}
}
